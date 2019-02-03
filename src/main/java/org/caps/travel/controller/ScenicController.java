package org.caps.travel.controller;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Scenic;
import org.caps.travel.entity.Ticket;
import org.caps.travel.service.ScenicService;
import org.caps.travel.service.TicketService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: ScenicController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/31 15:57
 */
@Controller
public class ScenicController {

    @Autowired
    ScenicService scenicService;

    @Autowired
    TicketService ticketService;
    /**
     * 为用户展示所有的景点信息getScenicPageForUser
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value = "/getScenicPageForUser")
    public String getScenicPageForUser(Model model, QueryVo vo, HttpServletRequest request){
        System.out.println("test-----------"+vo);
        Page<Scenic> page = scenicService.selectPageByQueryVo(vo);
        for (Scenic scenic :page.getRows()) {
            List<Ticket> ticketList =  ticketService.selectTicketListByFid(scenic.getId());
            double cheapestPrice = ticketService.selectPriceByFid(scenic.getId());
            scenic.setTicketList(ticketList);
            scenic.setCheapestPrice(cheapestPrice);
        }
        //根据参数判断目前是不是根据地址获得景点信息
        String addrParameter = request.getParameter("addr");
        if (addrParameter!=null){
            model.addAttribute("addrParameter",addrParameter);
        }
        model.addAttribute("page", page);
        return "user/scenicList";
    }
}
