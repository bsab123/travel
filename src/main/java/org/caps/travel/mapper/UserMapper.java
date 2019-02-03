package org.caps.travel.mapper;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

public interface UserMapper extends MyMapper<User> {
    //总条数
    public Integer customerCountByQueryVo(QueryVo vo);
    //结果集
    public List<User> selectCustomerListByQueryVo(QueryVo vo);
}