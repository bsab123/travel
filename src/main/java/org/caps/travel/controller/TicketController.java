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
import org.springframework.web.bind.annotation.ResponseBody;

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
    /**
     * 管理员获得景点门票列表
     * @param model
     * @param vo
     * @return
     */
    @RequestMapping(value = "/manageTicket")
    public String getTicketPage(Model model, QueryVo vo){
        Page<Ticket> page = ticketService.selectTicketPageByQueryVo(vo);
        model.addAttribute("page", page);
        model.addAttribute("name",vo.getName());
        return "manager/ticket";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteTicket")
    public String deleteScenic(Integer id){
        //删除
        ticketService.deleteById(id);
        return "OK";
    }

    @RequestMapping(value = "/addTicket")
    public String insert(Ticket ticket){
        ticketService.addTicket(ticket);
        return "redirect:/manageTicket";
    }

    /**
     * 管理员修改门票信息
     * @param
     * @return
     */
    @RequestMapping(value = "/updateTicket")
    public String update(Ticket ticket){
        //插入
        ticketService.updateTicket(ticket);
        return "redirect:/manageTicket";
    }

    /**
     * 根据id获得门票信息
     * @param
     * @return
     */
    @RequestMapping(value = "/getTicketById")
    public @ResponseBody
    Ticket getTicketById(Integer id){
        return ticketService.getTicketById(id);
    }

}
