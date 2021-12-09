package com.proj.invoice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.proj.invoice.bean.Customer;
import org.springframework.stereotype.Component;

@Component
public interface CustomerMapper extends BaseMapper<Customer> {
}
