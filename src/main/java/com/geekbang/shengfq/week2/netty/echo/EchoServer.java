package com.geekbang.shengfq.week2.netty.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * netty http服务端启动
 */
public final class EchoServer {
    private static final Logger logger = LoggerFactory.getLogger(EchoServer.class);
    static final int PORT = Integer.parseInt(System.getProperty("port", "8007"));
    // 配置服务器端的线程模型Reactor
    private final EventLoopGroup bossGroup = new NioEventLoopGroup(1);
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();
    //服务器的运行状态
    private volatile boolean isRunning=false;




    public static void main(String[] args) throws Exception {
        EchoServer server=new EchoServer();
        try {
            server.start();
        }catch(Exception e){
            logger.error("error..",e);
        }
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run(){
                server.stop();
            }
        });

    }

    /**
     * 启动服务器
     * */
    private synchronized  void start() throws Exception{
        try {
            logger.info("init...");
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new EchoChannelInitializer());

            // Start the server.
            ChannelFuture f = b.bind(PORT).sync();
            logger.info("start server...");
            // Wait until the server socket is closed.
            f.channel().closeFuture().sync();
            isRunning=true;
            logger.info("Wait until the server socket is closed...");
        } finally {
            // Shut down all event loops to terminate all threads.
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
            logger.info("server gracefully shutdown...");
        }
    }

    private synchronized void stop(){
        if (!this.isRunning) {
            throw new IllegalStateException("服务 未启动 .");
        }
        this.isRunning = false;
        try {
            Future<?> future = this.workerGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                logger.error("workerGroup 无法正常停止:{}", future.cause());
            }

            future = this.bossGroup.shutdownGracefully().await();
            if (!future.isSuccess()) {
                logger.error("bossGroup 无法正常停止:{}", future.cause());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Echo服务已经停止...");
    }
}
