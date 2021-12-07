package com.proj.invoice.controller;

import com.proj.invoice.bean.OrderItem;
import com.proj.invoice.bean.RepositoryItem;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class RepositoryItemController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<RepositoryItem> itemService;

    // TODO: 2021/12/8 修改库存的接口
    @RequestMapping("/repositoryItem/mdyStock")
    public R mdfStock(@RequestParam long repId,@RequestParam long goodId,@RequestParam long num)
    {
        System.out.println(num);
        return R.ok();
    }

    @RequestMapping("/repositoryItem/add")
    public R add(@RequestBody RepositoryItem repositoryItem){
        return itemService.add(repositoryItem);
    }

    @RequestMapping("/repositoryItem/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new RepositoryItem());
    }

    @RequestMapping("/repositoryItem/delByRepositoryId")
    public R delByRepositoryId(@RequestParam long repositoryId){
        return itemService.del(repositoryId,"repository_id",new RepositoryItem());
    }

    @RequestMapping("/repositoryItem/update")
    public R update(@RequestBody RepositoryItem repositoryItem){
        return itemService.update(repositoryItem);
    }

    @RequestMapping("/repositoryItem/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new RepositoryItem());
    }

    @RequestMapping("/repositoryItem/searchByRepositoryId")
    public R searchByRepositoryId(@RequestParam long repositoryId){
//        return itemService.search(repositoryId,"repository_id",new RepositoryItem());
        List<RepositoryItem> list = new ArrayList<>();
        RepositoryItem item1 = new RepositoryItem(1,2,3,400);
        RepositoryItem item2 = new RepositoryItem(2,2,4,200);
        list.add(item1);
        list.add(item2);
        return R.ok().data("items",list);
    }

    @RequestMapping("/repositoryItem/all")
    public R all(){
        return itemService.all(new RepositoryItem());
    }
}
