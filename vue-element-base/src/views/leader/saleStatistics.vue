<template>
  <el-container>
    <el-aside width="600px">
      <h3 align="center">客户销售状况统计</h3>
      请输入待统计用户的ID：<el-input
        v-model="customerID"
        clearable
        style="width: 100px"
      >
      </el-input>
      <el-button type="primary" @click="handleCustm()" style="margin-left: 50px">统计</el-button>
      <el-table :data="goods" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180"> </el-table-column>
        <el-table-column prop="name" label="名称" width="180">
        </el-table-column>
        <el-table-column prop="num" label="个数"> </el-table-column>
      </el-table>
    </el-aside>
    <el-main>
      <h3 align="center">货品销售状况统计</h3>
      请输入待统计货品的ID：<el-input
        v-model="goodID"
        clearable
        style="width: 100px"
      >
      </el-input>
      <el-button type="primary" @click="handleGood()" style="margin-left: 50px"
        >统计
      </el-button>
      <el-table :data="customers" style="width: 100%">
        <el-table-column prop="id" label="ID" width="180"> </el-table-column>
        <el-table-column prop="name" label="客户姓名" width="180">
        </el-table-column>
        <el-table-column prop="num" label="个数"> </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>
<script>
import {custmStatistics,goodStatistics} from '@/api/order'
export default {
  data() {
    return {
      customerID: 5,
      goods: [
        {
          id: 9,
          name: "铅笔",
          num: 1000,
        },
      ],
      goodID: 9,
      customers: [
          {
              id: 8,
              name: "张益达",
              num: 60
          }
      ]
    };
  },
  created() {
    if(this.$route.params.id)
    {
      this.customerID = this.$route.params.id
    }
  },
  methods: {
    handleCustm() {
        custmStatistics(this.customerID).then(response =>{
          this.goods = response.data.items;
        })
    },
    handleGood() {
        goodStatistics(this.goodID).then(res =>{
          this.customers = res.data.items;
        })
    }
  },
};
</script>