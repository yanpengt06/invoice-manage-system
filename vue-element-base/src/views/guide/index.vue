<template>
  <div class="app-container">
    <aside>
      <!-- The guide page is useful for some people who entered the project for the first time. You can briefly introduce the
      features of the project. Demo is based on -->
      操作指南：1. 在左侧下拉列表中选择想要使用的功能，也可通过右上角搜索框搜索  <br> 2. xxxxxx <br> 3.xxxxx     
      <!-- <a href="https://github.com/kamranahmedse/driver.js" target="_blank">driver.js.</a> -->
    </aside>
    <el-button icon="el-icon-question" type="primary" @click.prevent.stop="guide">
      下载
    </el-button>
  </div>
</template>

<script>
import Driver from 'driver.js' // import driver.js
import 'driver.js/dist/driver.min.css' // import driver.js css
import steps from './steps'

export default {
  name: 'Guide',
  data() {
    return {
      driver: null
    }
  },
  mounted() {
    this.driver = new Driver()
  },
  methods: {
    guide() {
      this.driver.defineSteps(steps)
      this.driver.start()
    }
  }
}
</script>
