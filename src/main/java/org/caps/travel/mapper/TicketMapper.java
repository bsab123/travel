package org.caps.travel.mapper;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Ticket;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TicketMapper extends MyMapper<Ticket> {
    List<Ticket> selectTicketList(Integer id);
    //总条数
    Integer postCountByQueryVo(QueryVo vo);
    //结果集
    List<Ticket> selectPostListByQueryVo(QueryVo vo);
    void deleteById(Integer id);
    void addTicket(Ticket ticket);
    int updateTicket(Ticket ticket);
    Ticket getTicketById(Integer id);
    double selectPriceByFid(Integer id);
}