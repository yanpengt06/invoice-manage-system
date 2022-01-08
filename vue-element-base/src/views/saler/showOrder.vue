<template>
  <el-container style="min-height: 100vh">
    <el-table
      :data="orders.slice((currentPage - 1) * pagesize, currentPage * pagesize)"
      style="width: 100%"
      @sort-change="onSortChange"
    >
      <el-table-column prop="orderId" label="订单编号" width="180">
      </el-table-column>
      <el-table-column
        prop="time"
        label="开单时间"
        width="300"
        sortable="custom"
      >
      </el-table-column>
      <el-table-column label="客户ID" width="180" prop="customerId">
      </el-table-column>
      <el-table-column label="订单状态" width="180">
        <template slot-scope="scope">
          <el-tag type="success" v-if="scope.row.state == '已保存'">
            {{ scope.row.state }}
          </el-tag>
          <el-tag type="warning" v-if="scope.row.state == '待审核'">
            {{ scope.row.state }}
          </el-tag>
          <el-tag type="danger" v-if="scope.row.state == '待支付'">
            {{ scope.row.state }}
          </el-tag>
          <el-tag v-if="scope.row.state == '待出库'">
            {{ scope.row.state }}
          </el-tag>
          <el-tag v-if="scope.row.state == '已完成'">
            {{ scope.row.state }}
          </el-tag>
          <el-tag type="info" v-if="scope.row.state == '已退货'">
            {{ scope.row.state }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column  align="center">
        <template slot="header" slot-scope="scope">
          <el-input
            v-model="search"
            size="mini"
            placeholder="输入订单号搜索"
            style="width: 150px"
            @keyup.enter.native="searchByOrderId"
          />
          <el-button @click="searchByOrderId" style="margin-left:10px" size="small"> 搜索</el-button>
        </template>
        <template slot-scope="scope">
          <el-button
            v-if="scope.row.state == '已保存'"
            size="mini"
            @click="handleModify(scope.row.orderId)"
            >修改订单</el-button
          >
          <el-button v-else size="mini" @click="handleQuery(scope.row.orderId)"
            >查看详情</el-button
          >
        </template>
      </el-table-column>
    </el-table>
    <el-footer>
      <div class="block" style="text-align: center">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 40]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="orders.length"
        >
        </el-pagination>
      </div>
    </el-footer>
  </el-container>
</template>

  <script>
import { getAll, getOrderById } from "@/api/order";
import { getCustmById } from "@/api/customer";
import { getAllGoods } from '@/api/good';
export default {
  data() {
    return {
      orders: [
        {
          orderId: 1,
          time: "2016-01-01",
          state: "已支付",
          customerId: 4,
          salerId: 9,
        },
      ],
      currentPage: 1, //初始页
      pagesize: 10, //    每页的数据
      search: '',
    };
  },
  created() {
    this.getAllOrders();
  },
  methods: {
    getAllOrders() {
      getAll().then((response) => {
        this.orders = response.data.items;
        this.orders = this.orders.sort(function (a, b) {
          return Date.parse(b.time) - Date.parse(a.time);
        });
        // this.orders = this.orders.reverse
      });
    },
    handleQuery(id) {
      this.$router.push("/order/detail/" + id);
    },
    handleModify(id) {
      this.$router.push("/order/makeOrder/" + id);
    },
    handleSizeChange(size) {
      this.pagesize = size;
      console.log(this.pagesize); //每页下拉显示数据
    },
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      console.log(this.currentPage); //点击第几页
    },
    onSortChange({ order, prop }) {
      if (order == "ascending" && prop == "time") {
        this.orders = this.orders.sort(function (a, b) {
          return Date.parse(a.time) - Date.parse(b.time);
        });
      } else if (order == "descending" && prop == "time") {
        //曾经这里多了一个空格 <--!>
        this.orders = this.orders.sort(function (a, b) {
          return Date.parse(b.time) - Date.parse(a.time);
        });
      }
    },
    // async custmIdToName(id) {      转换name很麻烦
    //   var name;
    //   name = await new Promise((resolve, reject) => {
    //     getCustmById(id).then((res) => {
    //       resolve(res.data.item.name);
    //     });
    //   });
    //   console.log(name);
    //   return name;
    // },
    // getName(id) {
    //   console.log(this.custmIdToName(id).then((result) => {
    //     return result;
    //   }));
    // },
    searchByOrderId() {
      if(this.search == "")
      {
        this.getAllOrders()
      }
      else{
      getOrderById(this.search).then(res =>{
        this.orders = [res.data.item]
        console.log(this.orders)
      })
      }
    }
  },
};
</script>