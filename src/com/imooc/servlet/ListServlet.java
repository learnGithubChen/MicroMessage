package com.imooc.servlet;

import com.imooc.service.QueryService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by peterchen on 2017/7/8.
 */
public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //设置编码格式
        req.setCharacterEncoding("UTF-8");
        //获取command和description参数
        String command = req.getParameter("command");
        String description = req.getParameter("description");
        req.getSession().setAttribute("command",command);
        req.getSession().setAttribute("description",description);



        //将返回值放入request中
        QueryService listService = new QueryService();
        req.setAttribute("messageList",listService.getMessageList(command,description));

        //返回响应页面
        req.getRequestDispatcher("WEB-INF/jsp/back/list.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
