package com.proj.invoice.controller;

import com.proj.invoice.bean.Good;
import com.proj.invoice.bean.aUser;
import com.proj.invoice.service.ItemService;
import com.proj.invoice.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @PostMapping("login")
    public R login(@RequestBody aUser user){
        System.out.println(user);

        return R.ok().data("token","12138");
    }

    @GetMapping("info")
    public R info(@RequestParam String token){
        String[] roles = new String[1];
        roles[0] = "admin";
        System.out.println(token);
        return R.ok().data("name","admin").data("avatar","ps://wpimg.wallstcn.com/f778738c-e4httf8-4870-b634-56703b4acafe.gif")
                .data("introduction","I am a super administrator").data("roles",roles);
    }

    @PostMapping("setPos")
    public R setPos(@RequestBody aUser u)
    {
        //set Pos in DataBase
        System.out.println(u);
        return R.ok();
    }

    //上面的是模板代码，还没来得及改
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

}
