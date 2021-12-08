<template>
<el-container>
  <el-header>
  <h3 style="margin-left: 600px">
    从A库往B库调货
  </h3> 
  </el-header>
  <el-form ref="form"  label-width="250px" style="margin:100px auto auto 400px">
    <el-form-item label="A库id">
      <el-input v-model="repAid" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="B库id">
      <el-input v-model="repBid" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="货品id">
      <el-input v-model="goodId" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="调货数量">
      <el-input v-model="num" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">调度货品</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { deploy } from "@/api/rep";
export default {
  data() {
    return {
        repAid:5,
        repBid:6,
        goodId: 50,
        num :60
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将调度货品, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        deploy(this.repAid,this.repBid,this.goodId,this.num).then((response) => {
        if (response.code == 20000) {
          this.$message({
              type: "success",
              message: "调货成功"
          })
        }
        else{
            this.$message({
                type:"alert",
                message: "调货失败"
            })
        }
      });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消调货",
          });
        });        

    },
  },
};
</script>
