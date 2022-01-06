package com.proj.invoice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.*;
import com.proj.invoice.mapper.CustomerMapper;
import com.proj.invoice.mapper.GoodMapper;
import com.proj.invoice.mapper.OrderItemMapper;
import com.proj.invoice.mapper.OrderMapper;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
public class OrderItemController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<OrderItem> itemService;

    @RequestMapping("/orderItem/add")
    public R add(@RequestBody OrderItem orderItem){
        return itemService.add(orderItem);
    }

    @RequestMapping("/orderItem/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new OrderItem());
    }

    @RequestMapping("/orderItem/delByOrderId")
    public R delByOrderId(@RequestParam long orderId){
        return itemService.del(orderId,"order_id",new OrderItem());
    }

    @RequestMapping("/orderItem/update")
    public R update(@RequestBody OrderItem orderItem){
        return itemService.update(orderItem);
    }

    @RequestMapping("/orderItem/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new OrderItem());
    }

    @RequestMapping("/orderItem/searchByOrderId")
    public R searchByOrderId(@RequestParam long orderId){
        return itemService.search(orderId,"order_id",new OrderItem());
    }

    @RequestMapping("/orderItem/all")
    public R all(){
        return itemService.all(new OrderItem());
    }

    @Autowired
    OrderMapper orderMapper;
    @Autowired
    OrderItemMapper orderItemMapper;
    @Autowired
    GoodMapper goodMapper;
    @Autowired
    CustomerMapper customerMapper;
    class Statistics{
        long id;
        String name;
        long num;

        public Statistics(long id, String name, long num) {
            this.id = id;
            this.name = name;
            this.num = num;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getNum() {
            return num;
        }

        public void setNum(long num) {
            this.num = num;
        }
    }
    // TODO: 2022/1/3 路由orderItem/custmStatistics 参数: id: 客户id，返回值： goods列表，客户购买过的所有货品，json格式：{id:5,name:"铅笔"，num：100}，还是封装在items里
    @RequestMapping("/orderItem/custmStatistics")
    public R custmStatistics(@RequestParam long id){//顾客id
        List<Statistics> list_statistics=new ArrayList<>();
        List<aOrder> list_order=orderMapper.selectList(new QueryWrapper<aOrder>().eq("customer_id",id));
        for(aOrder aOrder:list_order){
            List<OrderItem> list_orderItem=orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("order_id",aOrder.getOrderId()));
            for(OrderItem orderItem:list_orderItem){
                boolean flag=true;
                for(Statistics statistics:list_statistics){
                    if(statistics.getId()==orderItem.getGoodId()) {
                        statistics.setNum(statistics.getNum() + orderItem.getNum());
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    list_statistics.add(new Statistics(orderItem.getGoodId(),goodMapper.selectById(orderItem.getGoodId()).getName(),orderItem.getNum()));
                }
            }

        }
        return R.ok().data("items",list_statistics);
    }
    // TODO: 2022/1/3 路由orderItem/goodStatistics 参数： id：货品id，返回值：购买过该货品的所有客户列表，json格式{id:8,name:"张益达",num:1000}
    @RequestMapping("/orderItem/goodStatistics")
    public R goodStatistics(@RequestParam long id){//货品id
        List<Statistics> list_statistics=new ArrayList<>();
        List<Customer> list_customer=new ArrayList<>();
        List<OrderItem> list_orderItem=orderItemMapper.selectList(new QueryWrapper<OrderItem>().eq("good_id",id));
        for(OrderItem orderItem:list_orderItem){
            Customer customer=null;
            if(orderItem.getOrderId()!=-1){
                customer=customerMapper.selectById(orderMapper.selectById(orderItem.getOrderId()).getCustomerId());
            }
            boolean flag=true;
            for(Customer customer1:list_customer){
                if(customer1.getId()==customer.getId()){
                    flag=false;
                }
            }
            if(flag){
                list_customer.add(customer);
            }
        }
        for(Customer customer:list_customer){
            list_statistics.add(new Statistics(customer.getId(),customer.getName(),0));
        }
        for(OrderItem orderItem:list_orderItem){
            for(Statistics statistics:list_statistics){
                if(orderMapper.selectById(orderItem.getOrderId()).getCustomerId()==statistics.getId()){
                    statistics.setNum(statistics.getNum()+orderItem.getNum());
                }
            }
        }
        return R.ok().data("items",list_statistics);
    }
    class FundStatistics{
        long id;
        String name;
        double checked;
        double tocheck;

        public FundStatistics(long id, String name, double checked, double tocheck) {
            this.id = id;
            this.name = name;
            this.checked = checked;
            this.tocheck = tocheck;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getChecked() {
            return checked;
        }

        public void setChecked(double checked) {
            this.checked = checked;
        }

        public double getTocheck() {
            return tocheck;
        }

        public void setTocheck(double tocheck) {
            this.tocheck = tocheck;
        }
    }
    // DONE: 2022/1/3 路由orderItem/fundStatistics 参数：null，返回值：客户列表，包含该客户已支付款项、未支付款项等信息，json格式{id:5,name:"张三疯"，checked:5000,tocheck:200}
    @RequestMapping("/orderItem/fundStatistics")
    public R fundStatistics(){
        List<FundStatistics> list_fundStatistics=new ArrayList<>();
        List<Customer> list_customer=customerMapper.selectList(new QueryWrapper<Customer>());
        for(Customer customer:list_customer){
            list_fundStatistics.add(new FundStatistics(customer.getId(),customer.getName(),0,0));
        }
        List<OrderItem> list_orderItem=orderItemMapper.selectList(new QueryWrapper<OrderItem>());
        Set<Long> set_order=new HashSet<>();
        for(OrderItem orderItem:list_orderItem){
            if(orderItem.getOrderId()==-1){
                continue;
            }
            set_order.add(orderItem.getOrderId());
        }
//        List<aOrder> set_order=orderMapper.selectList(new QueryWrapper<aOrder>());
        for(long order_id:set_order){
            for(FundStatistics fundStatistics:list_fundStatistics){
                aOrder aOrder=orderMapper.selectById(order_id);
                if(fundStatistics.getId()==aOrder.getCustomerId()){
                    if(aOrder.getState().equals("已完成")){
                        fundStatistics.setChecked(fundStatistics.getChecked()+aOrder.getTotal());
                    }else{
                        fundStatistics.setTocheck(fundStatistics.getTocheck()+aOrder.getTotal());
                    }
                }
            }
        }
        return R.ok().data("items",list_fundStatistics);
    }
}
