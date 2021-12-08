<template>
  <el-table :data="customers" style="width: 100%">
    <el-table-column prop="id" label="客户ID" width="180"> </el-table-column>
    <el-table-column prop="name" label="客户姓名" width="180"> </el-table-column>
      <el-table-column prop="phone" label="电话" width="180"> </el-table-column>
      <el-table-column prop="address" label="送货地址" width="180"> </el-table-column>           
    <el-table-column  label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handledelete(scope.row.id)">删除</el-button>
      </template>      
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllCustomers } from '@/api/customer';
export default {
  data() {
    return {
      customers: [
        {
          id: 5,
          name: "张三",
          phone: '18912345689',
          address: '西大直街90#'
        }
      ],
    };
  },
  created(){
    getAllCustomers().then(response =>{
      this.customers = response.data.items;
    })
  },
  methods: {
    handleQuery(id){
        this.$router.push('/good/detail/' + id);
    }
  }
};
</script>