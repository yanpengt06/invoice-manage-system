package com.proj.invoice.controller;

import com.proj.invoice.bean.Employee;
import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.OrderItem;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

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


}
