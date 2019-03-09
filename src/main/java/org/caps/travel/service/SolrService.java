package org.caps.travel.service;

import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import org.caps.travel.utils.Page;

import java.util.List;

/**
 * solr搜索
 */
public interface SolrService {
    public List<User> search(String query, int page, int rows);
}
