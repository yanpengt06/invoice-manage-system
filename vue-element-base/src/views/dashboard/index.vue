<template>
  <div class="dashboard-container">
    <github-corner class="github-corner" />
    <div id="text" style="text-align: center">
      <div class="dashboard-text">您好: {{ roles[0] }} {{ name }}</div>
      <div class="dashboard-text">欢迎使用进销存管理系统！</div>
    </div>
    <panel-group
      @handleSetLineChartData="handleSetLineChartData"
      style="margin: 50px auto auto auto"
    />
    <el-col
      :xs="{ span: 24 }"
      :sm="{ span: 12 }"
      :md="{ span: 12 }"
      :lg="{ span: 6 }"
      :xl="{ span: 6 }"
      style="margin-top: 0px"
    >
      <box-card />
    </el-col>  
    <el-col :xs="24" :sm="24" :lg="8" style="margin-left: 100px">
      <div class="chart-wrapper" >
        <pie-chart />
      </div>
    </el-col>      
  </div>
</template>

<script>
import { mapGetters } from "vuex";
import BoxCard from "./admin/components/BoxCard";
import PanelGroup from "./admin/components/PanelGroup";
import GithubCorner from "@/components/GithubCorner";
import PieChart from './admin/components/PieChart'

export default {
  name: "Dashboard",
  computed: {
    ...mapGetters(["name"]),
    ...mapGetters(["roles"]),
  },
  components: {
    BoxCard,
    PanelGroup,
    GithubCorner,
    PieChart
  },
  data() {
    return {
      // roles: ['店长']
    };
  },
  created() {},
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

.component-item {
  min-height: 100px;
}

.github-corner {
  position: absolute;
  top: 0px;
  border: 0;
  right: 0;
}
.chart-wrapper {
  background: #fff;
  padding: 16px 16px 0;
  margin-bottom: 32px;
}

@media (max-width: 1024px) {
  .chart-wrapper {
    padding: 8px;
  }
}
</style>