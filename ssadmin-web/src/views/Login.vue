<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-logo">
        <a href="#">
          <img src="../assets/卡通导游-静态.png" alt="TripEasy">
        </a>
      </div>
      <h1 class="title">ASK TripEasy</h1>
      <div class="login-form">
        <h3>用户登录</h3>
        <el-form ref="form" :model="loginForm" :rules="rules" label-position="left" label-width="0">
          <el-form-item prop="username">
            <el-input v-model="loginForm.username" placeholder="请输入用户名" ></el-input>
          </el-form-item>
          <el-form-item prop="password">
            <el-input type="password" v-model="loginForm.password" placeholder="请输入密码" ></el-input>
          </el-form-item>
          <el-form-item class="button-group">
            <el-button type="primary" @click="do_login" :loading="loading">登录</el-button>
            <el-button type="success" @click="goToRegister">注册</el-button>
          </el-form-item>
        </el-form>
      </div>
    </div>
  </div>
</template>

<script>
import {login} from '@/api/login.js'
import { setToken } from '@/utils/auth'
export default {
  data() {
    return {
      loginForm: {
        username: 'user',
        password: '1234abc'
      },
      rules: {
        username: [{ required: true, message: '请输入用户名', trigger: 'blur' }],
        password: [{ required: true, message: '请输入密码', trigger: 'blur' }]
      },
      loading: false
    }
  },
  methods: {
    do_login() {
      this.$refs.form.validate(valid => {
        if (valid) {
          this.loading = true
          // 登录处理逻辑中增加网络请求
          login(this.loginForm.username,this.loginForm.password).then(res =>{
            // 假设res.data.token是从后端接口返回的token
            console.log(res.data.accessToken)
            setToken(res.data.accessToken)
            this.$router.push({ path: '/' })
          }).catch(() => {
            // 登录失败，显示错误提示
            this.$message.error('用户名或密码错误')
            this.loading = false
          })
        }
      })
    },
    goToRegister(){
      this.$router.push('/Submit');
    },
  }
}
</script>

<style lang="scss">
.login-container {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

  .login-box {
    width: 400px;
    height: 400px;
    border-radius: 5px;
    box-shadow: 0 0 10px #ccc;

    .login-logo {
      height: 100px;
      display: flex;
      justify-content: center;
      align-items: center;

      img {
        width: 100px;
        height: 100px;
        border-radius: 50%;
        border: 3px solid white;
        margin-bottom: 1rem;
      }
    }

    .title {
      text-align: center;
      font-size: 14px;
      margin-bottom: 4px;
    }

    .login-form {
      padding: 20px;

      h3 {
        font-size: 24px;
        margin-bottom: 20px;
        text-align: center;
      }
    }
  }
}
</style>
