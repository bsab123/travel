package org.caps.travel;

import com.google.common.collect.Lists;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.caps.travel.entity.SolrResult;
import org.caps.travel.entity.User;
import org.caps.travel.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest(classes = TravelApplication.class)
public class SearchServiceTest {

    @Autowired
    private SolrClient solrClient;
    @Autowired
    private UserMapper userMapper;

    /**
     * 初始化 Solr
     */
    @Test
    public void testInitSolr() {
        List<User> solrResults = userMapper.selectAll();
        SolrInputDocument document = null;
        /*solrResults.forEach((solrResult ->document.addField() ));*/
        for (User solrResult : solrResults) {
            document = new SolrInputDocument();
            document.addField("id", solrResult.getUserid());
            document.addField("tr_username", solrResult.getUsername());
            document.addField("tr_password", solrResult.getPassword());
            document.addField("tr_name", solrResult.getName());
            document.addField("tr_email", solrResult.getEmail());
            document.addField("tr_telephone", solrResult.getTelephone());
            document.addField("tr_birthday", solrResult.getBirthday());
            document.addField("tr_sex", solrResult.getSex());
            document.addField("tr_state", solrResult.getState());
            document.addField("tr_code", solrResult.getCode());

            try {
                solrClient.add(document);
                solrClient.commit();
            } catch (SolrServerException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * 删除索引库
     */
    @Test
    public void testDeleteDocument() {
        try {
            solrClient.deleteByQuery("*:*");
            solrClient.commit();
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    /* *//**
     * 查询索引库
     *//*
    @Test
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
    }*/
}
