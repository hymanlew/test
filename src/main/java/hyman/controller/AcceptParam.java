package hyman.controller;

import hyman.entity.User;
import hyman.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring 向页面传值以及接受页面传过来的参数的方式
 */
@Controller
@RequestMapping("param")
public class AcceptParam {

    @Resource
    private UserService userService;


    // 使用HttpServletRequest获取。
    @RequestMapping("/login.do")
    public String login(HttpServletRequest request){
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        return "";
    }


    // 使用@RequestParam注解。 表单属性是pass,用变量password接收
    @RequestMapping("/login1.do")
    public String login(HttpServletRequest request,String name, @RequestParam("pass")String password){
        System.out.println(name);
        System.out.println(password);
        return "";
    }


    /**
     *  使用自动机制封装成实体参数。
     *  <form action="login.do">
     *      用户名：<input name="name"/>
     *      密码：<input name="pass"/>
     *      <input type="submit" value="登陆">
     *  </form>
     */
    @RequestMapping("/login2.do")
    public String login1(User user){
        System.out.println(user.getName());
        System.out.println(user.getNum());
        return "";
    }


    /**
     * 二、向页面传值，当Controller组件处理后，需要向响应JSP传值时，可以使用的方法:
     *
     *   1)，使用HttpServletRequest 和 Session 然后setAttribute()，就和Servlet中一样，Model数据会利用 HttpServletRequest
     *      的 Attribute 传值到 success.jsp中
     *
     *   2)，使用ModelAndView对象
     */
    @RequestMapping("/login3.do")
    public ModelAndView  login(String name,String age){
        User user = userService.login(name,age);
        Map<String,Object> data = new HashMap<String,Object>();
        data.put("user",user);
        return new ModelAndView("success",data);
    }


    /**
     *   3)，使用 ModelMap 对象, ModelMap 数据会利用 HttpServletRequest 的Attribute 传值到success.jsp中
     */
    @RequestMapping("/login4.do")
    public String login(String name,String age ,ModelMap model){
        User user  = userService.login(name,age);
        model.addAttribute("user",user);
        model.put("name",name);
        return "success";
    }


    /**
     *   4)，使用 @ModelAttribute注解，在 Controller 方法的参数部分或 Bean 属性方法上使用， @ModelAttribute 数据会利用
     *      HttpServletRequest 的 Attribute 传值到success.jsp中
     *      @ModelAttribute 使用大致有有两种，一种是是直接标记在方法上，一种是标记在方法的参数中，两种标记方法产生的效果也各不相同。
     *
     *   在项目部署后运行，当控制器处理页面发出的请求时，查看控制台输出会发现，后台控制器并没有直接进入页面访问的路径（例如 login.do），
     *   而是先执行了被 @ModelAttribute 标记的方法。
     *
     *   应该这么理解，当同一个 controller 中有任意一个方法被 @ModelAttribute 注解标记，页面请求只要进入这个控制器，不管请求那个方法，
     *   均会优先执行被 @ModelAttribute 标记的方法，所以我们可以用 @ModelAttribute 注解的方法做一些初始化操作。
     *   而当同一个 controller 中有多个方法被 @ModelAttribute 注解标记，所有被 @ModelAttribute 标记的方法均会被优先执行，且会按先后顺
     *   序执行，然后再进入请求的方法。
     */
    @RequestMapping("/login5.do")
    public String login(@ModelAttribute("user") User user){
        return "success";
    }

    /**
     *  这里稍微做了点变形，可以看到在被 @ModelAttribute 方法中设值了返回路径为 befor 方法，但是在在代码运行的过程中并不会跳转 befor 方法，
     *  而是在代码执行完成 return 之前直接跳转了实际请求的方法。
     */
    @ModelAttribute()
    public String init(Model model){
        model.addAttribute("pojo", "pojo");
        return "param/befor.do";
    }

    @RequestMapping(value="befor.do")
    public String befor(){
        System.out.println("进入befor方法");
        return "index";
    }

    /**
     * 但是当 @RequestMapping 标记和 @ModelAttribute 同时标记在一个方法上时，页面会报 404，这是因为当两个注解标记到同一个方法上时，逻辑视图名
     * （即对应的 jsp 名称）并不是 return 的返回值，而是返回整个的请求路径（即 param/test，但是实际上是没有这个目录结构的，所以 404）。
     * 在这里我们就要修改代码，将页面的路径，与访问的请求路径相同。
     */
    @RequestMapping(value="test.do")
    @ModelAttribute(value="pojo")
    public String modelTest(){
        System.out.println("进入Test方法");
        return "test";
    }


    //  5）Session存储：可以利用 HttpServletReequest 的 getSession() 方法
    @RequestMapping("/login6.do")
    public String login(String name,String pwd,ModelMap model,HttpServletRequest request){
        User user = userService.login(name,pwd);
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        model.addAttribute("user",user);
        return "success";
    }


    //  6）自定义Map
    @ResponseBody
    @RequestMapping(value = "/updatestatus", method = RequestMethod.POST)
    public Map<String, Object> updateStatus(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        String id = request.getParameter("id");
        Integer inid = Integer.valueOf(id);
        boolean flag = inid>10;
        result.put("status", flag);
        return result;
    }


    @RequestMapping(value = "/list", method = {RequestMethod.POST,RequestMethod.GET})
    public String queryAdministrator(HttpServletRequest request, ModelMap model) {
        Integer roleId = request.getParameter("roleListById") == null ? 0 : Integer.parseInt(request.getParameter("roleListById"));
        Map<String, Object> properties = new HashMap<String, Object>();
        if(roleId.intValue() > 0) {
            properties.put("role:=", roleId);
            model.put("roleId", roleId);
        }
        model.put("roleList", roleId);
        return "sys_admin_list";
    }


    /**
     *  7）Spring MVC 默认采用的是转发来定位视图，如果要使用重定向，可以如下操作：
     *
     *    1，使用RedirectView，
     *    2，使用redirect:前缀，
     */
    public ModelAndView login2(){
        RedirectView view = new RedirectView("regirst.do");
        return new ModelAndView(view);
    }

    public String login(){
        return "redirect:regirst.do";
    }
}

