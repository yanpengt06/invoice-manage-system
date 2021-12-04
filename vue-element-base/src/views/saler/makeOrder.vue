<template>  
<el-form :model="dynamicValidateForm" ref="dynamicValidateForm" label-width="100px" class="demo-dynamic">
  <el-form-item
    v-for="(orderItem, index) in dynamicValidateForm.orderItems"
    :label="'第' + (index + 1) + '个商品ID'"
    :key="orderItem.key"
    :prop="'domains.' + index + '.value'"
  >
    <el-input v-model="orderItem.goodId" style="width: 200px"></el-input><el-button @click.prevent="removeOrderItem(orderItem)">删除</el-button>
  </el-form-item>
  <el-form-item
    v-for="(orderItem, index) in dynamicValidateForm.orderItems"
    :label="'第' + (index + 1) + '个商品数量'"
    :key="orderItem.key"
    :prop="'domains.' + index + '.value'"
  >
    <el-input v-model="orderItem.num" style="width: 200px"></el-input><el-button @click.prevent="removeOrderItem(orderItem)">删除</el-button>
  </el-form-item>
  <el-form-item>
    <el-button type="primary" @click="submitForm('dynamicValidateForm')">开单</el-button>
    <el-button type="primary" @click="handleSave()">保存</el-button>

    <el-button @click="addOrderItem">新增一类商品</el-button>
  </el-form-item>
</el-form>
</template>
<script>
  import {getOrderDetail, makeOrder,saveOrder} from '@/api/order' 
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
        }
      };
    },
    created(){
      if(this.$route.params && this.$route.params.id)
      {
          getOrderDetail(this.$route.params.id).then(response =>{
            this.dynamicValidateForm.orderItems = response.data.items;
          })
      }
    },
    methods: {
      submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            var id;
            if(this.$route.params.id)
            {
              id = this.$route.params.id;
            }
            else id = -1;
            makeOrder(this.dynamicValidateForm.orderItems,id).then(response =>{
              if(response.code == 20000)
                  alert('开单成功，已送审核！');
              else
              {
                alert('error!');
              }
            })
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      },
      removeOrderItem(item) {
        var index = this.dynamicValidateForm.orderItems.indexOf(item)
        if (index !== -1) {
          this.dynamicValidateForm.orderItems.splice(index, 1)
        }
      },
      addOrderItem() {
        var orderId;
        if(this.$route.params.id)
        {
          orderId = this.$route.params.id;
        }
        else
        {
          orderId = -1;
        }
        this.dynamicValidateForm.orderItems.push({
          num:0,
          goodId: 0,
          key: Date.now(),
          orderId: orderId,
          itemId:-1
        });
      },
      handleSave(){
        var id ;
        if(!this.$route.params.id)
        {
           id = -1;
        }
        else{
          id = this.$route.params.id;
        }
        saveOrder(this.dynamicValidateForm.orderItems,id).then(response =>{
          if(response.code == 20000)
            alert('保存成功')
          else
            alert('error!')
        })
      }
    }
  }
</script>