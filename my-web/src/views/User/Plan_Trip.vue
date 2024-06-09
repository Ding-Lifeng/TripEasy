<template>
  <div id="ask-TripEasy">
    <!-- 行易卡通头像 -->
    <div class="avatar">
      <img src="../../assets/卡通导游-静态.png" alt="TripEasy">
    </div>
    <!-- 标题和描述 -->
    <h1>ASK TripEasy</h1>
    <p>行易:你的云南AI旅行规划师</p>
    <!-- 问题输入框和按钮 -->
    <div class="input-container">
      <input v-model="userInput" type="text" placeholder="昆明三天两晚游玩推荐">
      <button class="ask-button" @click="startToPlans()">开始规划</button>
    </div>
    <!-- 功能按钮 -->
    <div class="action-buttons">
      <button class="action-button" @click="jumpToPlans()">
        <i class="el-icon-notebook-1"></i>
        <span slot="title">规划分享</span>
      </button>
      <button class="action-button" @click="showView()">
        <i class="el-icon-ship"></i>
        <span slot="title">周边景点</span>
      </button>
      <button class="action-button" @click="showFood()">
        <i class="el-icon-knife-fork"></i>
        <span slot="title">美食推荐</span>
      </button>
      <button class="action-button" @click="showPlans()">
        <i class="el-icon-map-location"></i>
        <span slot="title">旅行路线</span>
      </button>
      <!-- 视频区域 -->
      <div id="video-section">
        <video autoplay loop width="100%">
          <source src="../../assets/云南玉龙雪山视频.mp4" type="video/mp4">
        </video>
      </div>
    </div>
    <el-dialog :visible.sync="popup" width="75%">
      <component ref="dynamicComponent" :is="currentComponent" :initialInput="userInput"></component>
    </el-dialog>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import { Dialog } from 'element-ui';
export default {
  name: 'ask-TripEasy',
  components:{
    'el-dialog': Dialog
  },
  data() {
    return {
      popup: false,
      currentComponent: null,
      userInput: ''
    }
  },
  methods: {
    jumpToPlans(){
      this.$router.push('/rbac/user/plan-recommend');
    },
    async startToPlans(){
      const component = await import('./Side_Page/Planned_Trip.vue');
      this.currentComponent = component.default;
      this.popup = true;
    },
    async showView(){
      const component = await import('./Side_Page/ViewVue.vue');
      this.currentComponent = component.default;
      this.popup = true;
    },
    async showFood(){
      const component = await import('./Side_Page/FoodVue.vue');
      this.currentComponent = component.default;
      this.popup = true;
    },
    async showPlans(){
      const component = await import('./Side_Page/PlansVue.vue');
      this.currentComponent = component.default;
      this.popup = true;
    }
  }
}
</script>

<style scoped>
  #ask-TripEasy {
    text-align: center;
    background-color: #17a2b8;
    padding: 2rem;
    border-radius: 10px;
    width: 80%;
    margin: 2rem auto;
    color: white;
  }

  .avatar img {
    width: 80px;
    height: 80px;
    border-radius: 50%;
    border: 3px solid white;
    margin-bottom: 1rem;
  }

  h1 {
    font-size: 2.5rem;
    font-weight: bold;
    margin: 0;
  }

  p {
    font-size: 1.2rem;
    margin: 1rem 0;
  }

  .input-container {
    display: flex;
    justify-content: center;
    margin: 2rem 0;
  }

  input[type="text"] {
    width: 60%;
    padding: 1rem;
    border-radius: 30px 0 0 30px;
    border: none;
    outline: none;
    font-size: 1rem;
  }

  .ask-button {
    padding: 1rem 2rem;
    border-radius: 0 30px 30px 0;
    border: none;
    background-color: #0056b3;
    color: white;
    font-size: 1rem;
    cursor: pointer;
  }

  .action-buttons {
    display: flex;
    justify-content: space-around;
    flex-wrap: wrap;
  }

  .action-button {
    background-color: white;
    color: #17a2b8;
    border: none;
    border-radius: 30px;
    padding: 0.8rem 1.6rem;
    margin: 0.5rem;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    font-size: 1.2rem;
  }

  .action-button i {
    margin-right: 0.5rem;
  }

  #video-section {
    text-align: center;
    background-color: #17a2b8;
    border-radius: 10px;
    width: 100%;
    margin: 2rem auto;
    color: white;
  }
</style>