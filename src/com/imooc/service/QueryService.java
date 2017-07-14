package com.imooc.service;

import com.imooc.bean.Command;
import com.imooc.bean.CommandContent;
import com.imooc.bean.Message;
import com.imooc.dao.CommandDao;
import com.imooc.dao.MessageDao;
import com.imooc.util.ConstantMessage;

import java.util.List;
import java.util.Random;

/**
 * Created by peterchen on 2017/7/8.
 */
public class QueryService {
    public List<Message> getMessageList(String command, String description){
        MessageDao messageDao = new MessageDao();
        return messageDao.getMessageList(command,description);
    }
    /**
     * 通过指令查询自动回复的内容
     */
    public String queryByCommand(String command){
        CommandDao comandDao = new CommandDao();
        List<Command> commandList;
        if(ConstantMessage.HELP_COMMAND.equals(command)){
            commandList = comandDao.queryCommandList(null,null);
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<commandList.size();i++){
                if(i!=0)
                    sb.append("<br/>");
                sb.append("回复["+commandList.get(i).getName()+"]可以查看"+commandList.get(i).getDescription());
            }
            return sb.toString();
        }

        commandList = comandDao.queryCommandList(command,null);
        if(commandList.size()>0){
            List<CommandContent> contentList = commandList.get(0).getContentList();
            int i = new Random().nextInt(contentList.size());
            return contentList.get(i).getContent();
        }
        return ConstantMessage.NO_MATCHING_CONTENT;
    }
}
