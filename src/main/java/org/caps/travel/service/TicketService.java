package org.caps.travel.service;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Ticket;
import org.caps.travel.utils.Page;

import java.util.List;

/**
 * <p>Title: TicketService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 14:05
 */
public interface TicketService {
    Page<Ticket> selectTicketPageByQueryVo(QueryVo vo);
    List<Ticket> selectTicketListByFid(Integer fid);
    double selectPriceByFid(Integer fid);
    void deleteById(Integer id);
    void addTicket(Ticket ticket);
    int updateTicket(Ticket ticket);
    Ticket getTicketById(Integer id);
}
