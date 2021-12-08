<template>
<el-container> 
  <el-form ref="form" :model="rep" label-width="250px" style="margin:100px auto auto 400px">
    <el-form-item label="员工姓名">
      <el-input v-model="eply.name" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="员工性别">
      <el-input v-model="eply.sex" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="员工年龄">
      <el-input v-model="eply.age" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="员工电话">
      <el-input v-model="eply.phone" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="员工账号">
      <el-input v-model="eply.account" style="width: 200px"></el-input>
    </el-form-item>    
    <el-form-item label="员工职位">
      <el-input v-model="eply.position" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加员工</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { addEply } from "@/api/employee";
export default {
  data() {
    return {
      eply: {
        name: '张三',
        age:45,
        sex:'男',
        position:'收银员',
        address: '西大直街92号',
        account: '12166',
        phone: '18912345678'
      },
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将添加一个员工, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        addEply(this.eply).then((response) => {
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
