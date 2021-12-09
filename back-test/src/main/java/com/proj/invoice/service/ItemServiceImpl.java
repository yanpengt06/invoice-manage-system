package com.proj.invoice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.proj.invoice.bean.*;
import com.proj.invoice.mapper.*;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.ParameterizedType;
import java.util.List;


@Service("ItemService")
public class ItemServiceImpl<T> implements ItemService<T>{

    @Autowired
    GoodMapper goodMapper;

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    CustomerMapper customerMapper;

    @Autowired
    OrderItemMapper orderItemMapper;

    @Autowired
    RepositoryItemMapper repositoryItemMapper;

    @Autowired
    OrderMapper orderMapper;

    @Autowired
    RepositoryMapper repositoryMapper;

    @Autowired
    UserMapper userMapper;

    /*BaseMapper<T> baseMapper;*/

    public BaseMapper<T> GenericMapper(T t){
        if(t instanceof Good){
            return (BaseMapper<T>) goodMapper;
        }
        if(t instanceof Employee){
            return (BaseMapper<T>) employeeMapper;
        }
        if(t instanceof Customer){
            return (BaseMapper<T>) customerMapper;
        }
        if(t instanceof OrderItem){
            return (BaseMapper<T>) orderItemMapper;
        }
        if(t instanceof RepositoryItem){
            return (BaseMapper<T>) repositoryItemMapper;
        }
        if(t instanceof aOrder){
            return (BaseMapper<T>) orderMapper;
        }
        if(t instanceof Repository){
            return (BaseMapper<T>) repositoryMapper;
        }
        if(t instanceof aUser){
            return (BaseMapper<T>) userMapper;
        }
        return null;
    }

    @Override
    public R add(T t) {
        int i=GenericMapper(t).insert(t);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @Override
    public R del(long id,T t) {
        int i=GenericMapper(t).deleteById(id);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @Override
    public R del(long content, String index, T t) {
        QueryWrapper<T> wrapper=new QueryWrapper<>();
        wrapper.eq(index,content);
        int i=GenericMapper(t).delete(wrapper);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @Override
    public R del(String content,String index,T t) {
        QueryWrapper<T> wrapper=new QueryWrapper<>();
        wrapper.eq(index,content);
        int i=GenericMapper(t).delete(wrapper);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
    }


    @Override
    public R update(T t) {
        int i=GenericMapper(t).updateById(t);
        if(i>0){
            return R.ok();
        }else{
            return R.error();
        }
    }

    @Override
    public R search(long id,T t) {
        T item=GenericMapper(t).selectById(id);
        if(item!=null){
            return R.ok().data("item",item);
        }else{
            return R.error();
        }
    }

    @Override
    public R search(long content, String index, T t) {
        QueryWrapper<T> wrapper=new QueryWrapper<>();
        wrapper.eq(index,content);
        List<T> items=GenericMapper(t).selectList(wrapper);
        if(items.size()!=0){
            return R.ok().data("items",items);
        }else{
            return R.error();
        }
    }

    @Override
    public R search(String content,String index, T t) {
        QueryWrapper<T> wrapper=new QueryWrapper<>();
        wrapper.eq(index,content);
        List<T> items=GenericMapper(t).selectList(wrapper);
        if(items.size()!=0){
            return R.ok().data("items",items);
        }else{
            return R.error();
        }
    }

    @Override
    public R all(T t) {
        List<T> items=GenericMapper(t).selectList(null);
        if(items.size()!=0){
            return R.ok().data("items",items);
        }else{
            return R.error();
        }
    }


}

