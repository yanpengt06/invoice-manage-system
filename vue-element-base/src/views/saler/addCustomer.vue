<template>
<el-container> 
  <el-form ref="form" :model="customer" label-width="250px" style="margin:100px auto auto 400px">
    <el-form-item label="顾客姓名">
      <el-input v-model="customer.name" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="顾客电话">
      <el-input v-model="customer.phone" style="width: 200px"></el-input>
    </el-form-item>    
    <el-form-item label="送货地址">
      <el-input v-model="customer.address" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加顾客</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { addCustomer } from "@/api/customer";
export default {
  data() {
    return {
      customer: {
        name: '张三',
        address: '西大直街92号',
        phone: '18912345678'
      },
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将添加一个顾客, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        addCustomer(this.customer).then((response) => {
        if (response.code == 20000) {
          this.$message({
              type: "success",
              message: "添加成功"
          })
        }
        else{
            this.$message({
                type:"alert",
                message: "添加失败"
            })
        }
      });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消添加",
          });
        });        

    },
  },
};
</script>
