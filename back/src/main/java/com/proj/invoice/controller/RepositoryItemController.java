package com.proj.invoice.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.OrderItem;
import com.proj.invoice.bean.RepositoryItem;
import com.proj.invoice.mapper.RepositoryItemMapper;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class RepositoryItemController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<RepositoryItem> itemService;

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
        return itemService.search(repositoryId,"repository_id",new RepositoryItem());
    }

    @RequestMapping("/repositoryItem/all")
    public R all(){
        return itemService.all(new RepositoryItem());
    }



    // TODO: 2021/12/8 修改库存的接口
    @Autowired
    RepositoryItemMapper repositoryItemMapper;
    @RequestMapping("/repositoryItem/mdyStock")
    public R mdfStock(@RequestParam long repId,@RequestParam long goodId,@RequestParam long num)
    {
        RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",repId).eq("good_id",goodId));
        if(repositoryItem!=null){
            repositoryItem.setNum(num);
            return update(repositoryItem);
        }
        return R.error();
    }
}
