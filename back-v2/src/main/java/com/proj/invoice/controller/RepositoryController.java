package com.proj.invoice.controller;

import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.Repository;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RepositoryController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<Repository> itemService;

    @RequestMapping("/repository/add")
    public R add(@RequestBody Repository repository){
        return itemService.add(repository);
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
        return itemService.all(new Repository());
    }
}
