<template>
  <el-table :data="orders" style="width: 100%">
    <el-table-column prop="orderId" label="订单编号" width="180">
    </el-table-column>
    <el-table-column prop="time" label="提交时间" width="180">
    </el-table-column>
    <el-table-column prop="name" label="收款状态" width="180">
      <el-tag :type="primary"> 未支付 </el-tag>
    </el-table-column>
    <el-table-column label="操作" width="180">
      <template slot-scope="scope">
        <el-button size="mini" @click="handleQuery(scope.row.orderId)"
          >查看详情
        </el-button>
      </template>
    </el-table-column>
    <el-table-column label="收银">
      <template slot-scope="scope">      
      <el-button
        size="mini"
        type="success"
        @click="handleCheckout(scope.row.orderId)"
        >确认收款
      </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import { getAllOrderToCheck, orderCheckout, getAllOrderToCheckout } from "@/api/order";
export default {
  data() {
    return {
      orders: [
        {
          orderId: 5,
          time: "asdfasdf",
        },
      ],
    };
  },
  created() {
    getAllOrderToCheckout().then(response =>{
      this.orders = response.data.items;
    })
  },
  methods: {
    handleQuery(id) {
      this.$router.push("/order/detail/" + id);
    },
    handleCheckout(id) {
      this.$confirm("此操作将确认收款, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          orderCheckout(id).then((response) => {
            if (response.code == 20000) {
              this.$message({
                type: "success",
                message: "收款成功!",
              });
            } else {
              this.$message({
                type: "info",
                message: "收款失败",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消收款",
          });
        });
    },
  },
};
</script>