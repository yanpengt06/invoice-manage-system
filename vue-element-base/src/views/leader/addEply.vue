<template>
<el-container> 
  <el-main>
  <el-form ref="form" :model="rep" label-width="250px" style="margin:100px auto auto 300px">
    <el-form-item label="员工姓名">
      <el-input v-model="eply.name" style="width: 250px" placeholder="请输入员工姓名"></el-input>
    </el-form-item>
    <el-form-item label="员工性别">
      <!-- <el-input v-model="eply.sex" style="width: 200px"></el-input> -->
      <el-radio v-model="eply.sex" label="男">男</el-radio>
      <el-radio v-model="eply.sex" label="女">女</el-radio>  
    </el-form-item>
    <el-form-item label="员工年龄">
      <el-input v-model="eply.age" style="width: 200px" placeholder="请输入员工年龄"></el-input>
    </el-form-item>
    <el-form-item label="员工电话">
      <el-input v-model="eply.phone" style="width: 200px" placeholder="请输入员工电话"></el-input>
    </el-form-item>  
    <el-form-item label="员工职位">
      <el-input v-model="eply.position" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加员工</el-button>
    </el-form-item>
  </el-form>
  </el-main>
  <el-footer>
  <h3>员工初始账号：{{this.acc.account}}</h3>
  <h3>员工初始密码：{{this.acc.pwd}}</h3>
  </el-footer>
</el-container>
</template>

<script lang="ts">
import { addEply } from "@/api/employee";
export default {
  data() {
    return {
      eply: {
        name: '',
        age:'',
        sex:'女',
        position:'销售员',
        address: '',
        phone: ''
      },
      acc:{
        account: 0,
        pwd: 111111
      }
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
          this.acc = response.data.item;
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
