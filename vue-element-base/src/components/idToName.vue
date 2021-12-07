<template>
  <div>
    {{ name }}
  </div>
</template>
<script>
import { queryGoodById } from '@/api/good';

export default {
  data() {
    return {
      name: null,
      id: this.goodId,
    };
  },
  methods: {
    getGoodName() {
        queryGoodById(this.id).then(response =>{
            this.name = response.data.item.name;
        })
    },
  },
  watch: {},
  mounted: function () {
    console.log(this.id);
    this.getGoodName();
  },
  props: {
    goodId: String,
    required: true,
  },
};
</script>
 
<style>
</style>