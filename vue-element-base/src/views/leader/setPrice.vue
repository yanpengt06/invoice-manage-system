<template>
<el-container> 
  <el-form ref="form" :model="good" label-width="120px" style="margin:100px auto auto 500px" :rules="rules">
    <el-form-item label="商品Id" prop="id">
      <el-input v-model="good.id" style="width: 250px"></el-input>
    </el-form-item>
    <el-form-item label="商品零售价" prop="retailPrice">
      <el-input v-model="good.retailPrice" style="width: 200px"></el-input>
    </el-form-item>
    <el-form-item label="商品批发价" prop="wholesalePrice"> 
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
    var validateInt = (rule, value, callback) => {
        if(!value)
        {
          callback(new Error("此处不能为空"))
        }
        else if(!(/(^[1-9]\d*$)/.test(value)))
        {
          callback(new Error("ID值非法"))
        }
        else
        {
          callback()
        }
    };  
    var validateNum = (rule, value, callback) => {
        if(!value)
        {
          callback(new Error("此处不能为空"))
        }
        else if(isNaN(value))
        {
          callback(new Error("不是一个合法数字"))
        }
        else
        {
          callback()
        }
    };      
    return {
      good: {
        id: 5,
        retailPrice: 60,
        wholesalePrice: 55
      },
      rules: {
        id: [{validator: validateInt, trigger: "blur"}],
        retailPrice: [{validator: validateNum, trigger: "blur"}],
        wholesalePrice: [{validator: validateNum, trigger: "blur"}],
      }
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
        modify(this.good.id,this.good.retailPrice,this.good.wholesalePrice).then((response) => {
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
