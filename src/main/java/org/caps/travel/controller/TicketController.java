package org.caps.travel.controller;

import org.caps.travel.entity.Scenic;
import org.caps.travel.entity.Ticket;
import org.caps.travel.service.ScenicService;
import org.caps.travel.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <p>Title: TicketController</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/31 14:41
 */
@Controller
public class TicketController {
    @Autowired
    private ScenicService scenicService;
    @Autowired
    private TicketService ticketService;

    /**
     * 根据景点id获得门票信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getScenicTById")
    public String getScenicById(Integer id, Model model){
        Scenic scenic = scenicService.getScenicById(id);
        List<Ticket> ticketList =  ticketService.selectTicketListByFid(id);
        scenic.setTicketList(ticketList);
        model.addAttribute("scenic", scenic);
        return "user/scenicTicketInfo";
    }
}
