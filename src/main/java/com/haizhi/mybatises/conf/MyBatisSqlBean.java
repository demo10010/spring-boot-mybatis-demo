//package com.haizhi.mybatises.conf;
//
//import com.haizhi.mybatises.dao.UserMapper;
//import org.apache.ibatis.mapping.BoundSql;
//import org.apache.ibatis.mapping.MappedStatement;
//import org.apache.ibatis.mapping.ParameterMapping;
//import org.apache.ibatis.mapping.ParameterMode;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.ibatis.session.Configuration;
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.apache.ibatis.type.TypeHandlerRegistry;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StringUtils;
//
//import javax.sql.DataSource;
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.text.SimpleDateFormat;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class MyBatisSqlBean {
//
//    @Value("${es.mapper-locations-dir}")
//    private String mapperLocationsDir;
//
//    @Value("${mybatis.mapper-locations}")
//    private String mapperDir;
//
//    @Value("${es.config-location}")
//    private String configLocations;
//
//    private static ThreadLocal<SimpleDateFormat> dateTimeFormatter = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//
//    public void mybatisStep() throws IOException {
//        String methodName = null;
//        try {
//            Method method = UserMapper.class.getMethod("Sel", Integer.TYPE);
//            Class<?> returnType = method.getReturnType();
//            Object result = null;
//            methodName = method.getName();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        }
//        String sqlId2 = UserMapper.class.getName() + "." + methodName;
//
//        Object parameter = null;
//        long startTime = System.currentTimeMillis();
//        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
//        Resource[] mappersResource = patternResolver.getResources(mapperDir);
//
//        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
//        DataSource dataSource = new DriverManagerDataSource();
//        ((DriverManagerDataSource) dataSource).setDriverClassName("com.mysql.cj.jdbc.Driver");
//        sessionFactory.setDataSource(dataSource);
//        ClassPathResource configResource = new ClassPathResource(configLocations);
//        System.out.println(configResource.getPath());
//        sessionFactory.setConfigLocation(configResource); //读取到配置文件，将文件以输入流的方式读取到内存中
//
//        sessionFactory.setMapperLocations(mappersResource); //读取到mappers文件，将文件以输入流的方式读取到内存中
//        SqlSessionFactory sqlSessionFactory = null;
//        try {
//            sqlSessionFactory = sessionFactory.getObject();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        SqlSession sqlSession = sqlSessionFactory.openSession();
//
//        Configuration factoryConfiguration = sqlSessionFactory.getConfiguration();
//        MappedStatement mappedStatement = factoryConfiguration.getMappedStatement(sqlId2);
//        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//        Configuration configuration = mappedStatement.getConfiguration();
//        String sql = getSql(configuration, boundSql);
//        long endTime = System.currentTimeMillis();
//
//        long sqlCostTime = endTime - startTime;
//        System.out.println(sql);
//
//    }
//
//    private static String getSql(Configuration configuration, BoundSql boundSql) {
//        // 输入sql字符串空判断
//        String sql = boundSql.getSql();
//        if (StringUtils.isEmpty(sql)) {
//            return "";
//        }
//        return formatSql(sql, configuration, boundSql);
//    }
//
//
//    private static String formatSql(String sql, Configuration configuration, BoundSql boundSql) {
//        //填充占位符, 目前基本不用mybatis存储过程调用,故此处不做考虑
//        Object parameterObject = boundSql.getParameterObject();
//        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
//        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
//
//        List<String> parameters = new ArrayList<>();
//        if (parameterMappings != null) {
//            MetaObject metaObject = parameterObject == null ? null : configuration.newMetaObject(parameterObject);
//            for (int i = 0; i < parameterMappings.size(); i++) {
//                ParameterMapping parameterMapping = parameterMappings.get(i);
//                if (parameterMapping.getMode() != ParameterMode.OUT) {
//                    //  参数值
//                    Object value;
//                    String propertyName = parameterMapping.getProperty();
//                    //  获取参数名称
//                    if (boundSql.hasAdditionalParameter(propertyName)) {
//                        // 获取参数值
//                        value = boundSql.getAdditionalParameter(propertyName);
//                    } else if (parameterObject == null) {
//                        value = null;
//                    } else if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
//                        // 如果是单个值则直接赋值
//                        value = parameterObject;
//                    } else {
//                        value = metaObject == null ? null : metaObject.getValue(propertyName);
//                    }
//
//                    if (value instanceof Number) {
//                        parameters.add(String.valueOf(value));
//                    } else {
//                        StringBuilder builder = new StringBuilder();
//                        builder.append("'");
//                        if (value instanceof Date) {
//                            builder.append(dateTimeFormatter.get().format((Date) value));
//                        } else if (value instanceof String) {
//                            builder.append(value);
//                        }
//                        builder.append("'");
//                        parameters.add(builder.toString());
//                    }
//                }
//            }
//        }
//
//        for (String value : parameters) {
//            sql = sql.replaceFirst("\\?", value);
//        }
//        return sql;
//    }
//
//    private static String formatSqlLog(String sql, long costTime, Object obj) {
//        String sqlLog = "==> " + sql;
//        StringBuffer result = new StringBuffer();
//        if (obj instanceof List) {
//            List list = (List) obj;
//            int count = list.size();
//            result.append("<==      Total: " + count);
//        } else if (obj instanceof Integer) {
//            result.append("<==      Total: " + obj);
//        }
//
//        result.append("      Spend Time ==> " + costTime + " ms");
//        System.err.println("输出的sql为 ======》》》" + sqlLog + "    " + result.toString());
//        return sqlLog;
//    }
//
//}
