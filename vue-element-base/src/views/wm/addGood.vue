<template>
  <el-form ref="form" :model="good" label-width="120px">
    <el-form-item label="商品名">
      <el-input v-model="good.name" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="商品进价">
      <el-input v-model="good.inputPrice" style="width: 200px"></el-input>
    </el-form-item>

    <el-form-item label="商品简介">
      <el-input
        v-model="good.description"
        :autosize="{ minRows: 4, maxRows: 8 }"
        type="textarea"
      >
      </el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="onSubmit">添加商品</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts">
import { add } from "@/api/good";
export default {
  data() {
    return {
      good: {
        name: "",
        description: "",
        inputPrice: 50,
      },
    };
  },
  methods: {
    onSubmit() {
      add(this.good).then((response) => {
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
    },
    open() {
      this.$alert("更改成功", "标题名称", {
        confirmButtonText: "确定",
        callback: (action) => {
          this.$message({
            type: "info",
            message: `action: ${action}`,
          });
        },
      });
    },
  },
};
</script>
