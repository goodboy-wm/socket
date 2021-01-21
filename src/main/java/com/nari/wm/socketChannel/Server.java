package com.nari.wm.socketChannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.SocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

public class Server {


    public static void main(String[] args) {

        try {
            Selector selector = Selector.open();  //初始化select

            ServerSocketChannel socketChannel = ServerSocketChannel.open();// socketChannel

            ServerSocket socket = socketChannel.socket(); //单例的socket
            socket.bind(new InetSocketAddress(999)); // 绑定端口
            socket.setReuseAddress(true);

            socketChannel.configureBlocking(true);// 采用非阻塞方式
            socketChannel.register(selector, SelectionKey.OP_ACCEPT); //注册监听事件 相当于accept()

            System.out.println("服务器： 等待连接");

            new Thread(new Runnable() {
                @Override
                public void run() {

                    while (true){

                        try {

                            while (true){

                                selector.select(); // 有几个，就是几个事件
                                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                                while (iterator.hasNext()){

                                    SelectionKey next = iterator.next();

                                    if(next.isAcceptable()); //是否可以接受新的连接

                                    else if(next.isReadable()){ //是否已经读过
                                        iterator.remove(); //处理完的时间要从 keys中去除
                                    }

                                }


                            }


                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }


                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
