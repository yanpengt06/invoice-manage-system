<template>
  <div :class="className" :style="{ height: height, width: width }" />
</template>

<script>
import echarts from "echarts";
require("echarts/theme/macarons"); // echarts theme
import resize from "./mixins/resize";
import { countReps } from "@/api/rep";

export default {
  mixins: [resize],
  props: {
    className: {
      type: String,
      default: "chart",
    },
    width: {
      type: String,
      default: "100%",
    },
    height: {
      type: String,
      default: "300px",
    },
  },
  created() {},
  data() {
    return {
      chart: null,
      reps: {
        1: 200,
      },
      dataSrc: [
        {
          value: 5,
          name: "1",
        },
      ],
    };
  },
  mounted() {
    this.$nextTick(() => {
      this.initChart();
    });
  },
  beforeDestroy() {
    if (!this.chart) {
      return;
    }
    this.chart.dispose();
    this.chart = null;
  },
  methods: {
    async initChart() {
      this.chart = echarts.init(this.$el, "macarons");
      await countReps().then((res) => {
        this.reps = res.data.items; // reps: {"1":2,"2":3,...}
        var dataSrc = [];
        var i = 0;
        for (var key in this.reps) {
          var rep = {};
          rep["name"] = key;
          rep["value"] = this.reps[key];
          dataSrc[i] = rep;
          i++;
        }
        this.dataSrc = dataSrc;
      });
      this.chart.setOption({
        tooltip: {
          trigger: "item",
          formatter: "{a} <br/>{b} : {c} ({d}%)",
        },
        legend: {
          left: "center",
          bottom: "10",
          data: Object.keys(this.reps),
        },
        series: [
          {
            name: "库存分布展示",
            type: "pie",
            roseType: "radius",
            radius: [15, 95],
            center: ["50%", "38%"],
            // data: [
            //   { value: 320, name: 'Industries' },
            //   { value: 240, name: 'Technology' },
            //   { value: 149, name: 'Forex' },
            //   { value: 100, name: 'Gold' },
            //   { value: 59, name: 'Forecasts' }
            // ],
            data: this.dataSrc,
            animationEasing: "cubicInOut",
            animationDuration: 2600,
          },
        ],
      });
    },
  },
};
</script>
