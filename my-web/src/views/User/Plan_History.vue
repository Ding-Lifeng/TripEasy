<template>
  <div id="history-plans">
    <h1>历史规划</h1>
    <div class="cards-container">
      <div class="card" v-for="plan in tripContents" :key="plan.trip_id">
        <div class="card-content">
          <h2>规划时间:
            <span v-if="!plan.isEditing">{{ plan.time }}</span>
            <el-input v-if="plan.isEditing" v-model="plan.time" size="small"></el-input></h2>
          <p v-if="!plan.isEditing">{{ plan.content }}</p>
          <el-input
              v-if="plan.isEditing"
              type="textarea"
              v-model="plan.content"
              size="large"
              autosize
              class="edit-textarea"
          ></el-input>
          <el-button v-if="plan.isEditing" type="success" icon="el-icon-check" circle @click="endEditing(plan)"></el-button>
          <el-button type="primary" icon="el-icon-edit" circle @click="startEditing(plan)"></el-button>
          <el-button type="danger" icon="el-icon-delete" circle @click="deletePlan(plan.trip_id)"></el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import { Dialog } from 'element-ui';
import { getTripContents, deleteTripContent, updateTripContent } from '@/api/dealWithTripContent';

export default {
  name: 'Plan_History',
  components: {
    'el-dialog': Dialog,
  },
  data() {
    return {
      tripContents: [],
    };
  },
  async created() {
    try {
      const response = await getTripContents();
      this.tripContents = response.data.map(plan => ({
        ...plan,
        isEditing: false
      }));
    } catch (error) {
      console.error('获取旅行计划内容失败!', error);
    }
  },
  methods: {
    startEditing(plan) {
      plan.isEditing = true;
    },
    async endEditing(plan) {
      try {
        await updateTripContent(plan);
        plan.isEditing = false;
        this.$message.success('旅行规划修改成功!');
      } catch (error) {
        plan.isEditing = false;
        this.$message.error('旅行规划修改失败!');
        console.error('修改旅行计划失败!', error);
      }
    },
    async deletePlan(tripId) {
      try {
        await deleteTripContent(tripId);
        this.tripContents = this.tripContents.filter(plan => plan.trip_id !== tripId);
        this.$message.success('旅行规划删除成功!');
      } catch (error) {
        console.error('删除旅行计划失败!', error);
      }
    },
  },
};
</script>

<style scoped>
#history-plans {
  text-align: center;
  background-color: #f8f9fa;
  padding: 2rem;
  border-radius: 10px;
  width: 80%;
  margin: 2rem auto;
}

h1 {
  font-size: 2.5rem;
  font-weight: bold;
  margin-bottom: 2rem;
}

.cards-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
}

.card {
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 10px;
  margin: 1rem;
  padding: 1rem;
  width: 300px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.card-content {
  text-align: left;
}

.card-content h2 {
  font-size: 1.5rem;
  margin-bottom: 0.5rem;
}

.card-content p {
  font-size: 1rem;
  margin-bottom: 1rem;
}

.card-content button {
  color: white;
  cursor: pointer;
}
</style>
