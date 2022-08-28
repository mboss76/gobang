<template>
  <div id="form">
    <a-form
        :model="formState"
        name="basic"
        :label-col="{ span: 8 }"
        :wrapper-col="{ span: 16 }"
        autocomplete="off"
        @finish="onFinish"
        @finishFailed="onFinishFailed"
    >
      <a-form-item
          label="Username"
          name="username"
          :rules="[{ required: true, message: 'Please input your username!' }]"
      >
        <a-input v-model:value="formState.username" />
      </a-form-item>
      <a-form-item
          label="Password"
          name="password"
          :rules="[{ required: true,message: 'Please input your password'}]"
      >
        <a-input-password v-model:value="formState.password" />
      </a-form-item>

      <a-form-item name="remember" :wrapper-col="{ offset: 8, span: 16 }">
        <a-checkbox v-model:checked="formState.remember">Remember me</a-checkbox>
      </a-form-item>

      <a-form-item :wrapper-col="{ offset: 8, span: 16 }">
        <a-button type="primary" html-type="submit">Login</a-button>
      </a-form-item>
    </a-form>
  </div>
</template>
<script>
import {  reactive,inject } from 'vue';
export default({
  name:"Login",
  setup() {
    const global=inject('global');
    const  axios=inject('axios');
    const formState = reactive({
      username: '',
      password: '',
      remember: true,
    });

    const onFinish = values => {

      const url=global.http_local_url+'log/login/'+formState.username+'/'+formState.password+'/'+formState.remember;
      axios.get(url)
          .then(res=>{
        console.log('请求成功',res.data);
      }).catch(
          function (error){
            console.log(error)
          }
      )

    };

    const onFinishFailed = errorInfo => {
      console.log('Failed:', errorInfo);
    };
    return {
      formState,
      onFinish,
      onFinishFailed,
    };
  },

});
</script>
<style>
#before{
  width: 33%;
  height: 100%;
  display: inline-block;
}
#form{
  margin-top: 10%;
  margin-left: 33%;
  display: inline-block;
}
#after{
  width: 33%;
  height: 100%;
  display: inline-block;
}
</style>