import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css'
import global from './js/global';
import axios from 'axios';
import {routes} from './js/router'
import {createRouter,createWebHashHistory}from 'vue-router'
const app=createApp(App);
app.provide('global',global);
app.provide('axios',axios);
//antd组件库
app.use(Antd);
//路由
const router = createRouter({
    history:createWebHashHistory(),
    routes
})
app.use(router)
app.mount('#app');
