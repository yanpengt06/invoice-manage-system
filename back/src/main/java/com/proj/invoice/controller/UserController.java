package com.proj.invoice.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.proj.invoice.bean.Employee;
import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.aUser;
import com.proj.invoice.mapper.EmployeeMapper;
import com.proj.invoice.mapper.UserMapper;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    @Qualifier("ItemService")
    ItemService<aUser> itemService;

    @RequestMapping("/user/add")
    public R add(@RequestBody aUser aUser){
        return itemService.add(aUser);
    }

    @RequestMapping("/user/delById")
    public R del(@RequestParam long id){
        return itemService.del(id,new aUser());
    }

    @RequestMapping("/user/delByPosition")
    public R del(@RequestParam String position){
        return itemService.del(position,"position",new aUser());
    }

    @RequestMapping("/user/update")
    public R update(@RequestBody aUser aUser){
        return itemService.update(aUser);
    }

    @RequestMapping("/user/searchById")
    public R search(@RequestParam long id){
        return itemService.search(id,new aUser());
    }

    @RequestMapping("/user/searchByPosition")
    public R search(@RequestParam String position){
        return itemService.search(position,"position",new aUser());
    }

    @RequestMapping("/user/all")
    public R all(){
        return itemService.all(new aUser());
    }


    //上面的是模板代码,下面图省事了
    @Autowired
    UserMapper userMapper;
    @Autowired
    EmployeeMapper employeeMapper;

    @PostMapping("/user/login")
    public R login(@RequestBody aUser user){
        aUser aUser=userMapper.selectById(user.getAccount());
        if (aUser!=null&&user.getPwd().equals(aUser.getPwd())){
            return R.ok().data("token",aUser.getAccount());
        }
        return R.error();
    }

    @GetMapping("/user/info")
    public R info(@RequestParam String token){
        Employee employee=employeeMapper.selectOne(new QueryWrapper<Employee>().eq("account",token));
        if(employee!=null){
            String[] roles = new String[1];
            roles[0] = employee.getPosition();
            return R.ok().data("name",employee.getName()).data("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif")
                    .data("introduction","I am "+roles[0]).data("roles",roles);
        }
        return R.error();
    }

    @PostMapping("setPos")
    public R setPos(@RequestBody aUser u)
    {
        //set Pos in DataBase
        System.out.println(u);
        return R.ok();
    }

}
