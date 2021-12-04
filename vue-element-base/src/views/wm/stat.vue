<template>
    <el-table
      :data="tableData"
      style="width: 100%">
      <el-table-column
        prop="date"
        label="货品ID"
        width="360">
      </el-table-column>
      <el-table-column
        prop="name"
        label="货品名称"
        width="360">
      </el-table-column>
      <el-table-column
        prop="name"
        label="库存"
        width="360">
      </el-table-column>
      <el-table-column
        prop="name"
        label="操作"
       >
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