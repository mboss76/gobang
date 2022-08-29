<template>
  <div id="pre_zhanwei"></div>
  <div id="content">
    <div v-show="page_num<=3">
      <a-button v-on:click="page_num=2">创建棋盘</a-button>
      <a-button v-on:click="page_num=3">加入棋盘</a-button>
    </div>
    <div v-show="page_num===2">
      <h1>创建页面</h1>
      <div>
        <a-radio-group v-model:value="xianshouOrhoushou">
          <a-radio :style="radioStyle" :value="1">Option A</a-radio>
          <a-radio :style="radioStyle" :value="2">Option B</a-radio>
        </a-radio-group>
      </div>
      <a-button >确认创建</a-button>
    </div>
    <div v-show="page_num===3">
      <h1>加入页面</h1>
      <a-input  size="large" v-model:value="room_num" placeholder="请输入房间号" />
      <a-button>确认加入</a-button>
    </div>
    <div v-show="page_num===4">
      <h5>棋盘页面</h5>
    </div>

  </div>

</template>

<script>
import {reactive, ref} from 'vue'
export default {
  name: "addGobang",
  setup(){
    /*
    * page_num:
    * 1 选择创建or加入
    * 2 创建页面
    * 3 加入页面
    * 4 棋盘页面
    * */
    const radioStyle = reactive({
      display: 'flex',
      height: '30px',
      lineHeight: '30px',
    });

    const room_num=ref(" ");
    room_num.value='';
    return{
      page_num:ref(1),
      radioStyle,
      xianshouOrhoushou:ref(1),
      room_num,
    }
  }
}
</script>

<style scoped>
#pre_zhanwei{
  display: inline-block;
  width: 33%;
}
#content{
  display: inline-block;
}
#content .ant-input {
  width: 200px;
  margin: 0 8px 8px 0;
}
</style>