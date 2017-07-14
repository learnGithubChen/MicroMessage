package com.imooc.service;

import com.imooc.dao.MessageDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by peterchen on 2017/7/9.
 */
public class MaintainService {
    /**
     * 删除单条数据
     */
    public void deleteSingleMessage(String id){
        if(id!=null&&!"".equals(id.trim())){
            MessageDao messageDao = new MessageDao();
            messageDao.deleteSingleMessage(Integer.valueOf(id));
        }
    }
    /**
     * 删除多条数据
     */
    public void deleteBatchMessage(String[] ids){
        List<Integer> idsList = new ArrayList<>();
        for(String id:ids){
            idsList.add(Integer.valueOf(id));
        }
        MessageDao messageDao  = new MessageDao();
        messageDao.deleteBatchMessage(idsList);
    }
}
