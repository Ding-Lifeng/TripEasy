<template>
  <div>
      <div class="side-bar">
          <el-menu class="el-menu-vertical-demo" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b">
              <el-menu-item index="/" @click="$router.push('/')">
                  <i class="el-icon-guide"></i>
                  <span slot="title">旅行规划</span>
              </el-menu-item>
              <el-menu-item index="/rbac/user/plan-recommend" @click="$router.push('/rbac/user/plan-recommend')">
                  <i class="el-icon-place"></i>
                  <span slot="title">游玩推荐</span>
              </el-menu-item>
              <el-menu-item index="/rbac/user/plan-history" @click="$router.push('/rbac/user/plan-history')">
                  <i class="el-icon-guide"></i>
                  <span slot="title">历史规划</span>
              </el-menu-item>
              <el-menu-item index="/rbac/user/change-info" @click="$router.push('/rbac/user/change-info')">
                  <i class="el-icon-user"></i>
                  <span slot="title">用户信息</span>
              </el-menu-item>
              <el-menu-item index="/logout" @click="logout">
                  <i class="el-icon-setting"></i>
                  <span slot="title">退出</span>
              </el-menu-item>
          </el-menu>

          <div class="main-content">
              <router-view/>
          </div>
      </div>
  </div>
</template>

<script>
import { removeToken } from '@/utils/auth'

export default {
  name: "MyComponent",
  methods: {
    logout() {
      this.$confirm('确定注销并退出系统吗？', '提示').then(() => {
        removeToken() // 清除token
        this.$router.push({ path: '/login' }) // 重定向到登录页面
      }).catch(() => {});
    },
  },
};
</script>


<style lang="scss" scoped>
.side-bar {
display: flex;
height: 100vh;
}

.main-content {
flex: 1;
padding: 20px;
}

</style>
