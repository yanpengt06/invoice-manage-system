<template>
  <el-container>
    <el-header>
      <el-form
        :label-position="left"
        label-width="160px"
        style="margin-left:400px"
      >
        <el-form-item label="请输入仓库ID：">
          <el-input v-model="repId" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <el-button
        type="primary"
        @click="handleSearch()"
        style="margin-left: 550px"
        >搜索
      </el-button>
    </el-header>
    <el-main style="margin-top : 50px">
      <el-table :data="goods" style="width: 100%">
        <el-table-column prop="id" label="货品ID" width="360">
        </el-table-column>
        <el-table-column prop="name" label="货品名称" width="360">
        </el-table-column>
        <el-table-column prop="totalNum" label="库存" width="360">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleQuery(scope.row.id)"
              >查看详情</el-button
            >
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

  <script>
import { getAllGoods } from "@/api/good";
import {getGoodsByRepId} from '@/api/rep'
export default {
  data() {
    return {
      goods: [
        {
          id: 5,
          name: "钢笔",
          totalNum: 500,
        },
      ],
      repId: 55
    };
  },
  created() {
    getAllGoods().then((response) => {
      this.goods = response.data.items;
    });
  },
  methods: {
    handleQuery(id) {
      this.$router.push("/good/detail/" + id);
    },
    handleSearch() {
      getGoodsByRepId(this.repId).then(response =>{
        if(response.code == 20000)
        {
          this.goods = response.data.items;
          this.$message({
            type: 'success',
            message: '查询成功'
          })
        }
        else
        {
          this.$message({
            type: 'error',
            message: '查询失败！'
          })
        }
      })
    }
  },
};
</script>