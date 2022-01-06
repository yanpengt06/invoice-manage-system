<template>
  <el-table :data="reps" style="width: 100%">
    <el-table-column prop="repositoryId" label="仓库ID" width="180"> </el-table-column>
    <el-table-column prop="classification" label="仓库类别" width="180">
    </el-table-column>
    <el-table-column prop="address" label="地址" width="360"> </el-table-column>
      <el-table-column prop="phone" label="电话" width="360"> </el-table-column>
    <el-table-column  label="操作">
      <template slot-scope="scope">
        <el-button
          size="mini"
          @click="handledelete(scope.row.repositoryId)">删除</el-button>
      </template>      
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllReps, delRep} from '@/api/rep';
export default {
  data() {
    return {
      reps: [
        {
          repositoryId: 5,
          classification: "门店仓库",
          address: '西大直街92#',
          phone: '18912345689'
        }
      ],
    };
  },
  created(){
    getAllReps().then(response =>{
      this.reps = response.data.items;
    })
  },
  methods: {
    handleQuery(id){
        this.$router.push('/good/detail/' + id);
    },
    handledelete(RepId) {
      this.$confirm("此操作将永久删除该仓库, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delRep(RepId).then((res) => {
            if (res.code == 20000) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              getAllReps().then(res =>{
                this.reps = res.data.items
              })
            } 
            else {
              this.$message({
                type: "error",
                message: "删除失败!",
              });
            }
          });
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });      
    }
  }
};
</script>