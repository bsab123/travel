package org.caps.travel.controller;

import org.caps.travel.entity.Manager;
import org.caps.travel.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class ManagerController {
    @Autowired
    private ManagerService managerService;
    /**
     * 删除管理员
     * @param id
     * @return
     */
    @RequestMapping(value = "/deleteManager")
    public @ResponseBody
    String deleteManager(String id){
        return null;
    }

    /**
     * 管理员列表
     * @param model
     * @return
     */
    @RequestMapping(value = "/listManager")
    public String getAllManager(Model model,@RequestParam(required=false)String name){
       return null;
    }

    /**
     * 管理员登陆
     * @param model
     * @param session
     * @param
     * @return
     */
    @RequestMapping(value = "/managerLogin")
    public String confirmManager(RedirectAttributes redirectAttributes, Model model, HttpSession session, String name, String password ){
        Manager manager1 = managerService.confirmManager(name,password);
        if(manager1!=null) {
            if (manager1.getFlag() == 1 || manager1.getFlag() == 0) {
                //将user对象存到session中
                session.setAttribute("manager", manager1);
                return "redirect:/manager";
            }
            redirectAttributes.addFlashAttribute("errorInfo", "账号信息错误！");
            //model.addAttribute("errorInfo", "账号信息错误！");
            return "redirect:/managerLoginPage";
        }
        redirectAttributes.addFlashAttribute("errorInfo", "用户名或密码错误！");
        return "redirect:/managerLoginPage";
    }

    /**
     * 管理员注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/managerLogout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        //从session中将user删除
        session.removeAttribute("manager");
        return "manager/login";
    }
}
