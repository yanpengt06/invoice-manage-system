package com.proj.invoice.controller;

import com.proj.invoice.bean.Good;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class GoodController {

    @Autowired
    @Qualifier("ItemService")
    ItemService<Good> itemService;

    @RequestMapping("/good/add")
    public R add(@RequestBody Good good){
        return itemService.add(good);
    }

    @RequestMapping("/good/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new Good());
    }

    @RequestMapping("/good/delByName")
    public R del(@RequestParam String name){
        return itemService.del(name,"name",new Good());
    }

    @RequestMapping("/good/update")
    public R update(@RequestBody Good good){
        return itemService.update(good);
    }

    @RequestMapping("/good/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new Good());
    }

    @RequestMapping("/good/searchByName")
    public R search(@RequestParam String name){
        return itemService.search(name,"name",new Good());
    }

    @RequestMapping("/good/all")
    public R all(){
        return itemService.all(new Good());
    }
}
