<template>
  <div>
    <div style="margin: 20px"></div>
    <div id="form">
      <el-form
        :label-position="labelPosition"
        label-width="80px"
        :model="formLabelAlign"
        :rules="rules"
      >
        <el-form-item label="退货订单号" prop="orderId">
          <el-input
            v-model="formLabelAlign.orderId"
            style="width: 500px"
          ></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div id="button">
      <el-button type="primary" @click="open()">整单退货</el-button>
    </div>
  </div>
</template>
<script>
import { returnOrder } from "@/api/order";
export default {
  data() {
    var validateID = (rule, value, callback) => {
        if(!value)
        {
          callback(new Error("订单号不能为空"))
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
    return {
      labelPosition: "top",
      formLabelAlign: {
        orderId: 5,
      },
      rules : {
          orderId:[{validator: validateID, trigger: "blur"}]
      }
    };
  },
  methods: {
    handleReturn() {
      returnOrder(this.formLabelAlign.orderId).then((response) => {
        if (response.code == 20000)
          this.$message({
            type: "success",
            message: "退货成功!"
          });
        else {
          this.$message({
            type: "info",
            message: "退货失败！"
          })
        }
      });
    },
    open() {
      this.$confirm("此操作将整单退货, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.handleReturn();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消退货",
          });
        });
    },
  },
};
</script>

<style scoped>
#form {
  margin-left: 500px;
}
#button {
  margin-left: 600px;
}
</style>