package org.caps.travel.service;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Scenic;
import org.caps.travel.utils.Page;

import java.util.List;

/**
 * <p>Title: ScenicService</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 13:59
 */
public interface ScenicService {
    List<Scenic> selectScenicList();
    Page<Scenic> selectPageByQueryVo(QueryVo vo);
    //新增
    void addScenic(Scenic scenic);
    public void deleteById(Integer id);
    int updateScenic(Scenic scenic);
    Scenic getScenicById(Integer id);
    int updateScenicSales(Integer id);
}
