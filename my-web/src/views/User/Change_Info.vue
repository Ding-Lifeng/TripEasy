<template>
  <div id="user-profile">
    <!-- 用户信息表单 -->
    <div class="user-info">
      <h1>修改用户信息</h1>
      <el-form ref="form" :model="userInfo" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="userInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="userInfo.password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="userInfo.real_name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="userInfo.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phoneNum">
          <el-input v-model="userInfo.phoneNum"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">修改信息</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
    <!-- 旅游规划展示 -->
    <div class="plans-container">
      <h2>我的规划分享</h2>
      <div class="plan-card" v-for="plan in plans" :key="plan.id">
        <div class="card-content">
          <h3>{{ plan.title }}</h3>
          <p>{{ plan.summary }}</p>
          <el-button type="primary">修改</el-button>
          <el-button type="danger">删除</el-button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import {
  Form,
  FormItem,
  RadioGroup,
  Radio,
  Button,
} from 'element-ui';

import { changeUserInfo, getUserInfo } from "@/api/changeUserInfo";
import {removeToken} from "@/utils/auth";

export default {
  name: 'UserProfile',
  components: {
    'el-form': Form,
    'el-form-item': FormItem,
    'el-radio-group': RadioGroup,
    'el-radio': Radio,
    'el-button': Button,
  },
  data() {
    return {
      userInfo: {
        name: '',
        password: '',
        real_name: '',
        gender: '',
        phoneNum: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' },
        ],
        phone: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
        ],
      },
      plans: [
        { id: 1, title: '昆明三天两晚游', summary: '游览翠湖、滇池、西山等景点' },
        { id: 2, title: '大理五日游', summary: '洱海骑行、苍山徒步、古城游览' },
        // 添加更多的计划...
      ],
    };
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      getUserInfo()
          .then(response => {
            const { name, password, real_name, gender, phoneNum } = response.data;
            this.userInfo = { name, password, real_name, gender, phoneNum };
          })
          .catch(error => {
            console.error('获取用户信息失败:', error);
            this.$message.error('获取用户信息失败!');
          });
    },
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          changeUserInfo(this.userInfo.name, this.userInfo.password, this.userInfo.real_name, this.userInfo.gender, this.userInfo.phoneNum)
              .then(response => {
                this.$message.success('用户修改成功!');
                removeToken() // 清除token
                this.$router.push('/Login');
              })
              .catch(error => {
                console.error('修改失败:', error);
                this.$message.error('用户修改失败!');
              });
        } else {
          console.log('表单验证失败');
        }
      });
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
  },
};
</script>

<style scoped>
#user-profile {
  text-align: center;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 10px;
  width: 80%;
  margin: 2rem auto;
}

.user-info {
  background-color: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;
}

.plans-container {
  background-color: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.plan-card {
  background-color:#ffffff;
  padding: 1rem;
  border-radius: 10px;
  margin-bottom: 1rem;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.card-content {
  text-align: left;
}

.card-content h3 {
  margin: 0;
  font-size: 1.5rem;
  color: #333;
}

.card-content p {
  margin: 0.5rem 0;
  color: #666;
}
</style>