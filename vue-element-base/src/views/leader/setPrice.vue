<template>
<el-container> 
  <el-form ref="form" :model="good" label-width="120px" style="margin:100px auto auto 500px">
    <el-form-item label="商品Id">
      <el-input v-model="good.id" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="商品零售价">
      <el-input v-model="good.retailPrice" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="商品批发价">
      <el-input v-model="good.wholesalePrice" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">设定价格</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { modify } from "@/api/good";
export default {
  data() {
    return {
      good: {
        id: 5,
        retailPrice: 60,
        wholesalePrice: 55
      },
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将修改价格, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        modify(this.good).then((response) => {
        if (response.code == 20000) {
          this.$message({
              type: "success",
              message: "设置成功"
          })
        }
        else{
            this.$message({
                type:"alert",
                message: "设置失败"
            })
        }
      });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消修改",
          });
        });        

    },
  },
};
</script>
