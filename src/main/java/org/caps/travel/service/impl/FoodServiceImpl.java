package org.caps.travel.service.impl;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Tastyfood;
import org.caps.travel.mapper.TastyfoodMapper;
import org.caps.travel.service.FoodService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: FoodServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 14:00
 */
@Service
public class FoodServiceImpl implements FoodService {
    @Autowired
    private TastyfoodMapper tastyfoodMapper;

    @Override
    public List<Tastyfood> selectFoodList() {
        return tastyfoodMapper.selectAll();
    }

    @Override
    public Page<Tastyfood> selectFoodPageByQueryVo(QueryVo vo) {
        Page<Tastyfood> page = new Page<Tastyfood>();
        //每页数
        page.setSize(6);
        vo.setSize(6);
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
            page.setTotal(tastyfoodMapper.postCountByQueryVo(vo));
            page.setRows(tastyfoodMapper.selectPostListByQueryVo(vo));
        }
        return page;
    }

    @Override
    public void addFood(Tastyfood tastyFood) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public int updateFood(Tastyfood tastyFood) {
        return 0;
    }

    @Override
    public Tastyfood getFoodById(Integer id) {
        return null;
    }
}
