<template>
  <div class="app-container">
    <aside>
      <!-- The guide page is useful for some people who entered the project for the first time. You can briefly introduce the
      features of the project. Demo is based on -->
      操作指南：1. 在左侧下拉列表中选择想要使用的功能，也可通过右上角搜索框搜索
      <br />
      2. xxxxxx <br />
      3.xxxxx
      <br />
      用户手册请点击右侧<span style="font-weight: bold">下载</span>按钮进行下载
      <!-- <a href="https://github.com/kamranahmedse/driver.js" target="_blank">driver.js.</a> -->
      <!-- 下载数据模板 -->
      <el-button
        @click="download"
        class="el-button-color add-button-box"
        size="medium"
        type="primary"
        style="margin:auto 50px auto 100px"
      >
        <i class="el-icon-download el-icon--right"></i>用户手册
      </el-button>
      点击右侧按钮查看界面操作指南
      <el-button
        icon="el-icon-question"
        type="primary"
        style="margin-left: 100px"
        @click.prevent.stop="guide"
      >
        操作指南
      </el-button>
    </aside>
  </div>
</template>

<script>
import Driver from "driver.js"; // import driver.js
import "driver.js/dist/driver.min.css"; // import driver.js css
import steps from "./steps";

export default {
  name: "Guide",
  data() {
    return {
      driver: null,
    };
  },
  mounted() {
    this.driver = new Driver();
  },
  methods: {
    guide() {
      this.driver.defineSteps(steps);
      this.driver.start();
    },

    download() {
      window.location.href = "http://localhost:8080/UserBook.docx";
    },
  },
};
</script>
