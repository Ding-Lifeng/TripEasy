<template>
  <div class="container">
    <div class="input-section">
      <el-input
          type="textarea"
          v-model="inputContent"
          placeholder="昆明三天两晚游玩推荐"
          autosize
          :rows="10"
          class="input-box"
      ></el-input>
    </div>
    <div class="checkbox-section">
      <span class="checkbox-label">出行需求：</span>
      <el-checkbox-group v-model="checkboxGroup" class="checkbox-group">
        <el-checkbox-button label="个人用户" :indeterminate="true"></el-checkbox-button>
        <el-checkbox-button label="商务旅行" :indeterminate="true"></el-checkbox-button>
        <el-checkbox-button label="旅行达人" :indeterminate="true"></el-checkbox-button>
        <el-checkbox-button label="学生用户" :indeterminate="true"></el-checkbox-button>
        <el-checkbox-button label="团体用户" :indeterminate="true"></el-checkbox-button>
      </el-checkbox-group>
    </div>
    <el-button type="primary" @click="fetchData">生成规划</el-button>
    <div v-if="showPlan" class="plan-box">
      <h2>旅行规划</h2>
      <div v-if="isEditing">
        <el-input
            type="textarea"
            v-model="planContent"
            autosize
            :rows="10"
            class="input-box"
        ></el-input>
      </div>
      <span v-else class="plan-content">{{ planContent }}</span>
      <div class="button-group">
        <el-button v-if="isEditing" type="success" icon="el-icon-check" circle @click="endEditing"></el-button>
        <el-button type="primary" icon="el-icon-edit" circle @click="startEditing"></el-button>
        <el-button type="warning" icon="el-icon-star-off" circle @click="sendPlanContent"></el-button>
        <el-button type="danger" icon="el-icon-delete" circle @click="clearContent"></el-button>
      </div>
    </div>
  </div>
</template>

<script>
import {sendMessageToGpt} from '@/api/chatWithGpt';
import {v4 as uuidv4} from 'uuid';
import { dealWithTripContent } from '@/api/dealWithTripContent';
import { getAccessToken } from '@/utils/auth';

export default {
  name: 'Planned_Trip',
  props: {
    initialInput: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      inputContent: this.initialInput,
      checkboxGroup: [],
      showPlan: false,
      sessionId: uuidv4(),
      planContent: '',
      isEditing: false,
    };
  },
  watch: {
    initialInput(newVal) {
      this.inputContent = newVal;
    }
  },
  methods: {
    startEditing() {
      this.isEditing = true;
    },
    endEditing() {
      this.isEditing = false;
    },
    clearContent() {
      this.planContent = '';
      this.showPlan = false;
    },
    async fetchData() {
      try {
        const message = this.inputContent.trim() === '' ? '昆明三天两晚游玩推荐' : this.inputContent;

        // 出行需求对应prompt
        const checkboxMap = {
          '个人用户': '我是个人用户，正常安排出行即可，没有特殊需求。',
          '商务旅行': '我是商务旅行用户，我的时间、精力比较有限，请尽量给我安排低体力消耗的活动并严格遵守我的时间要求。',
          '旅行达人': '我是旅行达人，我具有丰富的出游经验，请给我安排具有深度的旅行行程和小众有趣的景点。',
          '学生用户': '我是学生用户，我的出行经费有限但精力充足，请给我安排低价实惠并且内容丰富的行程。',
          '团体用户': '我是团体用户，我们的需求多样，请在满足个人出行需求的同时，尽量多的安排公共行程。'
        };
        let additionalInfo = '';
        this.checkboxGroup.forEach(label => {
          if (checkboxMap[label]) {
            additionalInfo += checkboxMap[label] + ' ';
          }
        });
        const fullMessage = `${message} ${additionalInfo}`.trim();

        const response = await sendMessageToGpt(fullMessage, this.sessionId);
        const content = response.data.content;
        if (content) {
          this.planContent = content;
        } else {
          this.planContent = '无法获取规划内容，请稍后再试';
        }
        this.showPlan = true;
      } catch (error) {
        this.planContent = (error && error.message) ? error.message : '发生未知错误，请稍后再试';
        this.showPlan = true;
      }
    },
    async sendPlanContent() {
      const token = getAccessToken();
      if (!token) {
        this.$message.error('用户未登录，无法保存规划!');
        return;
      }

      try {
        await dealWithTripContent(this.planContent);
        this.$message.success('旅行规划已成功保存!');
      } catch (error) {
        this.$message.error('提交失败，请稍后再试!');
      }
    }
  },
};
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}

.input-section {
  margin-bottom: 20px;
  width: 80%;
}

.input-box {
  width: 100%;
}

.checkbox-section {
  display: flex;
  align-items: center;
  justify-content: center;
  margin-bottom: 20px;
}

.checkbox-label {
  margin-right: 10px;
}

.checkbox-group {
  display: flex;
}

.plan-box {
  margin-top: 20px;
  padding: 20px;
  border: 1px solid #ddd;
  border-radius: 5px;
  width: 80%;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  position: relative;
}

.button-group {
  position: absolute;
  top: 10px;
  right: 10px;
  display: flex;
  justify-content: flex-end;
}

.plan-content {
  font-size: 22px;
}
</style>