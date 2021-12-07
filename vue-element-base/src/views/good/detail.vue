<template>
  <el-table :data="goods" style="width: 100%">
    <el-table-column prop="id" label="商品ID" width="180">
    </el-table-column>
    <el-table-column prop="name" label="商品名称" width="180">
    </el-table-column>
    <el-table-column prop="description" label="描述" width="180"> </el-table-column>
    <el-table-column prop="inputPrice" label="进价" width="180"> </el-table-column>
    <el-table-column prop="retailPrice" label="零售价" width="180"> </el-table-column>
    <el-table-column prop="wholesalePrice" label="批发价" width="180"> </el-table-column>

  </el-table>
</template>

<script>
import { queryGoodById } from "@/api/good";
export default {
  data() {
    return {
      goods: [
        {
          name: '铅笔',
          id: 3,
          description: '这是一根铅笔',
          inputPrice: 50,
          retailPrice: 60,
          wholesalePrice:70
        },
      ],
    };
  },
  created() {
    queryGoodById(this.$route.params.id).then(response =>{
      this.goods = [response.data.item]
    })
  },
  methods: {
    handleQuery(goodId) {
      this.$router.push('')
    },
  },
};
</script>