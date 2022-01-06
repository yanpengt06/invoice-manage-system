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


    // TODO: 2021/12/9 /order/cash 给orderItems，拿去开单，直接变成待支付，但是不用减库存，把总价和利润算好，返回一个order放item里面
    @RequestMapping("/order/cash")
    public R cash(@RequestBody Map<String,Object> map){
        /*ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> list = mapper.convertValue((List<OrderItem>)map.get("orderItems"), new TypeReference<List<OrderItem>>() { });*/
        String json= JSON.toJSONString((List<OrderItem>)map.get("orderItems"));
        List<OrderItem> list = JSONObject.parseArray(json, OrderItem.class);
        //long customerId=Integer.parseInt((String)map.get("customerId"));
        LocalDateTime time= LocalDateTime.now();
        double profit=0;
        double total=0;

        aOrder aOrder=new aOrder();
        aOrder.setState("待支付");
        //aOrder.setCustomerId(customerId);
        aOrder.setTime(time);

        Iterator<OrderItem> it = list.iterator();
        while(it.hasNext()){
            OrderItem orderItem= it.next();

            Good good=goodMapper.selectById(orderItem.getGoodId());
            total+=good.getRetailPrice()*orderItem.getNum();
            profit+=(good.getRetailPrice()-good.getInputPrice())*orderItem.getNum();

        }
        aOrder.setTotal(total);
        aOrder.setProfit(profit);
        orderMapper.insert(aOrder);
        long orderId=aOrder.getOrderId();
        for(OrderItem orderItem:list){
            orderItem.setOrderId(orderId);
            orderItemMapper.insert(orderItem);
        }
        return R.ok().data("item",aOrder);
    }

    // TODO: 2021/12/9 /order/pay 处理上面现金结账后的收款确认，给orderId，把状态变成已完成就可以了,这里减库存
    @RequestMapping("/order/pay")
    public R pay(@RequestParam long orderId){
        if(orderMapper.selectById(orderId)==null){
            return R.error();
        }
        updateState(orderId,"已完成");
        List<OrderItem> list_items=orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id",orderId));
        for(OrderItem orderItem:list_items){
            RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",2).eq("good_id",orderItem.getGoodId()));
            repositoryItem.setNum(repositoryItem.getNum()-orderItem.getNum());
            repositoryItemMapper.updateById(repositoryItem);
        }
        return R.ok();
    }

    //获取所有销售单
    @GetMapping("/order/all")
    public R getAllOrder(){
        List<aOrder> list=orderMapper.selectList(null);
        R r=R.ok().data("items",list);
        return r;
    }

    //更新销售单状态
    @GetMapping("/order/updateState")
    public R updateState(@RequestParam long order_id,@RequestParam String newState){
        aOrder order=orderMapper.selectById(order_id);
        if(order.getState().equals("待审核")&&newState.equals("已保存")){
            for(OrderItem orderItem:orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id",order_id))){
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",1).eq("good_id",orderItem.getGoodId()));
                repositoryItem.setNum(repositoryItem.getNum()+orderItem.getNum());
                repositoryItemMapper.updateById(repositoryItem);
            }
        }
        if(order.getState().equals("已完成")&&newState.equals("已退货")){
            for(OrderItem orderItem:orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id",order_id))){
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",1).eq("good_id",orderItem.getGoodId()));
                repositoryItem.setNum(repositoryItem.getNum()+orderItem.getNum());
                repositoryItemMapper.updateById(repositoryItem);
            }
        }
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
        long id=Integer.parseInt((String)map.get("id"));
        /*ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> list = mapper.convertValue((List<OrderItem>)map.get("orderItems"), new TypeReference<List<OrderItem>>() { });*/
        String json= JSON.toJSONString((List<OrderItem>)map.get("orderItems"));
        List<OrderItem> list = JSONObject.parseArray(json, OrderItem.class);
        long customerId=Integer.parseInt((String)map.get("customerId"));
        LocalDateTime time= LocalDateTime.now();
        double profit=0;
        double total=0;
        if(id==-1){
            aOrder aOrder=new aOrder();
            aOrder.setState("已保存");
            aOrder.setCustomerId(customerId);
            aOrder.setTime(time);

            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                    OrderItem orderItem= it.next();

                    Good good=goodMapper.selectById(orderItem.getGoodId());
                    total+=good.getWholesalePrice()*orderItem.getNum();
                    profit+=(good.getWholesalePrice()-good.getInputPrice())*orderItem.getNum();
            }
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.insert(aOrder);
            long orderId=aOrder.getOrderId();
            for(OrderItem orderItem:list){
                orderItem.setOrderId(orderId);
                orderItemMapper.insert(orderItem);
            }

        }else{
            aOrder aOrder=orderMapper.selectById(id);
            orderItemMapper.delete(new QueryWrapper<OrderItem>().eq("order_id",id));
            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getRetailPrice()*orderItem.getNum();
                profit+=(good.getRetailPrice()-good.getInputPrice())*orderItem.getNum();
            }
            //aOrder.setTime(time);
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            orderMapper.updateById(aOrder);
        }
        return R.ok();
    }

    // TODO: 2021/12/9 开单的时候算一下total放order的属性里面,从修改界面开的单也要待审核！！！
    @PostMapping("/order/makeOrder")
    public R makeOrder(@RequestBody Map<String,Object> map){
        long id=Integer.parseInt((String)map.get("id"));
        /*ObjectMapper mapper = new ObjectMapper();
        List<OrderItem> list = mapper.convertValue((List<OrderItem>)map.get("orderItems"), new TypeReference<List<OrderItem>>() { });*/
        String json= JSON.toJSONString((List<OrderItem>)map.get("orderItems"));
        List<OrderItem> list = JSONObject.parseArray(json, OrderItem.class);
        long customerId=Integer.parseInt((String)map.get("customerId"));
        LocalDateTime time= LocalDateTime.now();
        double profit=0;
        double total=0;
        System.out.println(id);
        System.out.println(list);
        if(id==-1){
            aOrder aOrder=new aOrder();
            aOrder.setState("待审核");
            aOrder.setCustomerId(customerId);
            aOrder.setTime(time);

            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                //orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getWholesalePrice()*orderItem.getNum();
                profit+=(good.getWholesalePrice()-good.getInputPrice())*orderItem.getNum();
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",1).eq("good_id",good.getId()));
                repositoryItem.setNum(repositoryItem.getNum()-orderItem.getNum());
                repositoryItemMapper.updateById(repositoryItem);
            }
            aOrder.setTotal(total);
            aOrder.setProfit(profit);
            int result=orderMapper.insert(aOrder);
            Long orderId=aOrder.getOrderId();
            for(OrderItem orderItem:list){
                orderItem.setOrderId(orderId);
                orderItemMapper.insert(orderItem);
            }
        }else{
            aOrder aOrder=orderMapper.selectById(id);
            aOrder.setState("待审核");         //这行原来没有
            orderItemMapper.delete(new QueryWrapper<OrderItem>().eq("order_id",id));
            Iterator<OrderItem> it = list.iterator();
            while(it.hasNext()){
                OrderItem orderItem= it.next();
                orderItemMapper.insert(orderItem);
                Good good=goodMapper.selectById(orderItem.getGoodId());
                total+=good.getRetailPrice()*orderItem.getNum();
                profit+=(good.getRetailPrice()-good.getInputPrice())*orderItem.getNum();
                RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",1).eq("good_id",good.getId()));
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

    //TODO
    @RequestMapping("/order/totalSale")
    public R totalSale(){
        List<aOrder> list=orderMapper.selectList(new QueryWrapper<aOrder>().eq("state","已完成"));
        double totalSale=0;
        for(aOrder aOrder:list){
            totalSale+=aOrder.getTotal();
        }
        return R.ok().data("item",totalSale);
    }

    //TODO
    @RequestMapping("/order/totalProfit")
    public R totalProfit(){
        List<aOrder> list=orderMapper.selectList(new QueryWrapper<aOrder>().eq("state","已完成"));
        double totalProfit=0;
        for(aOrder aOrder:list){
            totalProfit+=aOrder.getProfit();
        }
        return R.ok().data("item",totalProfit);
    }
}
