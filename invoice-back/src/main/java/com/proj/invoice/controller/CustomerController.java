package com.proj.invoice.controller;

import com.proj.invoice.bean.Customer;
import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.Repository;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CustomerController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<Customer> itemService;

    @RequestMapping("/customer/add")
    public R add(@RequestBody Customer customer){
//        return itemService.add(customer);
        System.out.println(customer);
        return R.ok();
    }

    @RequestMapping("/customer/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new Customer());
    }

    @RequestMapping("/customer/delByName")
    public R del(@RequestParam String name){
        return itemService.del(name,"name",new Customer());
    }

    @RequestMapping("/customer/update")
    public R update(@RequestBody Customer customer){
        return itemService.update(customer);
    }

    @RequestMapping("/customer/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new Customer());
    }

    @RequestMapping("/customer/searchByName")
    public R search(@RequestParam String name){
        return itemService.search(name,"name",new Customer());
    }

    @RequestMapping("/customer/all")
    public R all(){
//        return itemService.all(new Customer());
        List<Customer> list = new ArrayList<>();
        Customer c1 = new Customer(1,"张三","18912345678","东大直街60#","批发");
        Customer c2 = new Customer(1,"李四","18912345678","东大直街60#","批发");
        Customer c3 = new Customer(1,"王五","18912345678","东大直街60#","批发");
        list.add(c1);
        list.add(c2);
        list.add(c3);
        return R.ok().data("items",list);
    }
}
