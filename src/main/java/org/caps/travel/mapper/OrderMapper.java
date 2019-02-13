package org.caps.travel.mapper;

import org.caps.travel.entity.Order;
import org.caps.travel.entity.QueryVo;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface OrderMapper extends MyMapper<Order> {
    List<Order> selectOrderList(String id);
    //总条数
    Integer postCountByQueryVo(QueryVo vo);
    //结果集
    List<Order> selectPostListByQueryVo(QueryVo vo);

    int updateStateById(Integer id);

    void updateStateToPayById(Integer id);

    int insertOrder(Order order);

    Order getOrderByNo(String no);
}