<template>
  <el-table :data="customers" style="width: 100%">
    <el-table-column prop="id" label="客户ID" width="200"> </el-table-column>
    <el-table-column prop="name" label="客户姓名" width="200">
    </el-table-column>
    <el-table-column prop="checked" label="已支付款项" width="200"> </el-table-column>
      <el-table-column prop="tocheck" label="待支付款项" width="200"> </el-table-column>
    <el-table-column  label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleDetail(scope.row.id)">查看详情</el-button>
      </template>      
    </el-table-column>
  </el-table>
</template>

  <script>
import { fundStatistics } from '@/api/order';
export default {
  data() {
    return {
      customers: [
        {
            id: 8,
            name: "张三疯",
            checked:1000,
            tocheck:200
        }
      ],
    };
  },
  created(){
      fundStatistics().then(res =>{
          this.customers = res.data.items
      })
  },
  methods: {
      handleDetail(customerID) {
          this.$router.push("/leader/saleStatistics/" + customerID)
      }
  }
};
</script>