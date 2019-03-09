package org.caps.travel.mapper;

import org.apache.solr.client.solrj.SolrClient;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.SolrResult;
import org.caps.travel.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

@Repository
public interface UserMapper extends MyMapper<User> {
    //总条数
     Integer customerCountByQueryVo(QueryVo vo);
    //结果集
     List<User> selectCustomerListByQueryVo(QueryVo vo);
    //原生mapper，用于solr
    List<SolrResult> selectSolrAll();

}