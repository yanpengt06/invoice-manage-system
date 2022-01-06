package com.proj.invoice.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.*;
import com.proj.invoice.mapper.GoodMapper;
import com.proj.invoice.mapper.RepositoryItemMapper;
import com.proj.invoice.mapper.RepositoryMapper;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
public class RepositoryItemController {
    @Autowired
    @Qualifier("ItemService")
    ItemService<RepositoryItem> itemService;

    @Autowired
    RepositoryItemMapper repositoryItemMapper;
    // TODO: 2021/12/9 /repositoryItem/deploy 参数：@RequestParam repAid,repBid,goodId,num 返回值：ok/error 从A往B调配num个goodId
    @RequestMapping("/repositoryItem/deploy")
    public R deploy(@RequestParam long repAid,@RequestParam long repBid,@RequestParam long goodId,@RequestParam long num){
        RepositoryItem repositoryItem_A=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("good_id",goodId).eq("repository_id",repAid));
        RepositoryItem repositoryItem_B=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("good_id",goodId).eq("repository_id",repBid));
        repositoryItem_A.setNum(repositoryItem_A.getNum()-num);
        //需要检测库存的话请去掉注释
        if(repositoryItem_A.getNum()<0){
            return R.error();//.data("description","库存不足以调货"+String.valueOf(num));
        }
        repositoryItem_B.setNum(repositoryItem_B.getNum()+num);
        update(repositoryItem_A);
        update(repositoryItem_B);
        return R.ok();
    }

    @Autowired
    GoodMapper goodMapper;
    // TODO: 2021/12/9 进货，不是加入一个仓库项，在原来仓库项的基础上加库存，如果有的话
    @RequestMapping("/repositoryItem/input")
    public R input(@RequestParam long goodId,@RequestParam long num,@RequestParam long repId)
    {
        if(goodMapper.selectById(goodId)==null){
            return R.error();
        }
        RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("good_id",goodId).eq("repository_id",repId));
        if(repositoryItem==null){
            RepositoryItem repositoryItem_new=new RepositoryItem();
            repositoryItem_new.setRepositoryId(repId);
            repositoryItem_new.setNum(num);
            repositoryItem_new.setGoodId(goodId);
            repositoryItemMapper.insert(repositoryItem);
        }else{
            repositoryItem.setNum(repositoryItem.getNum()+num);
            update(repositoryItem);
        }
        return R.ok();
    }

    // TODO: 2021/12/9 参数：repId，返回值：List<Good> 里面是所有该仓库有的good，good里面带上库存，这个怎么搞，把数据库改了？
    //哈哈我看到你改的GoodNew了，就这么办吧，这样不会有冗余项，里面带上stock之类的属性
    @RequestMapping("/repositoryItem/getGoodsByRepId")
    public R getGoodsByRepId(@RequestParam long repId){
        List<RepositoryItem> list_items=repositoryItemMapper.selectList(new QueryWrapper<RepositoryItem>().eq("repository_id",repId));
        List<GoodNew> list_goodNew=new ArrayList<>();
        for(RepositoryItem repositoryItem:list_items){
            Good good=goodMapper.selectById(repositoryItem.getGoodId());
            list_goodNew.add(new GoodNew(good.getId(),good.getName(),good.getDescription(),good.getInputPrice(),good.getRetailPrice(),good.getWholesalePrice(),repositoryItem.getNum()));
        }
        return R.ok().data("items",list_goodNew);
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
        return itemService.search(repositoryId,"repository_id",new RepositoryItem());
    }

    @RequestMapping("/repositoryItem/all")
    public R all(){
        return itemService.all(new RepositoryItem());
    }



    // TODO: 2021/12/8 修改库存的接口
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

    @Autowired
    RepositoryMapper repositoryMapper;
    //TODO
    @RequestMapping("/repositoryItem/count")
    public R count(){
        List<Repository> list_repository=repositoryMapper.selectList(new QueryWrapper<Repository>());
        Map<Long,Long> map=new HashMap<>();
        for(Repository repository:list_repository){
            long repositoryId=repository.getRepositoryId();
            long count=0;
            List<RepositoryItem> list_repositoryItem=repositoryItemMapper.selectList(new QueryWrapper<RepositoryItem>().eq("repository_id",repositoryId));
            for(RepositoryItem repositoryItem:list_repositoryItem){
                count+=repositoryItem.getNum();
            }
            map.put(repositoryId,count);
        }
        return R.ok().data("items",map);
    }
}
