package com.haizhi.mybatises.interceptor;

import com.alibaba.druid.support.json.JSONUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.plugin.*;

import java.util.Properties;

/**
 * 定义插件StatementHandlerInterceptor
 * 告诉MyBatis当前插件用来拦截哪个对象的哪个方法
 */
@Intercepts({@Signature(type=StatementHandler.class,method="parameterize",args=java.sql.Statement.class)})
public class StatementHandlerInterceptor implements Interceptor {

    /**
     * 拦截目标对象的目标方法的执行
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 执行目标方法
        Object proceed = invocation.proceed();
        System.err.println(JSONUtils.toJSONString(proceed));
        // 返回执行后的返回值
        return proceed;
    }

    /**
     * 包装目标对象 包装：为目标对象创建一个代理对象
     * 根据当前拦截的对象创建了一个动态代理对象。代理对象的InvocationHandler处理器为新建的Plugin对象
     * 官方推荐实现方式：Plugin.wrap(target, this);
     */
    @Override
    public Object plugin(Object target) {
        // 我们可以借助Plugin的wrap方法来使用当前Interceptor包装我们目标对象

        System.err.println("StatementHandlerInterceptor...plugin:mybatis将要包装的对象" + JSONUtils.toJSONString(target));
        Object wrap = Plugin.wrap(target, this);
        // 返回为当前target创建的动态代理
        return wrap;
    }

    /**
     * 将插件注册时的property属性设置进来
     */
    @Override
    public void setProperties(Properties properties) {
        System.err.println("插件配置的信息："+ JSONUtils.toJSONString(properties));
    }

}
