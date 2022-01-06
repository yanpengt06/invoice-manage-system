<template>
  <el-container>
    <el-aside width="500px">
      <el-form
        :model="dynamicValidateForm"
        ref="dynamicValidateForm"
        label-width="100px"
        class="demo-dynamic"
      >
        <el-form-item
          v-for="(orderItem, index) in dynamicValidateForm.orderItems"
          :label="'第' + (index + 1) + '个商品ID'"
          :key="orderItem.key"
          :prop="'domains.' + index + '.value'"
        >
          <el-input v-model="orderItem.goodId" style="width: 200px"></el-input
          ><el-button @click.prevent="removeOrderItem(orderItem)"
            >删除</el-button
          >
        </el-form-item>
        <el-form-item
          v-for="(orderItem, index) in dynamicValidateForm.orderItems"
          :label="'第' + (index + 1) + '个商品数量'"
          :key="orderItem.key"
          :prop="'domains.' + index + '.value'"
        >
          <el-input v-model="orderItem.num" style="width: 200px"></el-input
          ><el-button @click.prevent="removeOrderItem(orderItem)"
            >删除</el-button
          >
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('dynamicValidateForm')"
            >开单</el-button
          >
          <el-button type="primary" @click="handleSave()">保存</el-button>

          <el-button @click="addOrderItem">新增一类商品</el-button>
        </el-form-item>
      </el-form>
    </el-aside>

    <el-main>
      <el-form ref="form" label-width="250px" style="">
        <el-form-item label="请输入批发顾客的id">
          <el-input v-model="customerId" style="width: 250px"></el-input>
        </el-form-item>
      </el-form>
      <el-form
        :label-position="labelPosition"
        label-width="160px"
        :model="formLabelAlign"
        @keyup.enter.native="handleSearch"
      >
        <el-form-item label="请输入商品名称：">
          <el-input v-model="goodName" style="width: 200px"></el-input>
        </el-form-item>
      </el-form>
      <el-button
        type="primary"
        @click="handleSearch()"
        style="margin-left: 200px"
        >搜索</el-button
      >
      <el-table :data="goods" style="width: 100%">
        <el-table-column prop="id" label="商品id" width="180">
        </el-table-column>
        <el-table-column prop="name" label="商品名称" width="180">
        </el-table-column>
        <el-table-column prop="description" label="商品描述" width="180">
        </el-table-column>
        <el-table-column label="操作">
          <template slot-scope="scope">
            <el-button
              type="success"
              icon="el-icon-circle-plus-outline"
              circle
              @click="addOrderItem(scope.row.id)"
            ></el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>
<script>
import { getOrderDetail, makeOrder, saveOrder } from "@/api/order";
import { searchGoodsByName } from "@/api/good";
export default {
  data() {
    return {
      dynamicValidateForm: {
        orderItems: [
          //   {
          //   goodId: 4,
          //   num: 40,
          //   orderId: 1,
          //   itemId: 5,
          //   key: date()
          // }
        ],
      },
      customerId: -1,
      goodName: "商品名称",
      goods: [
        {
          id: 5,
          name: "钢笔-蓝",
          description: "aaa",
        },
      ],
            

    };
  },
  created() {
    if (this.$route.params && this.$route.params.id) {
      getOrderDetail(this.$route.params.id).then((response) => {
        this.dynamicValidateForm.orderItems = response.data.items;
      });
    }
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          var id;
          if (this.$route.params.id) {
            id = this.$route.params.id;
          } else id = "-1";
          makeOrder(
            this.dynamicValidateForm.orderItems,
            id,
            this.customerId
          ).then((response) => {
            if (response.code == 20000)
            {
                this.$message({
                  type:"success",
                  message:"开单成功，已送审核!"
                })
            }
            else {
              alert("error!");
            }
          });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    removeOrderItem(item) {
      var index = this.dynamicValidateForm.orderItems.indexOf(item);
      if (index !== -1) {
        this.dynamicValidateForm.orderItems.splice(index, 1);
      }
    },
    addOrderItem(id) {
      var orderId;
      if (this.$route.params.id) {
        orderId = this.$route.params.id;
      } else {
        orderId = -1;
      }
      this.dynamicValidateForm.orderItems.push({
        num: 0,
        goodId: id,
        key: Date.now(),
        orderId: orderId,
        itemId: 0,
      });
    },
    handleSave() {
      var id;
      if (!this.$route.params.id) {
        id = "-1";
      } else {
        id = this.$route.params.id;
      }
      saveOrder(this.dynamicValidateForm.orderItems, id, this.customerId).then(
        (response) => {
          if (response.code == 20000) alert("保存成功");
          else alert("error!");
        }
      );
    },
    handleSearch() {
      searchGoodsByName(this.goodName).then((response) => {
        this.goods = response.data.items;
      });
    },    
  },
};
</script>