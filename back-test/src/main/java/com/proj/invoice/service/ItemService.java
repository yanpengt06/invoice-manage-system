package com.proj.invoice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.proj.invoice.utils.R;
import org.springframework.stereotype.Service;

public interface ItemService<T>{
    public R add(T t);
    public R del(long id,T t);
    public R del(long content,String index,T t);
    public R del(String content,String index,T t);
    public R update(T t);
    public R search(long id,T t);
    public R search(long content,String index,T t);
    public R search(String content,String index,T t);
    public R all(T t);
}
