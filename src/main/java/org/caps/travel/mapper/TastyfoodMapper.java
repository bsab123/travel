package org.caps.travel.mapper;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Tastyfood;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface TastyfoodMapper extends MyMapper<Tastyfood> {
    List<Tastyfood> selectFoodList();
    //总条数
    Integer postCountByQueryVo(QueryVo vo);
    //结果集
    List<Tastyfood> selectPostListByQueryVo(QueryVo vo);
    //新增
    void addFood(Tastyfood tastyFood);
    void deleteById(Integer id);
    int updateFood(Tastyfood tastyFood);

    Tastyfood getFoodById(Integer id);
}