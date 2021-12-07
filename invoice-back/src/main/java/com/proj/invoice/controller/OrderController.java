package com.proj.invoice.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.*;
import com.proj.invoice.mapper.GoodMapper;
import com.proj.invoice.mapper.OrderItemMapper;
import com.proj.invoice.mapper.OrderMapper;
import com.proj.invoice.mapper.UserMapper;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Watchable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class OrderController {
    @Autowired
    OrderMapper orderMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    GoodMapper goodMapper;

    @GetMapping("/order/checkout")
    public R checkout(@RequestParam int id)
    {
        System.out.println(id);
        return R.ok();
    }

    //获取所有销售单
    @GetMapping("/order/all")
    public R getAllOrder(){
//        List<aOrder> list=orderMapper.selectList(null);
        List<aOrder> list = new ArrayList<>();
        list.add(new aOrder(1,"未支付", LocalDateTime.now(),3,300,9,800));
        list.add(new aOrder(4,"已保存", LocalDateTime.now(),3,500,9,900));
        list.add(new aOrder(5,"待审核", LocalDateTime.now(),3,300,9,900));
        R r=R.ok().data("items",list);
        return r;
    }

    // TODO: 2021/12/7 模拟超市收银结账 
    @RequestMapping("/order/cash")
    public R cash(@RequestBody List<OrderItem> list)
    {
        System.out.println(list.get(0));
        return R.ok();
    }


    // TODO: 2021/12/4 参数格式： {id: xxx, orderItems: list} 没有订单号，id = -1; orderItems内orderid同前，itemId都是-1
    // orderItem多一个属性key，前端控制按钮使用，前端不好去掉。。。这里处理一下呗

    @PostMapping("/order/makeOrder")
    public R makeOrder(@RequestBody Map<String,Object> map){
//        List<aOrder> list=orderMapper.selectList(null);
        System.out.println(map);
        List<OrderItem> list = (List<OrderItem>)map.get("orderItems");
        System.out.println(list);
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        return R.ok();
    }

    // TODO: 2021/12/4 参数格式：{id:xxx,orderItems:list}  若没有订单号，id=-1
    
    @PostMapping("/order/save")
    public R saveOrder(@RequestBody Map<String,Object> map){
        System.out.println(map);
//        List<aOrder> list=orderMapper.selectList(null);
        List<OrderItem> list = (List<OrderItem>)map.get("orderItems");
        System.out.println(list);
        return R.ok();
    }



    //更新销售单状态
    @PostMapping("/order/updateState")
    public R updateState(@RequestParam long order_id,@RequestParam String newState){
//        aOrder order=orderMapper.selectById(order_id);
//        order.setState(newState);
//        int i=orderMapper.updateById(order);
//        if(i>0){
//            return R.ok();
//        }else{
//            return R.error();
//        }
        System.out.println(order_id);
        System.out.println(newState);
        return R.ok();
    }

    //
    @GetMapping("/order/getGoodsById")
    public R getGoodsById(@RequestParam long order_id){
        QueryWrapper<OrderItem> wrapper=new QueryWrapper<>();
        wrapper.eq("order_id",order_id);
        List<OrderItem> items=orderItemMapper.selectList(wrapper);
        List<Good> goods=new ArrayList<>();
        for(OrderItem orderItem:items){
            Good good=goodMapper.selectById(orderItem.getGoodId());
            goods.add(good);
        }
        return R.ok().data("items",goods);
    }


    //上面的是之前写的，所以可能会有点乱
    @Autowired
    @Qualifier("ItemService")
    ItemService<aOrder> itemService;

    @RequestMapping("/order/add")
    public R add(@RequestBody aOrder aOrder){
        return itemService.add(aOrder);
    }

    @RequestMapping("/order/delById")
    public R del(@RequestParam long id){
//        return itemService.del(id,new aOrder());
        System.out.println(id);
        return R.ok();
    }

    @RequestMapping("/order/delByCustomerId")
    public R delByCustomerId(@RequestParam long id){
        return itemService.del(id,"customer_id",new aOrder());
    }

    @RequestMapping("/order/delByState")
    public R delByState(@RequestParam String state){
        return itemService.del(state,"state",new aOrder());
    }

    @RequestMapping("/order/update")
    public R update(@RequestBody aOrder aOrder){
        return itemService.update(aOrder);
    }

    @RequestMapping("/order/searchById")
    public R search(@RequestParam long id){

//        return itemService.search(id,new aOrder());
        return R.ok().data("item",new aOrder(5,"待支付",LocalDateTime.now(),5,600,8,1000));
    }

    @RequestMapping("/order/searchByCustomerId")
    public R searchByCustomerId(@RequestParam long id){
        return itemService.search(id,"customer_id",new aOrder());
    }

    @RequestMapping("/order/searchByState")
    public R searchByState(@RequestParam String state){
        return itemService.search(state,"state",new aOrder());
    }

//    @RequestMapping("/order/all")
//    public R all(){
//        return itemService.all(new aOrder());
//    }
}
