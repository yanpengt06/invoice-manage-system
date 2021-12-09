package com.proj.invoice.controller;

import com.proj.invoice.bean.Employee;
import com.proj.invoice.bean.Good;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class EmployeeController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<Employee> itemService;

    // TODO: 2021/12/9 这里没有员工account，自动生成一个账户，放在返回的item里面
    @RequestMapping("/employee/add")
    public R add(@RequestBody Employee employee){
        return itemService.add(employee);
    }

    @RequestMapping("/employee/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new Employee());
    }

    @RequestMapping("/employee/delByName")
    public R del(@RequestParam String name){
        return itemService.del(name,"name",new Employee());
    }

    // TODO: 2021/12/9 /employee/updatePos,给了一个eply对象，只有职位和id，改这两个就行，不要直接update，写到前面那个路由
    @RequestMapping("/employee/update")
    public R update(@RequestBody Employee employee){
        return itemService.update(employee);
    }

    @RequestMapping("/employee/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new Employee());
    }

    @RequestMapping("/employee/searchByName")
    public R search(@RequestParam String name){
        return itemService.search(name,"name",new Employee());
    }

    @RequestMapping("/employee/all")
    public R all(){
        return itemService.all(new Employee());
    }
}
