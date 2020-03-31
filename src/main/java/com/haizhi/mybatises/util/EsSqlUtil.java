package com.haizhi.mybatises.util;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.ElasticSearchDruidDataSourceFactory;
import com.google.gson.Gson;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.search.SearchHit;
import org.nlpcn.es4sql.SearchDao;
import org.nlpcn.es4sql.query.SqlElasticSearchRequestBuilder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class EsSqlUtil {

    public static <T> List<T> queryBySql(TransportClient tClient, String sql, Class<T> resultType) {
        System.err.println(sql);
        //执行sql查询
        try {
            //创建sql查询对象
            SearchDao searchDao = new SearchDao(tClient);
            //"select * from teacher where teacherId = 2"
            SqlElasticSearchRequestBuilder select = (SqlElasticSearchRequestBuilder) searchDao.explain(sql).explain();
            ActionResponse response = select.get();
            if (response instanceof SearchResponse) {
                SearchResponse searchResponse = (SearchResponse) response;
                SearchHit[] hits = searchResponse.getHits().getHits();
                List<T> collect = Arrays.asList(hits).stream().map(hit -> {
                            Gson gson = new Gson();
                            return gson.fromJson(hit.getSourceAsString(), resultType);
                        }
                ).collect(Collectors.toList());
                return collect;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
//            transportClient.close();
        }
        return null;
    }

    private static final String TEST_INDEX = "my_test";

    public static void testJDBC() throws Exception {
        Properties properties = new Properties();
        properties.put("url", "jdbc:elasticsearch://49.233.208.207:9300/" + TEST_INDEX);
        DruidDataSource dds = (DruidDataSource) ElasticSearchDruidDataSourceFactory.createDataSource(properties);
        Connection connection = dds.getConnection();
        PreparedStatement ps = connection.prepareStatement("SELECT * from  " + TEST_INDEX + " where id ='1'");
        ResultSet resultSet = ps.executeQuery();
        List<String> result = new ArrayList<>();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("id") + "," + resultSet.getInt("name") + "," + resultSet.getString("sex"));
        }
        ps.close();
        connection.close();
        dds.close();
    }

    public static void main(String[] args) {
        try {
            testJDBC();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
