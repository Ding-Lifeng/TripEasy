<template>
  <div id="scenic-spots">
    <h1>游玩推荐</h1>
    <div class="card-container">
      <div class="card" v-for="spot in spots" :key="spot.id">
        <img :src="getImagePath(spot.image)" :alt="spot.name" class="card-image">
        <div class="card-text">
          <h2>{{ spot.name }}</h2>
          <p>{{ spot.description }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'ScenicSpots',
  data() {
    return {
      spots: [
        {
          id: 1,
          name: '石林风景区',
          image: '石林.jpg',
          description: '石林风景区是中国著名的风景区，以其奇特的石灰岩地貌闻名。'
        },
        {
          id: 2,
          name: '滇池',
          image: '滇池.jpg',
          description: '滇池是云南省最大的淡水湖，风景秀丽，是鸟类的天堂。'
        },
        {
          id: 3,
          name: '米线',
          image: '米线.png',
          description: '云南米线种类繁多，包括小锅米线、豆花米线、过桥米线、凉米线。'
        },
        {
          id: 4,
          name: '西山森林公园',
          image: '西山.png',
          description: '西山森林公园位于昆明市区西南，是一个集自然景观和人文景观为一体的风景区。'
        },
        {
          id: 5,
          name: '翠湖公园',
          image: '翠湖.png',
          description: '翠湖公园位于昆明市中心，是一个市民休闲娱乐的好去处。'
        }
      ]
    };
  },
  methods: {
    getImagePath(image) {
      return require(`../../assets/${image}`);
    }
  }
};
</script>

<style scoped>
  #scenic-spots {
    text-align: center;
    background-color: #17a2b8;
    padding: 2rem;
    border-radius: 10px;
    width: 80%;
    margin: 2rem auto;
    color: white;
  }

  h1 {
    font-size: 2.5rem;
    font-weight: bold;
    margin: 0;
  }

  .card-container {
    display: flex;
    overflow-x: auto;
    padding: 1rem;
    gap: 1rem;
  }

  .card {
    background-color: white;
    color: #17a2b8;
    border-radius: 10px;
    overflow: hidden;
    min-width: 250px;
    max-width: 300px;
    flex: 0 0 auto;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
  }

  .card-image {
    width: 100%;
    height: 150px;
    object-fit: cover;
  }

  .card-text {
    padding: 1rem;
  }

  .card-text h2 {
    font-size: 1.5rem;
    margin: 0.5rem 0;
  }

  .card-text p {
    font-size: 1rem;
    margin: 0;
  }
</style>