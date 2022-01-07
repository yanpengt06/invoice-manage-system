<template>
  <el-container>
    <el-form
      ref="form"
      :model="customer"
      label-width="250px"
      style="margin: 100px auto auto 300px"
      :rules="rules"
      @keyup.enter.native="onSubmit"
    >
      <el-form-item label="顾客姓名">
        <el-input v-model="customer.name" style="width: 250px" placeholder="请输入顾客姓名"></el-input>
      </el-form-item>
      <el-form-item label="顾客电话" prop="phone" >
        <el-input
          v-model="customer.phone"
          style="width: 200px"
          @keyup.capture.enter="onSubmit"
          placeholder="请输入顾客电话"
        ></el-input>
      </el-form-item>
      <el-form-item label="送货地址">
        <el-input v-model="customer.address" style="width: 200px" placeholder="请输入送货地址"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="onSubmit" @>添加顾客</el-button>
      </el-form-item>
    </el-form>
    <el-main> </el-main>
  </el-container>
</template>

<script lang="ts">
import { addCustomer } from "@/api/customer";
export default {
  data() {
    var validatePhone = (rule, value, callback) => {
      if (value === "") {
        callback(new Error("电话为空"));
      } else 
      {
        var length = value.length;
        if (
          length == 11 &&
          /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(14[0-9]{1})|)+\d{8})$/.test(
            value
          )
        )
        {
          callback();
        }
        else
        {
          callback(new Error("电话号码格式非法"))
        }
        
      }
    };
    return {
      customer: {
        name: "",
        address: "",
        phone: "",
      },
      rules: {
        phone: [{ validator: validatePhone, trigger: "blur" }],
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
                message: "添加成功",
              });
            } else {
              this.$message({
                type: "alert",
                message: "添加失败",
              });
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
