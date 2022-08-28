<template class="template">
  <a-layout id="main">
    <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible>
      <div class="logo" />
      <a-menu v-model:selectedKeys="selectedKeys" theme="dark" mode="inline">
        <a-menu-item key="1" >
          <user-outlined />
          <span>个人信息</span>
          <router-link to="/person">
          </router-link>
        </a-menu-item>
        <a-menu-item key="2">
          <video-camera-outlined />
            <span>五子棋</span>
          <router-link to="/gobang"></router-link>
        </a-menu-item>
        <a-menu-item key="3">
          <upload-outlined />
            <span>德州扑克</span>
          <router-link to="/texasPoke">
          </router-link>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header style="background: #fff; padding: 0" id="fold">
        <menu-unfold-outlined
            v-if="collapsed"
            class="trigger"
            @click="() => (collapsed = !collapsed)"
        />
        <menu-fold-outlined v-else class="trigger" @click="() => (collapsed = !collapsed)" />
      </a-layout-header>
      <a-layout-content
          :style="{ margin: '24px 16px', padding: '24px', background: '#fff', minHeight: '280px' }"
      >
        <!-- 登录 ， 五子棋， 德州扑克-->
        <router-view></router-view>
      </a-layout-content>
    </a-layout>
  </a-layout>
</template>
<script>
import { UserOutlined, VideoCameraOutlined, UploadOutlined, MenuUnfoldOutlined, MenuFoldOutlined } from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';
import Login from "@/components/Login";
import Gobang from "@/components/Gobang";
import TexasPoke from "@/components/TexasPoke";
import Person from "@/components/Person";
export default defineComponent({
  components: {
    Person,
    TexasPoke,
    Gobang,
    Login,
    UserOutlined,
    VideoCameraOutlined,
    UploadOutlined,
    MenuUnfoldOutlined,
    MenuFoldOutlined,
  },

  setup() {
    return {
      selectedKeys: ref(['1']),
      collapsed: ref(false),
    };
  },

  data(){
    return{
      page_num:0
    }
  },
  methods:{
    changePage(pageNum){
      this.page_num=pageNum;
    }
  }

});
</script>
<style>
.template{
  height: 100%;
}
#main{
  height: 100%;
}
#fold .trigger{
  margin-left: 25px;
}
#components-layout-demo-custom-trigger .trigger {
  font-size: 18px;
  line-height: 64px;
  padding: 0 24px;
  cursor: pointer;
  transition: color 0.3s;
}

#components-layout-demo-custom-trigger .trigger:hover {
  color: #1890ff;
}

.logo {
  height: 32px;
  background: rgba(255, 255, 255, 0.3);
  margin: 16px;
}

.site-layout .site-layout-background {
  background: #fff;
}


</style>