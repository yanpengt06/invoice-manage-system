<template>
  <div>
    <div style="margin: 20px"></div>
    <div class="form">
      <el-form
        :label-position="labelPosition"
        label-width="150px"
        :model="formLabelAlign"
      >
        <el-form-item label="需要盘点的货品ID">
          <el-input
            v-model="formLabelAlign.name"
            style="width: 500px"
          ></el-input>
        </el-form-item>
      </el-form>
    </div>
    <div class="button">
      <el-button type="primary">盘点</el-button>
    </div>
    <div id="table">
    <el-table :data="tableData" style="width: 100%">
      <el-table-column prop="date" label="商品id" width="360">
      </el-table-column>
      <el-table-column prop="name" label="商品名" width="360">
      </el-table-column>
      <el-table-column prop="address" label="库存"> </el-table-column>
    </el-table>
    </div>
    <div class="form">
    <el-form
      :label-position="labelPosition"
      label-width="100px"
      :model="formLabelAlign"
    >
      <el-form-item label="盘点后数量">
        <el-input v-model="formLabelAlign.region" style="width:250px"></el-input>
      </el-form-item>
    </el-form>
    </div>
        <div class="button">
      <el-button type="primary">修改</el-button>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      labelPosition: "left",
      formLabelAlign: {
        name: "",
        region: "",
        type: "",
      },
      tableData: [
        {
          date: "2016-05-02",
          name: "王小虎",
          address: "上海市普陀区金沙江路 1518 弄",
        },
      ],
    };
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
  margin : 50px auto 50px auto;
}
</style>