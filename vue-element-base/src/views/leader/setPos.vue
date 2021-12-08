<template>
<el-container> 
  <el-form ref="form" :model="employee" label-width="120px" style="margin:100px auto auto 500px">
    <el-form-item label="员工Id">
      <el-input v-model="employee.id" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="员工职位">
      <el-input v-model="employee.position" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">设定权限</el-button>
    </el-form-item>
  </el-form>
<el-main>

</el-main>
</el-container>
</template>

<script lang="ts">
import { modify } from "@/api/employee";
export default {
  data() {
    return {
      employee: {
        id: 5,
        position: "销售员"
      },
    };
  },
  methods: {
    onSubmit() {
      this.$confirm("此操作将修改职位, 您确定吗?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
        modify(this.employee).then((response) => {
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
            message: "已取消设置",
          });
        });        

    },
  },
};
</script>
