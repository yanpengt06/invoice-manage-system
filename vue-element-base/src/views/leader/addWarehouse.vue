<template>
<el-container> 
  <el-form ref="form" :model="rep" label-width="250px" style="margin:100px auto auto 400px">
    <el-form-item label="仓库类别(门店仓库/大库)">
      <el-input v-model="rep.classification" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="仓库地址">
      <el-input v-model="rep.address" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="仓库电话">
      <el-input v-model="rep.phone" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加仓库</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { addRep } from "@/api/rep";
export default {
  data() {
    return {
      rep: {
        classification: '大库',
        address: '西大直街92号',
        phone: '18912345678'
      },
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将添加一个仓库, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        addRep(this.rep).then((response) => {
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
