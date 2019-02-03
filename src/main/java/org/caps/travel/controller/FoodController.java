package org.caps.travel.controller;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Tastyfood;
import org.caps.travel.service.FoodService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>Title: FoodController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/31 17:04
 */
@Controller
public class FoodController {
    @Autowired
    FoodService foodService;
    /**
     * 为用户展示所有美食getScenicPageForUser
     * @param model
     * @return
     */
    @RequestMapping(value = "/getFoodPageForUser")
    public String listFoodPageToUser(Model model, QueryVo vo){
        Page<Tastyfood> page = foodService.selectFoodPageByQueryVo(vo);
        model.addAttribute("page", page);
        return "user/foodList";
    }
}
