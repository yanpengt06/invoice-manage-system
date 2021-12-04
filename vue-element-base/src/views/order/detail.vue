<template>
  <el-table :data="orderItems" style="width: 100%">
    <el-table-column prop="goodId" label="商品编号" width="180">
    </el-table-column>
    <el-table-column prop="name" label="商品名称" width="180">
    </el-table-column>
    <el-table-column prop="num" label="个数" width="180"> </el-table-column>
    <el-table-column prop="num" label="操作"> </el-table-column>
  </el-table>
</template>

<script>
import {getOrderDetail} from '@/api/order'
export default {
  data() {
    return {
      orderItems: [
        {
          itemId: 1,
          goodId: 3,
          num: 300,
          orderId:1
        }
      ]
    };
  },
  created(){
      getOrderDetail(this.$route.params.id).then(response => {
          this.orderItems = response.data.items;
      })
  }
};
</script>