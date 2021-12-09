<template>
<div>
  <el-table :data="orderItems" style="width: 100%">
    <el-table-column prop="goodId" label="商品编号" width="180">
    </el-table-column>
    <el-table-column label="商品名称" width="180">
      <template slot-scope="scope">
        <id-to-name :goodId="scope.row.goodId">
        </id-to-name>
      </template>
    </el-table-column>
    <el-table-column prop="num" label="个数" width="180"> </el-table-column>
    <el-table-column prop="num" label="操作">
      <el-button size="mini" @click="handleQuery(scope.row.goodId)"
        >查看详情
      </el-button>
    </el-table-column>
  </el-table>
<div id="total">
    <h3>总价：{{order.total}}</h3>
</div>

</div>
</template>

<script>
import { getOrderDetail,getOrderById } from "@/api/order";
import { queryGoodById } from "@/api/good";
import idToName from '@/components/idToName.vue';
export default {
  components: { idToName },
  data() {
    return {
      orderItems: [
        // {
        //   itemId: 1,
        //   goodId: 3,
        //   num: 300,
        //   orderId: 1,
        // },
      ],
      order:{
        orderId:5,
        total: 100
      },
      name : 'a'
    };
  },
  created() {
    getOrderDetail(this.$route.params.id).then((response) => {
      this.orderItems = response.data.items;
    });
    getOrderById(this.$route.params.id).then(response => {
      this.order = response.data.item;
    })
  },
  methods: {
    handleQuery(goodId) {
      this.$router.push();
    },
     getGoodName(id){
       queryGoodById(id).then(response =>{
           this.name = response.data.item.name;       
      })
    },
  },
};
</script>