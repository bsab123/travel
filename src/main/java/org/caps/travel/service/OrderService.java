package org.caps.travel.service;

import org.caps.travel.entity.Order;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.utils.Page;

import java.util.List;

public interface OrderService {
    Page<Order> selectOrderPageByQueryVo(QueryVo vo);
    List<Order> selectOrderListByFid(String fid);
    void updateStateById(Integer id);
    void updateStateToPayById(Integer id);
    int insertOrder(Order order);
    Order getOrderByNo(String no);
}
