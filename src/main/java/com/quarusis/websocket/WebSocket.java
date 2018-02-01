package com.quarusis.websocket;

import com.quarusis.service.HomepageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/pagelist", configurator=GetHttpSessionConfigurator.class)
public class WebSocket {

    private Session session;
    private HttpSession httpSession;
    private ApplicationContext ctx = new ClassPathXmlApplicationContext("config/spring/*.xml");
    private HomepageService homepageService = (HomepageService) ctx.getBean("HomepageService");

    /**
     * 连接建立成功调用的方法
     * @param
     */
    @OnOpen
    public void onOpen(Session session,EndpointConfig config){
        this.session = session;
        httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        System.out.println("WebSocket建立连接");
    }

    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose(){
        System.out.println("WebSocket连接关闭");
    }

    /**
     * 向页面发送消息
     */
    @OnMessage
    public void onMessage(String message) {
        System.out.println("向页面发送消息");
        try {
            this.sendPageList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 发生错误时调用
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }

    /**
     * 向页面传递pagelist
     */
    public void sendPageList() throws Exception{
        httpSession.setAttribute("newpageList", homepageService.listPage((String) httpSession.getAttribute("uin")));
        this.session.getBasicRemote().sendText("1");
        System.out.println("已发送消息");
    }
}

