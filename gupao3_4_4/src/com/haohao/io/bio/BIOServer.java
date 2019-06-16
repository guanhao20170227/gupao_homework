package com.haohao.io.bio;



import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *  基础的 IO 模型
 */

public class BIOServer {

    ServerSocket serverSocket;

    public BIOServer(int port) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println("BIO 服务已经启动, 端口是:" + port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void listen() throws IOException {

        // 一直处于监听的状态
        while (true) {
            Socket client = serverSocket.accept();
            System.out.println("服务器端口:" + client.getPort());

            InputStream inputStream = client.getInputStream();
            byte [] buff = new byte[1024];
            int length = inputStream.read(buff);
            if (length > 0) {
                String msg = new String(buff, 0, length);
                System.out.println("收到的消息:" + msg);
            }
        }


    }

    public static void main(String[] args) throws IOException {
        new BIOServer(8080).listen();
    }

}
