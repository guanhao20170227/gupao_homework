package com.haohao.io.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

public class NIOClient {

    public static void main(String[] args) throws IOException {

        Socket socket = new Socket("localhost", 8080);

        OutputStream outputStream = socket.getOutputStream();

        String uuid = UUID.randomUUID().toString();

        System.out.println("客服端发送的数据:" + uuid);

        outputStream.write(uuid.getBytes());
        outputStream.close();
        socket.close();
    }
}
