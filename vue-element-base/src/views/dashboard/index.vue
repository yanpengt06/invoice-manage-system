<template>
  <div class="dashboard-container">

    <el-col :xs="{span: 24}" :sm="{span: 12}" :md="{span: 12}" :lg="{span: 6}" :xl="{span: 6}" style="margin-top:0px;">    
        <box-card />
    </el-col>
    <div id="text" style="text-align:center">
      <div class="dashboard-text">你好: {{ name }}</div>
      <div class="dashboard-text">欢迎使用进销存管理系统！</div>    
    </div>
    <panel-group @handleSetLineChartData="handleSetLineChartData" style="margin: 50px auto auto auto"/>    
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import BoxCard from "./admin/components/BoxCard";
import PanelGroup from './admin/components/PanelGroup'

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["name"]),
  },
  components: {
    BoxCard,
    PanelGroup,
  },
};
</script>

<style lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>