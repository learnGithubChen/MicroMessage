package com.imooc.dao;

import com.imooc.bean.Message;
import com.imooc.db.DBAccess;
import org.apache.ibatis.session.SqlSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by peterchen on 2017/7/8.
 */
public class MessageDao {
    public List<Message> getMessageList(String command, String description){

        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        List<Message> message1s = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            Message message1 = new Message();
            message1.setCommand(command);
            message1.setDescription(description);
            message1s = sqlSession.selectList("queryMessageList", message1);
            return message1s;

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
        return null;
    }
    /**
     * 删除单条数据
     */
        public void deleteSingleMessage(int id){
            DBAccess dbAccess = new DBAccess();
            SqlSession sqlSession = null;
            try {
                sqlSession = dbAccess.getSqlSession();
                sqlSession.delete("deleteSingle",id);
                sqlSession.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(sqlSession!=null){
                    sqlSession.close();
                }
            }
        }
    /**
     * 删除多条数据
     */
    public void deleteBatchMessage(List<Integer> ids){
        DBAccess dbAccess = new DBAccess();
        SqlSession sqlSession = null;
        try {
            sqlSession = dbAccess.getSqlSession();
            sqlSession.delete("deleteBatch",ids);
            sqlSession.commit();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(sqlSession!=null){
                sqlSession.close();
            }
        }
    }

    /*public List<Message> getMessageList(String command,String description){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message","root","mysql123");
            StringBuilder sql = new StringBuilder("select ID,COMMAND,DESCRIPTION,CONTENT from message where 1 = 1");
            List<String> paramList = new ArrayList<>();
            if(command!=null&&!"".equals(command.trim())){
                sql.append(" and COMMAND=? ");
                paramList.add(command);
            }
            if(description!=null&&!"".equals(description.trim())){
                sql.append(" and DESCRIPTION like '%' ? '%'");
                paramList.add(description);
            }
            PreparedStatement ps = connection.prepareStatement(sql.toString());
            for(int i=0;i<paramList.size();i++){
                ps.setString(i+1,paramList.get(i));
            }
            ResultSet rs = ps.executeQuery();
            List<Message> messages = new ArrayList<>();
            while (rs.next()){
                Message message = new Message();
                messages.add(message);
                message.setId(rs.getString("ID"));
                message.setCommand(rs.getString("COMMAND"));
                message.setDescription(rs.getString("DESCRIPTION"));
                message.setContent(rs.getString("CONTENT"));
            }
            return messages;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
    }*/
}
