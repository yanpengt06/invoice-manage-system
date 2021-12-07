<template>
  <el-table :data="orders" style="width: 100%">
    <el-table-column prop="orderId" label="订单编号" width="180">
    </el-table-column>
    <el-table-column prop="salerId" label="开单销售员编号" width="180">
    </el-table-column>
    <el-table-column prop="time" label="开单时间" width="180">
    </el-table-column>
    <el-table-column prop="customerId" label="客户id" width="180">
    </el-table-column>
    <el-table-column label="操作" width="180">
      <template slot-scope="scope">
        <el-button size="mini" @click="handleQuery(scope.row.orderId)"
          >查看详情
        </el-button>
      </template>
    </el-table-column>
    <el-table-column label="审核">
      <template slot-scope="scope">
        <el-button size="mini" @click="handlePass(scope.row.orderId)"
          >通过</el-button
        >
        <el-button
          size="mini"
          type="danger"
          @click="handleRefuse(scope.row.orderId)"
          >不通过</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllOrderToCheck, passOrder ,refuseOrder} from "@/api/order";
import { SUCCESS } from "dropzone";
export default {
  data() {
    return {
      orders: [
        {
          orderId: 5,
          // state: "待审核",
          time: "",
          customerId: 123,
          salerId: 5,
        },
      ],
    };
  },
  created() {
    getAllOrderToCheck().then((response) => {
      this.orders = response.data.items;
    });
  },
  methods: {
    handleQuery(id) {
      console.log("aa");
      this.$router.push("/order/detail/" + id);
    },
    handlePass(orderId) {
      passOrder(orderId).then((response) => {
        if (response.code == 20000) {
          this.$message({
            type: "success",
            message: "已通过审核",
          });
        } else {
          this.$message({
            type: "info",
            message: "通过失败",
          });
        }
      });
    },
    handleRefuse(orderId) {
      refuseOrder(orderId).then((response) => {
        if (response.code == 20000) {
          this.$message({
            type: "success",
            message: "已拒绝",
          });
        } else {
          this.$message({
            type: "info",
            message: "拒绝失败",
          });
        }
      });
    },
  },
};
</script>