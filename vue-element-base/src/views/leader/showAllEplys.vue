<template>
  <el-table :data="eplys" style="width: 100%">
    <el-table-column prop="id" label="员工ID" width="180"> </el-table-column>
    <el-table-column prop="position" label="员工职位" width="180">
    </el-table-column>
    <el-table-column prop="name" label="员工姓名" width="180">
    </el-table-column>
    <el-table-column prop="phone" label="电话" width="180"> </el-table-column>
    <el-table-column prop="age" label="年龄" width="180"> </el-table-column>
    <el-table-column prop="sex" label="性别" width="180"> </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        <el-button size="mini" @click="handledelete(scope.row.id)"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>
</template>

  <script>
import { getAllEplys, delEmply} from "@/api/employee";
export default {
  data() {
    return {
      eplys: [
        {
          id: 5,
          name: "张三",
          position: "销售员",
          phone: "18912345689",
          sex: "男",
          age: 45,
        },
      ],
    };
  },
  created() {
    getAllEplys().then((response) => {
      this.eplys = response.data.items;
    });
  },
  methods: {
    handleQuery(id) {
      this.$router.push("/good/detail/" + id);
    },
    handledelete(EmplyId) {
      this.$confirm("此操作将删除该员工及其相关账号, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delEmply(EmplyId).then((res) => {
            if (res.code == 20000) 
            {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              getAllEplys().then(res =>{
                this.eplys = res.data.items
              })
            }
            else
            {
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
    },
  },
};
</script>