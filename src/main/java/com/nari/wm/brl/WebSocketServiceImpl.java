package com.nari.wm.brl;

import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Service
@ServerEndpoint(value = "/ws")
public class WebSocketServiceImpl {

    private static int  onlineNums = 0;

    private static CopyOnWriteArraySet<WebSocketServiceImpl> webSockets = new CopyOnWriteArraySet<>();

    private Session session;

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSockets.add(this);
        // 添加在线人数
        this.addOnlineCount();
        System.out.println("有新的连接加入啦！ 当前在线人数： "+this.getOnlineNums());

        try {
            this.sendMsg("有新人来啦，欢迎");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @OnClose
    public void onClose(){
        webSockets.remove(this);
        this.reduceOnlineNums();
        System.out.println("有人关闭了连接。 当前在线人数： "+this.getOnlineNums());
    }

    @OnMessage
    public void onMsg(String msg,Session session){
        System.out.println("来自"+session.getUserPrincipal().getName()+"的客户端的消息： "+msg);

        // 群发消息
        for(WebSocketServiceImpl item : WebSocketServiceImpl.webSockets){
            try {
                String name = item.session.getUserPrincipal().getName();
                System.out.println(name);
                item.sendMsg(msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @OnError
    public void onError(Session session,Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }


    /**
     * 发送消息
     * @param msg
     * @throws IOException
     */
    private void sendMsg(String msg) throws IOException {
        this.session.getBasicRemote().sendText(msg);
    }

    // 添加在线人数
    public synchronized void addOnlineCount(){
        WebSocketServiceImpl.onlineNums = WebSocketServiceImpl.onlineNums + 1;
    }
    // 查询在线人数
    public synchronized int getOnlineNums(){
        return WebSocketServiceImpl.onlineNums;
    }
    // 减少人数
    public synchronized void reduceOnlineNums(){
        WebSocketServiceImpl.onlineNums--;
    }


}
