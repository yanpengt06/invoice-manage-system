<template>
  <el-table :data="goods" style="width: 100%">
    <el-table-column prop="id" label="货品ID" width="360"> </el-table-column>
    <el-table-column prop="name" label="货品名称" width="360">
    </el-table-column>
    <el-table-column prop="totalStock" label="库存" width="360"> </el-table-column>
    <el-table-column  label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handleQuery(scope.row.id)">查看详情</el-button>
      </template>      
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllGoods } from '@/api/good';
export default {
  data() {
    return {
      goods: [
        {
          id: 5,
          name: "钢笔",
          totalStock: 500
        }
      ],
    };
  },
  created(){
    getAllGoods().then(response =>{
      this.goods = response.data.items;
    })
  },
  methods: {
    handleQuery(id){
        this.$router.push('/good/detail/' + id);
    }
  }
};
</script>