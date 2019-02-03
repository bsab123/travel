package org.caps.travel.mapper;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Scenic;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface ScenicMapper extends MyMapper<Scenic> {
    List<Scenic> selectScenicList();
    //总条数
    Integer postCountByQueryVo(QueryVo vo);
    //结果集
    List<Scenic> selectPostListByQueryVo(QueryVo vo);
    //新增
    void addScenic(Scenic scenic);

    public void deleteById(Integer id);

    int updateScenic(Scenic scenic);

    Scenic getScenicById(Integer id);

    int updateScenicSales(Integer id);
}