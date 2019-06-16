package com.haohao.io.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AIOServer {

    private final Integer port;



    public static void main(String[] args) {
        int port = 8000;
        new AIOServer(port);
    }

    public AIOServer(Integer port) {
        this.port = port;
        listen();
    }

    private void listen() {
        try {
            ExecutorService executorService = Executors.newCachedThreadPool();
            AsynchronousChannelGroup asynchronousChannelGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 1);

            AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open(asynchronousChannelGroup);

            server.bind(new InetSocketAddress(port));
            System.out.println("服务已经启动:" + port);

            server.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>() {
                ByteBuffer buffer = ByteBuffer.allocate(1024);

                // 这个回调函数是由操作系统调用的;
                @Override
                public void completed(AsynchronousSocketChannel result, Object attachment) {

                    try {
                        System.out.println("IO 操作成功, 开始获取数据.");
                        buffer.clear();
                        result.read(buffer).get();
                        buffer.flip();
                        result.write(buffer);
                        buffer.flip();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            result.close();
                            server.accept(null, this);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("操作完成.");
                }

                @Override
                public void failed(Throwable exc, Object attachment) {
                    System.out.println("IO 操作失败.");
                }
            });

            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
