import { createApp } from 'vue'
import App from './App.vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/antd.css'
import global from './js/global';
import axios from 'axios';
import {routes} from './js/router'
import {createRouter,createWebHashHistory}from 'vue-router'
import VueCookies from 'vue-cookies';

axios.defaults.headers.post['Access-Control-Allow-Origin'] = 'http://localhost:8080';
const app=createApp(App);
app.provide('global',global);
app.provide('axios',axios);
axios.defaults.withCredentials = true
//antd组件库
app.use(Antd);
//cookie
app.provide('cookies',VueCookies);
//路由
const router = createRouter({
    history:createWebHashHistory(),
    routes
})

app.use(router)
app.provide('router',router);
app.mount('#app');
