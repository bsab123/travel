package org.caps.travel.controller;

import io.swagger.annotations.ApiOperation;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import org.caps.travel.service.SolrService;
import org.caps.travel.service.UserService;
import org.caps.travel.utils.MD5Utils;
import org.caps.travel.utils.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

/**
 * <p>Title: userController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/22 13:31
 */
@Controller
public class UserController {

    @Value("${pageSize}")
    private Integer pageSize;
    Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private SolrService solrService;


    /**
     * 用户登录
     * @param model
     * @param request
     * @param session
     * @param response
     * @param user
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String userLogin(Model model, HttpServletRequest request, HttpSession session, HttpServletResponse response, User user){
        String inputVerifyCode=request.getParameter("verifyCode");
        String verifyCodeValue=(String) session.getAttribute("verifyCodeValue");
        System.out.println(session.getAttribute("verifyCodeValue"));
        //判断输入的验证码和生成后存在session中的验证码是否一致
        if(verifyCodeValue.equals(inputVerifyCode.toUpperCase())){
            //输入的密码加密后与数据库进行密文比对
            user.setPassword(MD5Utils.md5(user.getPassword()));
            User u = userService.confirmUser(user);
            if(u==null){
                model.addAttribute("errorInfo","用户名或密码错误或者账号未处于激活状态！");
                return "user/login";
            }else {
                if (u.getUserid() != null && !"".equals(u.getUserid())) {
                    //登录成功
                    //***************判断用户是否勾选了自动登录*****************
                    String autoLogin = request.getParameter("autoLogin");
                    if("autoLogin".equals(autoLogin)){
                        //要自动登录
                        //创建存储用户名的cookie
                        Cookie cookie_username = new Cookie("cookie_username",user.getUsername());
                        cookie_username.setMaxAge(10*60);
                        //创建存储密码的cookie
                        Cookie cookie_password = new Cookie("cookie_password",user.getPassword());
                        cookie_password.setMaxAge(10*60);
                        response.addCookie(cookie_username);
                        response.addCookie(cookie_password);
                    }
                    //将user对象存到session中
                    session.setAttribute("user", u);
                    return "redirect:/user";
                }
                model.addAttribute("errorInfo","用户名id为空！");
                return "user/login";
            }
        }else{
            model.addAttribute("errorInfo","验证码错误！");
            return "user/login";
        }
    }

    /**
     * 用户注册
     * @param model
     * @param request
     * @param session
     * @param user
     * @return
     */
    @ApiOperation(value = "注册接口")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String userRegister(Model model, HttpServletRequest request, HttpSession session, User user){
        //验证码
        String inputVerifyCode=request.getParameter("verifyCode");
        //存在验证码中的session
        String verifyCodeValue=(String)session.getAttribute("verifyCodeValue");
        //如果匹配
        if(verifyCodeValue.equals(inputVerifyCode.toUpperCase())){
            //激活码
            String activeCode =  UUID.randomUUID().toString();
            user.setCode(activeCode);
            String userid = UUID.randomUUID().toString();
            user.setUserid(userid);
            user.setPassword(MD5Utils.md5(user.getPassword()));
            //检查用户名是否唯一
            int k = userService.checkUsername(user.getUsername());
            if(k==0) {
                int i = userService.userRegister(user);
                if (i > 0) {
                    //发送激活邮件
                    String emailMsg = "恭喜您注册成功，请点击下面的连接进行激活账户"
                            + "<a href='http://localhost:8503/caps/active?activeCode=" + activeCode + "'>"
                            + "http://localhost:8503/caps/active?activeCode=" + activeCode + "</a>";
                    try {
                        SimpleMailMessage message = new SimpleMailMessage();
                        message.setFrom("279205343@qq.com");
                        message.setTo(user.getEmail());
                        message.setSubject("主题：账号激活");
                        message.setText(emailMsg);
                        mailSender.send(message);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    return "user/login";//注册成功到登陆界面
                }
                model.addAttribute("errorInfo","注册用户失败！");
                return "user/register";//插入注册数据失败
            }
            model.addAttribute("errorInfo","用户名已经存在！");
            return "user/register";//用户名已经存在
        }else{
            model.addAttribute("errorInfo","验证码错误！");
            return "user/register";//验证码输入错误返回登陆界面
        }
    }

    /**
     * 用户激活
     * @param model
     * @param activeCode
     * @return
     */
    @RequestMapping(value = "/active", method = RequestMethod.GET)
    public String active(Model model,String activeCode){
        //把用户状态码变成1
        int i = userService.active(activeCode);
        if(i==1) {
            return "redirect:loginPage";//激活成功到界面
        }
        return "redirect:registerPage";//激活失败跳转到注册界面
    }

    /**
     * 用户注销
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //从session中将user删除
        session.removeAttribute("user");

        //将存储在客户端的cookie删除掉
        Cookie cookie_username = new Cookie("cookie_username","");
        cookie_username.setMaxAge(0);
        //创建存储密码的cookie
        Cookie cookie_password = new Cookie("cookie_password","");
        cookie_password.setMaxAge(0);

        response.addCookie(cookie_username);
        response.addCookie(cookie_password);

        return "user/login";
    }




    /**
     * 管理员禁用用户
     */
    @RequestMapping(value = "/updateUserState")
    public @ResponseBody
    String updateState(String id){
        userService.updateStateById(id);
        return "OK";
    }

    /**
     * 用户修改密码
     * @param id
     * @return
     */
    @RequestMapping(value = "/updatePwd")
    public @ResponseBody String updatePassword(String oldpwd,String newpwd,HttpServletRequest request, HttpSession session){
        User user = (User)session.getAttribute("user");
        user.setPassword(MD5Utils.md5(oldpwd));
        User u = userService.confirmUser(user);
        if(u!=null&&u.getUserid()!=null&&u.getPassword()!=null) {
            user.setPassword(MD5Utils.md5(newpwd));
            userService.updateUserInfo(user);
            return "OK";
        }
        return "error";
    }

    /**
     * 用户修改个人信息
     * @param
     * @return
     */
    @RequestMapping(value = "/updateInfo")
    public @ResponseBody String updateInfo(User user,HttpServletRequest request, HttpSession session){
        User user1 = (User)session.getAttribute("user");
        user.setUserid(user1.getUserid());
        userService.updateUserInfo(user);
        return "OK";
    }


    @RequestMapping(value = "search", method = RequestMethod.GET)
    public String search(
            @RequestParam(required = true) String query,
            Model model) {
        int size=100;
        int page=1;
        List<User> tbItemResults = solrService.search(query, page, size);
        model.addAttribute("page", tbItemResults);
        return "manager/visitor";
    }


}
