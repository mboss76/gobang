<template>
<h5>用户名:{{username}}</h5>
</template>

<script>
import global from "@/js/global";
import {ref} from "vue";
export default {
  name: "Person",
  data(){
    return{
      username:global.username
    }
  }
}
</script>

<style scoped>

</style>