<template>
  <el-table :data="orders" style="width: 100%">
    <el-table-column prop="orderId" label="订单编号" width="360">
    </el-table-column>
    <el-table-column prop="time" label="开单时间" width="360">
    </el-table-column>
    <el-table-column  label="订单状态" width="360">
      <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.state == '已保存'">
              {{scope.row.state}}
          </el-tag>
          <el-tag type="warning" v-if="scope.row.state == '待审核'">
              {{scope.row.state}}
          </el-tag>
          <el-tag type="danger" v-if="scope.row.state == '待支付'">
              {{scope.row.state}}
          </el-tag>   
          <el-tag  v-if="scope.row.state == '待出库'">
              {{scope.row.state}}
          </el-tag>  
          <el-tag type="info"  v-if="scope.row.state == '已完成'">
              {{scope.row.state}}
          </el-tag>                                      
      </template>
    </el-table-column>
    <el-table-column prop="address" label="操作">
      <template slot-scope="scope">
        <el-button
          v-if="scope.row.state == '已保存'"
          size="mini"
          @click="handleModify(scope.row.orderId)">修改订单</el-button>          
        <el-button
          v-else
          size="mini"
          @click="handleQuery(scope.row.orderId)">查看详情</el-button>
      </template>        
    </el-table-column>
  </el-table>
</template>

  <script>
import {getAll} from '@/api/order' 
export default {
  data() {
    return {
      orders: [
        {
          orderId: 1,
          time: "2016-01-01",
          state: "已支付",
          customerId: 3,
          salerId: 9
        },
      ],
    };
  },
  created() {
      this.getAllOrders();
  },
  methods:{
    getAllOrders(){
        getAll().then(response =>{
          this.orders = response.data.items;
        })
    },
    handleQuery(id){
        this.$router.push("/order/detail/" + id)
    },
    handleModify(id){
        this.$router.push("/saler/makeOrder/" + id)       
    }
  }
};
</script>