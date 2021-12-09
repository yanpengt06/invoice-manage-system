<template>
  <el-table :data="orders" style="width: 100%">
    <el-table-column prop="orderId" label="订单编号" width="180">
    </el-table-column>
    <el-table-column prop="time" label="开单时间" width="180">
    </el-table-column>
    <el-table-column label="订单状态" width="180">
      <el-tag type="danger"> 未出库 </el-tag>
    </el-table-column>
    <el-table-column prop="name" label="操作" width="180">
      <template slot-scope="scope">
        <el-button size="mini" @click="handleQuery(scope.row.orderId)"
          >查看详情</el-button
        >
      </template>
    </el-table-column>
    <el-table-column prop="name" label="出库">
      <template slot-scope="scope">
        <el-button
          size="mini"
          type="success"
          @click="handleAlc(scope.row.orderId)"
          >配货完成
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllUnAlcedOrder, alcOrder } from "@/api/order";
export default {
  data() {
    return {
      orders: [
        {
          orderId: 5,
          state: "待出库",
          time: "2021-01-05",
        },
      ],
    };
  },
  created() {
    getAllUnAlcedOrder().then((response) => {
      this.orders = response.data.items;
    });
  },
  methods: {
    handleQuery(id) {
      this.$router.push("/order/detail/" + id);
    },
    handleAlc(id) {
      alcOrder(id).then((response) => {
        this.$message({
          type: "success",
          message: "配货完成，订单已完成",
        });
        getAllUnAlcedOrder().then((response) => {
          this.orders = response.data.items;
        });
      });
    },
  },
};
</script>