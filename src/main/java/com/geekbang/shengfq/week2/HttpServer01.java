package com.geekbang.shengfq.week2;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 单线程socket服务器
 * @author sheng
 * @date 2021-01-16
 * */
public class HttpServer01 {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket=new ServerSocket(8801);
        while(true){
           Socket socket= serverSocket.accept();
            service(socket);
        }
    }

    public static void service(Socket socket){
        try{
            Thread.sleep(100);
            PrintWriter printWriter=new PrintWriter(socket.getOutputStream(),true);
            printWriter.println();
            printWriter.write("hello");
            printWriter.close();
        }catch (IOException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}
