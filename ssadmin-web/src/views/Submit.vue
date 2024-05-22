<template>
  <div id="user-profile">
    <!-- 用户信息表单 -->
    <div class="user-info">
      <h1>注册用户信息</h1>
      <el-form ref="form" :model="userInfo" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="userInfo.username"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="name">
          <el-input v-model="userInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="userInfo.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="userInfo.phone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">保存信息</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import {
  Form,
  FormItem,
  Input,
  RadioGroup,
  Radio,
  Button,
} from 'element-ui';

export default {
  name: 'UserProfile',
  components: {
    'el-form': Form,
    'el-form-item': FormItem,
    'el-input': Input,
    'el-radio-group': RadioGroup,
    'el-radio': Radio,
    'el-button': Button,
  },
  data() {
    return {
      userInfo: {
        username: '',
        name: '',
        gender: '',
        phone: '',
      },
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
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
  methods: {
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          // TODO: 提交用户信息到后端
          console.log(this.userInfo);
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