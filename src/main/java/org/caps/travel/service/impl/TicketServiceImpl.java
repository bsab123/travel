package org.caps.travel.service.impl;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Ticket;
import org.caps.travel.mapper.TicketMapper;
import org.caps.travel.service.TicketService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    TicketMapper ticketMapper;

    @Value("${pageSize}")
    private Integer pageSize;

    @Override
    public List<Ticket> selectTicketListByFid(Integer fid) {
        return ticketMapper.selectTicketList(fid);
    }

    /**
     *后台获得分页数据
     *
     * @param vo
     * @return
     */
    @Override
    public Page<Ticket> selectTicketPageByQueryVo(QueryVo vo) {
        Page<Ticket> page = new Page<Ticket>();
        //每页数
        page.setSize(this.pageSize);
        vo.setSize(this.pageSize);
        if (null != vo) {
            // 判断当前页
            if (null != vo.getPage()) {
                page.setPage(vo.getPage());
                vo.setStartRow((vo.getPage() - 1) * vo.getSize());
            }
            if(null != vo.getName() && !"".equals(vo.getName().trim())){
                vo.setName(vo.getName().trim());
            }
            //总条数
            Integer totalCount  = ticketMapper.postCountByQueryVo(vo);
            page.setTotal(totalCount );
            //总页数
            Integer totalPage=(totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);

            page.setRows(ticketMapper.selectPostListByQueryVo(vo));
        }
        return page;
    }
    @Override
    public void deleteById(Integer id){
        ticketMapper.deleteById(id);
    }

    @Override
    public void addTicket(Ticket ticket){
        ticketMapper.addTicket(ticket);
    }

    @Override
    public int updateTicket(Ticket ticket){
        return ticketMapper.updateTicket(ticket);
    }

    @Override
    public Ticket getTicketById(Integer id){
        return ticketMapper.getTicketById(id);
    }

    @Override
    public double selectPriceByFid(Integer fid){
        return ticketMapper.selectPriceByFid(fid);
    }
}