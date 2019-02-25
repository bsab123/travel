package org.caps.travel.service.impl;

import io.swagger.models.auth.In;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.Scenic;
import org.caps.travel.mapper.ScenicMapper;
import org.caps.travel.service.ScenicService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: ScenicServiceImpl</p>
 * <p>Description: </p>
 *
 * @author caps
 * @version 1.0.0
 * @Date 2019/1/24 14:01
 */
@Service
public class ScenicServiceImpl implements ScenicService {
    @Autowired
    private ScenicMapper scenicMapper;
    @Value("${pageSize}")
    private Integer pageSize;

    //查询所有景点
    @Override
    public List<Scenic> selectScenicList() {
        List<Scenic> scenics = scenicMapper.selectAll();
        return scenics;
    }

    @Override
    public Page<Scenic> selectPageByQueryVo(QueryVo vo) {
        Page<Scenic> page = new Page<Scenic>();
        //每页数
        page.setSize(this.pageSize);
        vo.setSize(this.pageSize);
        
        if (null != vo) {
            // 判断当前页
            if (null != vo.getPage()) {
                //返回给前端的当页码
                page.setPage(vo.getPage());
                //计算出要查询的数据从第几条开始
                vo.setStartRow((vo.getPage() - 1) * vo.getSize());
            }
            if(null != vo.getName() && !"".equals(vo.getName().trim())){
                vo.setName(vo.getName().trim());
            }
            if(null != vo.getTheme() && !"".equals(vo.getTheme().trim())){
                vo.setTheme(vo.getTheme().trim());
            }
            if(null != vo.getAddr() && !"".equals(vo.getAddr().trim())){
                vo.setAddr(vo.getAddr().trim());
            }
            //总条数
            Integer totalCount  = scenicMapper.postCountByQueryVo(vo);
            page.setTotal(totalCount );
            //总页数
            Integer totalPage=(totalCount + page.getSize() - 1) / page.getSize();
            page.setTotalPage(totalPage);
            //返回给页码的结果集
            page.setRows(scenicMapper.selectPostListByQueryVo(vo));
        }
        return page;
    }

    @Override
    public void addScenic(Scenic scenic) {
        scenicMapper.addScenic(scenic);
    }

    @Override
    public void deleteById(Integer id) {
        scenicMapper.deleteById(id);
    }

    @Override
    public int updateScenic(Scenic scenic) {
        return scenicMapper.updateScenic(scenic);
    }

    @Override
    public Scenic getScenicById(Integer id) {
        return scenicMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateScenicSales(Integer id) {
        return scenicMapper.updateScenicSales(id);
    }
}
