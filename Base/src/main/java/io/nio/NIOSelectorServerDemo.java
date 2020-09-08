package io.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @Description:
 * @Author:BigRedCaps
 */
public class NIOSelectorServerDemo
{
    // 多路复用器（相当于管理所有连接的注册中心）
    static Selector selector;
    public static void main (String[] args)
    {
        try
        {
            // 创建一个多路复用器
            selector = Selector.open();
            // 构建一个网络通道
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            // 在多路复用器模式下，必须要设置为非阻塞(连接的非阻塞)；
            /**
             * 因为如果设置为阻塞模式，那么只轮询一个，就阻塞了，那Selector也就没啥意义了
             */
            serverSocketChannel.configureBlocking(false);
            // 注册服务的监听端口
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 把当前的serverSocketChannel注册到多路服用器上，监听连接事件
            /**
             * 正常来说，这里因该是SocketChannel socketChannel = serverSocketChannel.accept();
             * 没有连接时，返回null;但这里把serverSocketChannel注册到selector上，接下来就是通过
             * 多路复用器不断的去轮询了
             */
            /*
             * 这里每次Channel向selector注册的时候，都会返回一个SelectionKey,SelectionKey代表创建该通道的唯一标志
             */
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(true)
            {
                // 当事件(这里指连接事件)没有到达前，这个方法时阻塞着的；
                /**
                 * 阻塞所有注册到多路复用器上的事件，一旦某个事件就绪，就会返回；
                 * 一旦返回，就可以用Set<SelectionKey> selectionKeys = selector.selectedKeys();接收
                 * SelectionKey代表该Channel唯一的
                 */
                selector.select();
                // 返回注册到复路器上的所有的Channel，一旦某个Channel准备就绪就返回这个Channel对应的唯一的一个
                // SelectionKey，如果有多个Channel，就返回一个集合
                Set<SelectionKey> selectionKeys = selector.selectedKeys();

                //迭代
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while(iterator.hasNext())
                {
                    SelectionKey selectionKey = iterator.next();
                    // 拿到key后，要移除掉，避免重复处理
                    iterator.remove();
                    // 拿到key,就表示获取到了当前key对应的通道
                    if(selectionKey.isAcceptable()){// 获取到连接事件
                        // 处理连接事件
                        handlerAccept(selectionKey);
                    }else if(selectionKey.isReadable())// 获取到读事件
                    {
                        // 处理读事件
                        handlerRead(selectionKey);
                    }
                }
            }

        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private static void handlerAccept(SelectionKey selectionKey) throws IOException
    {
        // 获取seletionKey对应的Channel
        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
        // 是不是一定存在客户端连接过来，是的，这里一定不会返回空
        SocketChannel socketChannel = serverSocketChannel.accept();

        // 设置IO的非阻塞
        socketChannel.configureBlocking(false);
        // 写数据
        socketChannel.write(ByteBuffer.wrap("Hello Client,I am NIO Server with Selector ".getBytes()));
        // 这里注册的时SocketChannel的读事件
        socketChannel.register(selector,SelectionKey.OP_READ);

    }

    private static void handlerRead(SelectionKey selectionKey) throws IOException
    {
        SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        socketChannel.read(byteBuffer);
        System.out.println("server receive Message:"+new String(byteBuffer.array()));

    }
}
