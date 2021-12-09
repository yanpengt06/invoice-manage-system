package com.proj.invoice.controller;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.GoodNew;
import com.proj.invoice.bean.RepositoryItem;
import com.proj.invoice.mapper.GoodMapper;
import com.proj.invoice.mapper.RepositoryItemMapper;
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

    // TODO: 2021/12/9 给商品的id，零售价，批发价，把两个价格update上去，不要直接update，把进价也覆盖掉了
    @GetMapping("/good/setPrice")
    public R setPrice(@RequestParam long id,@RequestParam double retail_price,@RequestParam double wholesale_price){
        Good good=goodMapper.selectById(id);
        int i=0;
        if(good==null){
            R.error();
        }else{
            good.setRetailPrice(retail_price);
            good.setWholesalePrice(wholesale_price);
            i=goodMapper.updateById(good);
        }
        if(i>0){
            return R.ok();
        }else {
            return R.error();
        }
    }

    @RequestMapping("/good/add")
    public R add(@RequestBody Good good){
        System.out.println(good.getName());
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

    // TODO: 2021/12/9 这里是模糊查询，不是简单的查询
    @RequestMapping("/good/searchByName")
    public R search(@RequestParam String name){
        return itemService.search(name,"name",new Good());
    }

    @RequestMapping("/good/blurSearch")
    public R blurSearch(@RequestParam String name){
        List<Good> list=goodMapper.selectList(new QueryWrapper<Good>().like("name",name));
        return R.ok().data("items",list);
    }

    @RequestMapping("/good/all")
    public R all(){
        return itemService.all(new Good());
    }

    @Autowired
    RepositoryItemMapper repositoryItemMapper;
    @GetMapping("/good/getStockById")
    public R getStock(@RequestParam long repId,@RequestParam long goodId)
    {
        RepositoryItem repositoryItem=repositoryItemMapper.selectOne(new QueryWrapper<RepositoryItem>().eq("repository_id",repId).eq("good_id",goodId));
        return R.ok().data("stock",repositoryItem.getNum());
    }

    @Autowired
    GoodMapper goodMapper;

    @GetMapping("/good/getAll")
    public R getAll()
    {
        List<Good> list_good = goodMapper.selectList(null);
        List<GoodNew> list_goodNew=new ArrayList<>();
        for(Good good:list_good){
            List<RepositoryItem> list_repositoryItem=repositoryItemMapper.selectList(new QueryWrapper<RepositoryItem>().eq("good_id",good.getId()));
            long sum=0;
            for(RepositoryItem repositoryItem:list_repositoryItem){
                sum+=repositoryItem.getNum();
            }
            //System.out.println(goodNew.getDescription());
            list_goodNew.add(new GoodNew(good.getId(),good.getName(),good.getDescription(),good.getInputPrice(),good.getRetailPrice(),good.getWholesalePrice(),sum));
        }
        return R.ok().data("items",list_goodNew);
    }
}
