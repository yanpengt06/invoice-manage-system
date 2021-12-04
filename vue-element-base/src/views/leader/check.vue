<template>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="订单编号"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="销售员"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="开单时间"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="客户"
        width="180">
      </el-table-column>
      <el-table-column
        prop="name"
        label="操作"
        width="180">
      </el-table-column>
      <el-table-column
        prop="address"
        label="审核">
      </el-table-column>      
    </el-table>
  </template>

  <script>
    export default {
      data() {
        return {
          tableData: [{
            date: '2016-05-02',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1518 弄'
          }, {
            date: '2016-05-04',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1517 弄'
          }, {
            date: '2016-05-01',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1519 弄'
          }, {
            date: '2016-05-03',
            name: '王小虎',
            address: '上海市普陀区金沙江路 1516 弄'
          }]
        }
      }
    }
  </script>