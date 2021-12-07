package com.proj.invoice.controller;

import com.proj.invoice.bean.Good;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class GoodController {

    @Autowired
    @Qualifier("ItemService")
    ItemService<Good> itemService;

    @RequestMapping("/good/add")
    public R add(@RequestBody Good good){
//        return itemService.add(good);
        System.out.println(good);
        return R.ok();
    }

    // TODO: 2021/12/8 获取所有货品信息，包括总库存，数据库里加一个总库存方便一点，冗余字段       顶不住了，先睡会儿  4.43a.m.
    @GetMapping("good/getAll")
    public R getAll()
    {
        List<Good> list = new ArrayList<>();
        Good g1 = new Good(5,"钢笔-黑","这是黑色钢笔",1,2,3,200);
        Good g2 = new Good(6,"钢笔-蓝","这是蓝色钢笔001",1,2,3,200);
        Good g3 = new Good(7,"钢笔-蓝-","这是蓝色钢笔002",1,2,3,300);
        list.add(g1);
        list.add(g2);
        list.add(g3);
        return R.ok().data("items",list);
    }

    @GetMapping("/good/getStockById")
    public R getStock(@RequestParam long repId,@RequestParam long goodId)
    {
        return R.ok().data("stock",500);
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
//        return itemService.search(id,new Good());
        Good g = new Good(5,"pencil","description",5,6,7,200);
        return R.ok().data("item",g);
    }

    @RequestMapping("/good/searchByName")
    public R search(@RequestParam String name){
//        return itemService.search(name,"name",new Good());
        List<Good> list = new ArrayList<>();
        Good g1 = new Good(5,"钢笔-黑","这是黑色钢笔",1,2,3,200);
        Good g2 = new Good(6,"钢笔-蓝","这是蓝色钢笔001",1,2,3,200);
        Good g3 = new Good(7,"钢笔-蓝-","这是蓝色钢笔002",1,2,3,300);
        list.add(g1);
        list.add(g2);
        list.add(g3);
        return R.ok().data("items",list);
    }

    @RequestMapping("/good/all")
    public R all(){
        return itemService.all(new Good());
    }
}
