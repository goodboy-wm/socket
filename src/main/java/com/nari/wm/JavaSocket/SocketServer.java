package com.nari.wm.JavaSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServer {

    public static void main(String[] args) {

        try {
            ServerSocket  serverSocket = new ServerSocket(9999);

            ExecutorService executorService = Executors.newFixedThreadPool(100);

            while (true){

                // 阻塞等待连接
                Socket socket = serverSocket.accept();

               Runnable runnable = new Runnable() {
                    @Override
                    public void run() {
                        BufferedReader bufferedReader = null;
                        try {
                            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            String content;

                            while ((content = bufferedReader.readLine()) != null) {
                                System.out.println(Thread.currentThread().getName()+"说： "+content);
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }
                };

                executorService.execute(runnable);

            }




        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
