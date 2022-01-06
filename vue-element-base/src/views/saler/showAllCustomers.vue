<template>
  <el-table :data="customers" style="width: 100%">
    <el-table-column prop="id" label="客户ID" width="180"> </el-table-column>
    <el-table-column prop="name" label="客户姓名" width="180">
    </el-table-column>
    <el-table-column prop="phone" label="电话" width="180"> </el-table-column>
    <el-table-column prop="address" label="送货地址" width="180">
    </el-table-column>
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
import { getAllCustomers ,delCustm} from "@/api/customer";
export default {
  data() {
    return {
      customers: [
        {
          id: 5,
          name: "张三",
          phone: "18912345689",
          address: "西大直街90#",
        },
      ],
    };
  },
  created() {
    getAllCustomers().then((response) => {
      this.customers = response.data.items;
    });
  },
  methods: {
    handleQuery(id) {
      this.$router.push("/good/detail/" + id);
    },
    handledelete(custmID) {
      this.$confirm("此操作将永久删除该客户, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          delCustm(custmID).then((res) => {
            if (res.code == 20000) {
              this.$message({
                type: "success",
                message: "删除成功!",
              });
              getAllCustomers().then(res =>{
                this.customers = res.data.items
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
    },
  },
};
</script>