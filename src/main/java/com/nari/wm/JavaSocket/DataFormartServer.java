package com.nari.wm.JavaSocket;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DataFormartServer {


    public static void main(String[] args) {

        try {

            ServerSocket serverSocket = new ServerSocket(9999);

            ExecutorService executorService = Executors.newFixedThreadPool(20);

            while (true){

                Socket socket = serverSocket.accept();

                Runnable runnable = new Runnable(){
                    @Override
                    public void run() {
                        try {
                            InputStream inputStream = socket.getInputStream();
                            DataInputStream dataInputStream = new DataInputStream(inputStream);

                            byte type = dataInputStream.readByte();
                            int len = dataInputStream.readInt();

                            byte [] data = new byte[len - 5];
                            dataInputStream.readFully(data);
                            String dataString = new String(data);

                            System.out.println("类别"+type+" "+"长度："+len+" "+"数据："+dataString);

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
