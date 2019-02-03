package org.caps.travel.service;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Tastyfood;
import org.caps.travel.utils.Page;

import java.util.List;

/**
 * <p>Title: FoodService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 13:59
 */
public interface FoodService {
    List<Tastyfood> selectFoodList();
    Page<Tastyfood> selectFoodPageByQueryVo(QueryVo vo);
    //新增
    void addFood(Tastyfood tastyFood);
    void deleteById(Integer id);
    int updateFood(Tastyfood tastyFood);
    Tastyfood getFoodById(Integer id);
}
