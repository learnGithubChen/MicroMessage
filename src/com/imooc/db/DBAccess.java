package com.imooc.db;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;


/**
 * Created by peterchen on 2017/7/8.
 */
public class DBAccess {
    public SqlSession getSqlSession() throws IOException {
        //通过配置文件获取数据库信息
       Reader reader= Resources.getResourceAsReader("com/imooc/config/Configuration.xml");
       //通过配置信息构建一个SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        //通过SqlSessionFactory打开一个数据库会话
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
}
