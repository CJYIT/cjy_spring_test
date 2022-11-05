package com.cjy.controller;

import com.cjy.domain.Role;
import com.cjy.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/role")
@Controller
public class RoleController {
    @Autowired  //使用自动注入spring容器set方法可以省略了
//    modelAndView需要向spring容器要service
    private RoleService roleService;
//使用set方法向spring容器注入
    /*public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }
*/
    @RequestMapping("/list")
    public ModelAndView list(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();//注入成功的话用集合接收
//        设置模型对象
        modelAndView.addObject("roleList",roleList);
//        设置视图
        modelAndView.setViewName("role-list");
        return modelAndView;
    }
    @RequestMapping("/del/{roleId}")
    public String del(@PathVariable("roleId") Long roleId){
        roleService.del(roleId);
        return "redirect:/role/list";
    }
    @RequestMapping("/save")
    public String save(Role role){//前端进行提交的是Role对象属性，所以这里用role进行接收
        roleService.save(role);//需要创建一个保存的方法，先调用命名，然后快速调用方法
        return "redirect:/role/list";//使用重定向跳转到list方法，不是list页面

    }
//    测试检测是否能正确从jsp页面拿到数据
    /*@RequestMapping("/save")
    public String receive(HttpServletRequest request){
        String roleName=request.getParameter("roleName");
        String roleDesc=request.getParameter("roleDesc");
        System.out.println(roleName);
        System.out.println(roleDesc);
        return "redirect:/role/list";
    }*/
}
