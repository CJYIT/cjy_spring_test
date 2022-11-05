package com.cjy.controller;

import com.cjy.domain.Role;
import com.cjy.domain.User;
import com.cjy.service.RoleService;
import com.cjy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping(value = "/user",produces = "text/html;charset=utf-8")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
//    文件上传
    @RequestMapping(value="/quick23",produces = "text/html;charset=utf-8")
    @ResponseBody
    public void save23(String username,String email,String password,String phoneNum, MultipartFile[] uploadFile) throws IOException {
    for (MultipartFile multipartFile : uploadFile) {
        String originalFilename = multipartFile.getOriginalFilename();
//        截取指定位置字符串.substring(2)，替换字符串如.replace(‘a’，‘1’)，从后面截取字符串 String.slice()
//        String originalFilename = (UUID.randomUUID()+multipartFile.getOriginalFilename()).substring(30);
        multipartFile.transferTo(new File("E:\\JavaProject\\学习项目\\springmvc\\cjy_spring_test\\src\\main\\webapp\\imges\\"+originalFilename));
        System.out.println(originalFilename);
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        user.setImg(originalFilename);
        user.setPhoneNum(phoneNum);
        System.out.println(user);
        userService.save(user);
        /*String path = System.getProperty("user.dir");
        System.out.println(path);*/
    }
}

    /**
     * 文件超大异常返回给客户端页面
     */
    @RequestMapping(value="/quick23",produces = "text/html;charset=utf-8")
    @RestControllerAdvice
    public class RestExceptionHandler {
        @ExceptionHandler(value = { MultipartException.class })
        @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
        public String unknownException(Exception ex, WebRequest req) {
            System.out.println(ex);
            return "图片太大了too large.";
        }
    }


    @RequestMapping(value = "/login",produces = "text/html;charset=utf-8")
    public String login(String username, String password,Integer type, HttpSession session){
        //添加多角色登录
        switch (type){
            case 1:{
                User user = userService.login(username, password);
                System.out.println("控制器获取的user:"+type);
                System.out.println(username+password);
                if (user!=null && password != null){
                    //登录成功
                    session.setAttribute("user",user);//将user存到session
                    return "redirect:/index.jsp";//登录成功重定向到新的页面

                }else {}
                break;
            }
            case 2:{
                User user = userService.Ptlogin(username, password);
                System.out.println("控制器获取的user:"+type);
                System.out.println(username+password);
                if (user!=null && user != null){
                    //登录成功
                    session.setAttribute("user",user);//将user存到session
                    return "ptuser";//登录成功返回的页面，视图解析器已经配置前缀后缀
                }else {}
                break;
            }



        }

        //登录失败
        return "redirect:/login.jsp";
       /* User user = userService.login(username, password);
        if (user!=null){
            //登录成功
            session.setAttribute("user",user);//将user存到session
            return "redirect:/index.jsp";
        }
        //登录失败
        return "redirect:/login.jsp";*/
    }

    @RequestMapping("/edit/{userId}")
    public String edit(User user,Long[] roleIds){
        userService.edit(user,roleIds);
        return "redirect:/user/list";
    }

    @RequestMapping("/del")
    public String del(Long userId,String img){
        File file=new File( "E:\\JavaProject\\学习项目\\springmvc\\cjy_spring_test\\src\\main\\webapp\\imges\\" + img);
        if(file.exists() && file.isFile())
            file.delete();//if();与if(){};一样，均表示执行完成这个判断
        userService.del(userId);
        return "redirect:/user/list";
    }

    //用户修改的保存页面
    @RequestMapping("/editUI")
    public ModelAndView editUI(){
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-edit");
        return modelAndView;
    }
    @RequestMapping(value = "/edit")
    public String updateUser(User user,Long[] roleIds) {
        userService.edit(user,roleIds);
        return "redirect:/user/list";
    }

    /*@RequestMapping("/del/{userId}")
    public String del(@PathVariable("userId") Long userId){
        userService.del(userId);
        return "redirect:/user/list";
    }*/
//保存的执行动作的方法
    @RequestMapping("/save")
    public String save(User user,Long[] roleIds){
        userService.save(user,roleIds);
        return "redirect:/user/list";
    }
//用户添加的保存页面
    @RequestMapping("/saveUI")
    public ModelAndView saveUI(){
        //构建ModelAndView实例
        ModelAndView modelAndView = new ModelAndView();
//        需要返回当前的所有角色数据,角色数据是由角色服务器负责,创建private RoleService roleService;并注入
        List<Role> roleList = roleService.list();
        //添加模型数据 可以是任意的POJO对象也可以是任何java类型
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");
        /*ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = roleService.list();
        modelAndView.addObject("roleList",roleList);
        modelAndView.setViewName("user-add");*/
        return modelAndView;
    }

    @RequestMapping("/list")
    public ModelAndView list(){
        List<User> userList = userService.list();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userList",userList);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

}
