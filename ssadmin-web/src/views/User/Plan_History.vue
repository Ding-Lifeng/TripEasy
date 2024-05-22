<template>
  <div id="history-plans">
    <h1>历史规划</h1>
    <div class="cards-container">
      <div class="card" v-for="plan in plans" :key="plan.id">
        <div class="card-content">
          <h2>规划时间: {{ plan.date }}</h2>
          <p>{{ plan.summary }}</p>
          <button @click="showDetails(plan)">查看详细信息</button>
        </div>
      </div>
    </div>
    <el-dialog :visible.sync="popup" width="75%">
      <component :is="currentComponent" :details="currentDetails"></component>
    </el-dialog>
  </div>
</template>

<script>
import 'element-ui/lib/theme-chalk/index.css';
import { Dialog } from 'element-ui';

export default {
  name: 'HistoryPlans',
  components: {
    'el-dialog': Dialog,
  },
  data() {
    return {
      plans: [
        { id: 1, date: '2024-01-01', summary: '昆明三天两晚游' },
        { id: 2, date: '2024-02-15', summary: '大理两天一晚游' },
        // 可以根据需求添加更多历史规划
      ],
      popup: false,
      currentComponent: null,
      currentDetails: {},
    };
  },
  methods: {
    async showDetails(plan) {
      const component = await import('./Side_Page/Planned_Trip.vue');
      this.currentComponent = component.default;
      this.currentDetails = plan;
      this.popup = true;
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
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 5px;
  background-color: #17a2b8;
  color: white;
  cursor: pointer;
}
</style>
