package com.proj.invoice.controller;

import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.Repository;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
public class RepositoryController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<Repository> itemService;

    @RequestMapping("/repository/add")
    public R add(@RequestBody Repository repository){
//        return itemService.add(repository);
        System.out.println(repository);
        return R.ok();
    }

    @GetMapping("/repository/deploy")
    public R deploy(@RequestParam long repAid,@RequestParam long repBid,
                    @RequestParam long goodId,@RequestParam long num)
    {
        System.out.println(num);
        return R.ok();
    }


    @RequestMapping("/repository/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new Repository());
    }

    @RequestMapping("/repository/delByClassification")
    public R del(@RequestParam String classification){
        return itemService.del(classification,"classification",new Repository());
    }

    @RequestMapping("/repository/update")
    public R update(@RequestBody Repository repository){
        return itemService.update(repository);
    }

    @RequestMapping("/repository/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new Repository());
    }

    @RequestMapping("/repository/searchByClassification")
    public R search(@RequestParam String classification){
        return itemService.search(classification,"classification",new Repository());
    }

    @RequestMapping("/repository/all")
    public R all(){
//        return itemService.all(new Repository());
        List<Repository> list = new ArrayList<>();
        Repository r1 = new Repository(1,"大库","一曼街91号","13345678912");
        Repository r2 = new Repository(1,"门店","一曼街93号","13345678912");
        Repository r3 = new Repository(1,"大库","西大直街91号","13345678912");
        list.add(r1);
        list.add(r2);
        list.add(r3);
        return R.ok().data("items",list);
    }
}
