package com.proj.invoice.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proj.invoice.bean.*;
import com.proj.invoice.mapper.*;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Watchable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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

    @Autowired
    RepositoryItemMapper repositoryItemMapper;

    //获取所有销售单
    @GetMapping("/order/all")
    public R getAllOrder(){
        List<aOrder> list=orderMapper.selectList(null);
        R r=R.ok().data("items",list);
        return r;
    }

    //更新销售单状态
    @PostMapping("/order/updateState")
    public R updateState(@RequestParam long order_id,@RequestParam String newState){
        aOrder order=orderMapper.selectById(order_id);
        order.setState(newState);
        int i=orderMapper.updateById(order);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
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

    @PostMapping("/order/save")
    public R saveOrder(@RequestBody Map<String,Object> map){
        long id=(long)(int) map.get("id");
        /*ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> list = mapper.convertValue((List<OrderItem>)map.get("orderItems"), new TypeReference<List<OrderItem>>() { });*/
        String json= JSON.toJSONString((List<OrderItem>)map.get("orderItems"));
        List<OrderItem> list = JSONObject.parseArray(json, OrderItem.class);
        //long customerId=(long)(int)map.get("customerId");
        //LocalDateTime time= (LocalDateTime) map.get("time");
        double profit=0;
        double total=0;
        if(id==-1){
            aOrder aOrder=new aOrder();
            aOrder.setState("未提交");
            //aOrder.setCustomerId(customerId);
            //aOrder.setTime(time);

            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                    OrderItem orderItem= it.next();
                    orderItemMapper.insert(orderItem);
                    Good good=goodMapper.selectById(orderItem.getGoodId());
                    total+=good.getRetailPrice();
                    profit+=good.getRetailPrice()-good.getInputPrice();
            }
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.insert(aOrder);
        }else{
            aOrder aOrder=orderMapper.selectById(id);
            orderItemMapper.delete(new QueryWrapper<OrderItem>().eq("order_id",id));
            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getRetailPrice();
                profit+=good.getRetailPrice()-good.getInputPrice();
            }
            //aOrder.setTime(time);
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.updateById(aOrder);
        }
        return R.ok();
    }

    @PostMapping("/order/makeOrder")
    public R makeOrder(@RequestBody Map<String,Object> map){
        long id=(long)(int) map.get("id");
        /*ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> list = mapper.convertValue((List<OrderItem>)map.get("orderItems"), new TypeReference<List<OrderItem>>() { });*/
        String json= JSON.toJSONString((List<OrderItem>)map.get("orderItems"));
        List<OrderItem> list = JSONObject.parseArray(json, OrderItem.class);
        //long customerId=(long)(int)map.get("customerId");
        //LocalDateTime time= (LocalDateTime) map.get("time");
        double profit=0;
        double total=0;
        System.out.println(id);
        System.out.println(list);
        if(id==-1){
            aOrder aOrder=new aOrder();
            aOrder.setState("未审核");
            //aOrder.setCustomerId(customerId);
            //aOrder.setTime(time);

            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getRetailPrice();
                profit+=good.getRetailPrice()-good.getInputPrice();
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",0).eq("good_id",good.getId()));
                repositoryItem.setNum(repositoryItem.getNum()-orderItem.getNum());
                repositoryItemMapper.updateById(repositoryItem);
            }
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.insert(aOrder);
        }else{
            aOrder aOrder=orderMapper.selectById(id);
            orderItemMapper.delete(new QueryWrapper<OrderItem>().eq("order_id",id));
            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getRetailPrice();
                profit+=good.getRetailPrice()-good.getInputPrice();
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",0).eq("good_id",good.getId()));
                repositoryItem.setNum(repositoryItem.getNum()-orderItem.getNum());
                repositoryItemMapper.updateById(repositoryItem);
            }
            //aOrder.setTime(time);
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.updateById(aOrder);
        }
        return R.ok();
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
        return itemService.del(id,new aOrder());
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
        return itemService.search(id,new aOrder());
    }

    @RequestMapping("/order/searchByCustomerId")
    public R searchByCustomerId(@RequestParam long id){
        return itemService.search(id,"customer_id",new aOrder());
    }

    @RequestMapping("/order/searchByState")
    public R searchByState(@RequestParam String state){
        return itemService.search(state,"state",new aOrder());
    }

    @RequestMapping("/order/all")
    public R all(){
        return itemService.all(new aOrder());
    }
}
