<template>
  <div id="user-profile">
    <!-- 用户信息表单 -->
    <div class="user-info">
      <el-button type="success" icon="el-icon-arrow-left" @click="goToLogin" class="back-button">登录</el-button>
      <h1>注册用户信息</h1>
      <el-form ref="form" :model="userInfo" :rules="rules" label-width="100px">
        <el-form-item label="用户名" prop="name">
          <el-input v-model="userInfo.name"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="userInfo.password"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="real_name">
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
  RadioGroup,
  Radio,
  Button,
} from 'element-ui';
import {submit} from '@/api/submit.js'

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
        name: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
        ],
        real_name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
        ],
        gender: [
          { required: true, message: '请选择性别', trigger: 'change' },
        ],
        phoneNum: [
          { required: true, message: '请输入手机号', trigger: 'blur' },
        ],
      },
    };
  },
  methods: {
    submitForm() {
      this.$refs.form.validate(valid => {
        if (valid) {
          submit(this.userInfo.name, this.userInfo.password, this.userInfo.real_name, this.userInfo.gender, this.userInfo.phoneNum)
              .then(response => {
                this.$message.success('用户注册成功!');
                this.$router.push('/Login');
              })
              .catch(error => {
                console.error('注册失败:', error);
                this.$message.error('用户注册失败!');
              });
        } else {
          console.log('表单验证失败');
        }
      });
    },
    resetForm() {
      this.$refs.form.resetFields();
    },
    goToLogin() {
      this.$router.push('/Login');
    },
  },
};
</script>

<style scoped>
#user-profile {
  text-align: center;
  padding: 2rem;
  border-radius: 10px;
  width: 100%;
  margin: 2rem auto;
}

.user-info {
  background-color: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  margin-bottom: 2rem;

  .back-button {
    position: absolute;
    top: 80px;
    left: 60px;
  }
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