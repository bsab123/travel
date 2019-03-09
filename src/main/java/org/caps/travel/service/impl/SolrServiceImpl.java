package org.caps.travel.service.impl;

import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.caps.travel.entity.QueryVo;
import org.caps.travel.entity.User;
import org.caps.travel.mapper.UserMapper;
import org.caps.travel.service.SolrService;
import org.caps.travel.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
public class SolrServiceImpl implements SolrService {

    @Autowired
    private SolrClient solrClient;

    @Override
    public List<User> search(String query, int page, int rows) {
        List<User> searchResults = Lists.newArrayList();

        // 创建查询对象
        SolrQuery solrQuery = new SolrQuery();

        // 设置查询条件
        solrQuery.setQuery(query);

        // 设置分页条件
        solrQuery.setStart((page - 1) * rows);
        solrQuery.setRows(rows);

        // 设置默认搜索域
        solrQuery.set("df", "tb_item_keywords");

        // 设置高亮显示
        solrQuery.setHighlight(true);
        solrQuery.addHighlightField("tr_username");
        solrQuery.setHighlightSimplePre("<span style='color:red'>");
        solrQuery.setHighlightSimplePost("</span>");

        try {
            // 执行查询操作
            QueryResponse queryResponse = solrClient.query(solrQuery);
            SolrDocumentList solrDocuments = queryResponse.getResults();
            Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
            for (SolrDocument solrDocument : solrDocuments) {

                User result = new User();
                result.setUserid(String.valueOf(solrDocument.get("id")));
                result.setUsername(String.valueOf(solrDocument.get("tr_username")));
                result.setPassword(String.valueOf(solrDocument.get("tr_password")));
                result.setName(String.valueOf(solrDocument.get("tr_name")));
                result.setEmail(String.valueOf(solrDocument.get("tr_email")));
                result.setTelephone(String.valueOf(solrDocument.get("tr_telephone")));
                result.setSex(String.valueOf(solrDocument.get("tr_sex")));
                result.setState(Integer.parseInt(String.valueOf(solrDocument.get("tr_state"))));
                result.setCode(String.valueOf(solrDocument.get("tr_code")));


                String username = "";
                List<String> list = highlighting.get(solrDocument.get("id")).get("tr_username");
                if (list != null && list.size() > 0) {
                    username = list.get(0);
                } else {
                    username = (String) solrDocument.get("tr_username");
                }
                result.setUsername(username);

                searchResults.add(result);
            }
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return searchResults;
    }
}
