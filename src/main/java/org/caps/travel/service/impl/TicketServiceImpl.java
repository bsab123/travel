package org.caps.travel.service.impl;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Ticket;
import org.caps.travel.mapper.TicketMapper;
import org.caps.travel.service.TicketService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: TicketServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 14:05
 */
@Service
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketMapper ticketMapper;
    @Override
    public Page<Ticket> selectTicketPageByQueryVo(QueryVo vo) {
        return null;
    }

    @Override
    public List<Ticket> selectTicketListByFid(Integer fid) {
        return ticketMapper.selectTicketList(fid);
    }

    @Override
    public double selectPriceByFid(Integer fid) {
        return ticketMapper.selectPriceByFid(fid);
    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public void addTicket(Ticket ticket) {

    }

    @Override
    public int updateTicket(Ticket ticket) {
        return 0;
    }

    @Override
    public Ticket getTicketById(Integer id) {
        return ticketMapper.getTicketById(id);
    }
}
