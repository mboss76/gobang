<template>
  <div>
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
            <a-radio :style="radioStyle" :value="true">先手</a-radio>
            <a-radio :style="radioStyle" :value="false">后手</a-radio>
          </a-radio-group>
        </div>
        <a-button v-on:click="createChessboard">确认创建</a-button>
      </div>
      <div v-show="page_num===3">
        <h1>加入页面</h1>
        <a-input  size="large" v-model:value="chessboard_num" placeholder="请输入房间号" />
        <a-button v-on:click="page_num=page_num+1">确认加入</a-button>
      </div>
      <div v-show="page_num===4">
        <chessboard></chessboard>
      </div>

    </div>
  </div>
</template>

<script>
import {reactive, ref, onMounted, inject} from 'vue'
import Chessboard from "@/components/chessboard";
export default {
  name: "Gobang",
  components: {Chessboard},
  setup(){

    const global=inject('global');
    const axios=inject('axios');

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
    const chessboard_num=ref(" ");
    chessboard_num.value='';
    //棋盘号
    let xianshouOrhoushou=ref(true);

    //创建棋盘函数
    function createChessboard(){
      const create_url=global.http_local_url+'gobang/createChessboard/'+global.username+'/'+xianshouOrhoushou.value;
      console.log(create_url)
      axios.get(create_url).then(res=>{
        const resData=res.data;
        console.log(resData);
        if(resData.code===0){
          chessboard_num.value=resData.data.chessboard_num;
        }
      })
    }

    return{
      page_num:ref(1),
      radioStyle,
      xianshouOrhoushou,
      chessboard_num,
      createChessboard,
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