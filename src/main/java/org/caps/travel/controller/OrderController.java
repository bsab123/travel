package org.caps.travel.controller;

import org.caps.travel.entity.Order;
import org.caps.travel.entity.Ticket;
import org.caps.travel.entity.User;
import org.caps.travel.service.OrderService;
import org.caps.travel.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class OrderController {


    @Autowired
    private TicketService ticketService;
    @Autowired
    private OrderService orderService;

    /**
     * 用户点击购买后获得的订单信息
     * @return
     */
    @RequestMapping(value = "/getOrderInfo")
    public String toFillOrderPage(Integer id, Model model){
        Ticket ticket = ticketService.getTicketById(id);
        model.addAttribute("ticket",ticket);
        return "user/order";
    }

    @PostMapping(value = "/submitOrderInfo")
    public String submitOrder(HttpServletRequest request, Order order, String name, Double price, Model model){
        order.setPaid(price*order.getNum());
        order.setNo(UUID.randomUUID().toString());
        order.setCode(UUID.randomUUID().toString());
        User user = (User)request.getSession().getAttribute("user");
        order.setUid(user.getUserid());
        orderService.insertOrder(order);
        model.addAttribute("order",order);
        model.addAttribute("mprice",price);
        model.addAttribute("name",name);
        return "user/orderInfo";
    }
}
