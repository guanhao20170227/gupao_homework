package com.haohao.io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class NIOServer {

    private Integer port = 8080;

    // 构造 Selector, Buffer
    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NIOServer(Integer port) {

        try {
            this.port = port;
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.bind(new InetSocketAddress(this.port));
            // 手动声明是 非阻塞式的
            serverSocketChannel.configureBlocking(false);
            selector = Selector.open();

            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void listen() {
        System.out.println(" Listen on:" + this.port + ".");
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    process(key);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel socketChannel = serverSocketChannel.accept();
            socketChannel.configureBlocking(false);
            key = socketChannel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            int length = channel.read(buffer);
            if (length > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, length);
                channel.register(selector, SelectionKey.OP_WRITE);
                key.attach(content);
                System.out.println("读取内容:" + content);
            }
        } else if (key.isWritable()) {
            SocketChannel channel = (SocketChannel) key.channel();
            String attachment = (String) key.attachment();
            channel.write(ByteBuffer.wrap(("输出：" + attachment).getBytes()));
            channel.close();
        }
    }

    public static void main(String[] args) {
        new NIOServer(8080).listen();
    }


}
