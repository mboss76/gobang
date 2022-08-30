<template>
    <canvas id="gobang" ></canvas>
</template>

<script>
import {inject, onMounted} from "vue";

export default {
  name: "chessboard",
  setup(){
    const global=inject('global');

    // context上下文
    let ctx=null;
    // 棋子坐标
    const chessboard_arr=[];
    // 坐标转换参数
    const getPointOnCanvas=function(canvas, x, y) {

      let bbox =canvas.getBoundingClientRect();

      return{ x: x- bbox.left *(canvas.width / bbox.width),

        y:y - bbox.top  * (canvas.height / bbox.height)

      };
    }
    //鼠标点击事件
    const doMouseDown=function  (event){
      var x = event.pageX;
      var y = event.pageY;
      var canvas = event.target;
      var loc = getPointOnCanvas(canvas, x, y);
      if(loc.x<15||loc.y<15||loc.x>645||loc.y>645){
        console.log("超出边界");
        return
      }
      let xLine = Math.round((loc.x - 15) / 45); // 竖线第x条
      let yLine = Math.round((loc.y - 15) / 45); // 横线第y条
      console.log("mouse down at point( x:" + loc.x + ", y:" + loc.y + ")");
      console.log("横线",xLine,"竖线",yLine);
      drawChess(xLine,yLine);
      //started = true;
    }
    //画棋子
    function drawChess(xLine,yLine){
      let grd=ctx.createRadialGradient(
          xLine * 45 + 15,
          yLine * 45 + 15,
          4,
          xLine * 45 + 15,
          yLine * 45 + 15,
          10
      );
      grd.addColorStop(0, "#fff");
      ctx.beginPath();
      ctx.fillStyle=grd;
      ctx.arc(
          xLine * 45 + 15,
          yLine * 45 + 15,
          10,
          0,
          2 * Math.PI,
          false
      );
      ctx.fill();
      ctx.closePath();


    }
    // 挂载生命周期时间
    onMounted(()=>{
        // 画棋盘
      let container=document.getElementById("gobang");
      container.width=800;
      container.height=800;
      container.addEventListener("mousedown", doMouseDown,false);
      ctx=container.getContext("2d");
      //ctx.translate(70,70);
        ctx.beginPath();
        ctx.fillStyle = "#fff";
        //ctx.rect(0, 0, 50, 50);
        ctx.fill();
        ctx.stroke();
      for (var i = 0; i < 15; i++) {
        ctx.beginPath();
        ctx.strokeStyle = "#D6D1D1";
        ctx.moveTo(15 + i * 45, 15); //垂直方向画15根线，相距45px;
        ctx.lineTo(15 + i * 45, 645);
        ctx.stroke();
        ctx.moveTo(15, 15 + i * 45); //水平方向画15根线，相距45px;棋盘为14*14；
        ctx.lineTo(645, 15 + i * 45);
        ctx.stroke();
        chessboard_arr.push(new Array(15).fill(0));
      }
      console.log(chessboard_arr);
    });

    return{

    }
  }
}
</script>

<style scoped>

</style>