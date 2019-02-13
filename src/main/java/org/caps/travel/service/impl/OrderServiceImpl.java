package org.caps.travel.service.impl;

import org.caps.travel.entity.Order;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.mapper.OrderMapper;
import org.caps.travel.service.OrderService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

   /* public List<Order> selectOrderListByFid(String tid) {
        return orderMapper.selectOrderList(tid);
    }*/

    @Override
    public Page<Order> selectOrderPageByQueryVo(QueryVo vo) {
        return null;
    }

    @Override
    public List<Order> selectOrderListByFid(String fid) {
        return null;
    }

    @Override
    public void updateStateById(Integer id) {

    }

    @Override
    public void updateStateToPayById(Integer id) {

    }

    /**
     *后台获得分页数据
     *
     * @param vo
     * @return
     */
/*    public Page<Order> selectOrderPageByQueryVo(QueryVo vo) {
        Page<Order> page = new Page<Order>();
        //每页数
        page.setSize(5);
        vo.setSize(5);
        if (null != vo) {
            // 判断当前页
            if (null != vo.getPage()) {
                page.setPage(vo.getPage());
                vo.setStartRow((vo.getPage() - 1) * vo.getSize());
            }
            if(null != vo.getName() && !"".equals(vo.getName().trim())){
                vo.setName(vo.getName().trim());
            }
            if(null !=vo.getUserid()&&!"".equals(vo.getUserid().trim())){
                vo.setUserid(vo.getUserid().trim());
            }
            //总条数
            page.setTotal(orderMapper.postCountByQueryVo(vo));
            page.setRows(orderMapper.selectPostListByQueryVo(vo));
        }
        return page;
    }

    public void updateStateById(Integer id){
        orderMapper.updateStateById(id);
    }

    public void updateStateToPayById(Integer id){
        orderMapper.updateStateToPayById(id);
    }*/

    @Override
    public int insertOrder(Order order){
        return orderMapper.insertOrder(order);
    }

    @Override
    public Order getOrderByNo(String no) {
        return null;
    }

  /*  public Order getOrderByNo(String no){
        return orderMapper.getOrderByNo(no);
    }*/
}