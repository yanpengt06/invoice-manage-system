<template>
  <div>
    <div style="margin: 20px"></div>
    <div class="form">
      <el-form :label-position="left" label-width="150px" :model="good">
        <el-form-item label="需要盘点的货品ID">
          <el-input v-model="good[0].id" style="width: 300px"></el-input>
        </el-form-item>
        <el-form-item label="需要盘点的仓库ID">
          <el-input v-model="rep.repositoryId" style="width: 300px"></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="button">
      <el-button type="primary" @click="queryGoodInf()">盘点</el-button>
    </div>
    <div id="table">
      <el-table :data="good" style="width: 100%">
        <el-table-column prop="id" label="商品id" width="360">
        </el-table-column>
        <el-table-column prop="name" label="商品名" width="360">
        </el-table-column>
        <el-table-column prop="description" label="商品描述" width="360">
        </el-table-column>
      </el-table>
      <h4>库存：{{stock}}</h4>
    </div>
    <div class="form">
      <el-form :label-position="left" label-width="100px" :model="good">
        <el-form-item label="盘点后数量">
          <el-input v-model="stock" style="width: 250px"></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="button">
      <el-button type="primary" @click="handleEdit()">修改</el-button>
    </div>
  </div>
</template>
<script>
import { queryGoodById, getGoodStock } from "@/api/good";
import {modifyStock} from '@/api/rep'
export default {
  data() {
    return {
      good: [
        {
          name: "",
          id: 2,
          desc: "",
        },
      ],
      rep: {
        repositoryId: 5,
      },
      stock: 200,
    };
  },
  methods: {
    queryGoodInf() {
      queryGoodById(this.good[0].id).then((response) => {
        this.good = [response.data.item];
      });
      getGoodStock(this.rep.repositoryId, this.good[0].id).then((response) => {
        this.stock = response.data.stock;
      });
    },
    handleEdit(){
        modifyStock(this.rep.repositoryId,this.good[0].id,this.stock).then(response =>{
            if(response.code == 20000)
            {
              this.$message({
                type:"success",
                message: "修改库存成功"
              })
            }
        })
    }
  },
};
</script>

<style scoped>
.form {
  margin-left: 400px;
}
.button {
  margin-left: 600px;
}
#table {
  margin: 50px auto 50px auto;
}
</style>