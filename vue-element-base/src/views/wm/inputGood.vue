<template>
  <el-form ref="form"  label-width="120px">
    <el-form-item label="商品id">
      <el-input v-model="goodId" style="width: 250px"></el-input>
    </el-form-item>

    <el-form-item label="进货数量">
      <el-input v-model="num" style="width: 200px"></el-input>
    </el-form-item>

    <el-form-item label="仓库id">
      <el-input v-model="repId" style="width: 200px"></el-input>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="onSubmit">进货</el-button>
    </el-form-item>
  </el-form>
</template>

<script lang="ts">
import {input} from '@/api/rep'
export default {
  data() {
    return {
        goodId: 6,
        num: 50,
        repId: 5

    };
  },
  methods: {
    onSubmit() {
      input(this.goodId,this.num,this.repId).then((response) => {
        if (response.code == 20000) {
          this.$message({
              type: "success",
              message: "进货成功"
          })
        }
        else{
            this.$message({
                type:"alert",
                message: "进货失败"
            })
        }
      });
    }
  },
};
</script>
