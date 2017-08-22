/*    */ package com.ztesoft.ne.tcp;
/*    */ 
/*    */ import io.netty.bootstrap.ServerBootstrap;
/*    */ import io.netty.channel.Channel;
/*    */ import io.netty.channel.ChannelFuture;
/*    */ import io.netty.channel.ChannelOption;
/*    */ import io.netty.channel.EventLoopGroup;
/*    */ import io.netty.channel.nio.NioEventLoopGroup;
/*    */ import io.netty.channel.socket.nio.NioServerSocketChannel;
/*    */ import java.io.PrintStream;
/*    */ import java.util.Date;
/*    */ 
/*    */ public class TcpServer
/*    */ {
/* 16 */   protected static final int BIZGROUPSIZE = Runtime.getRuntime().availableProcessors() * 2;
/*    */   protected static final int BIZTHREADSIZE = 4;
/*    */ 
/*    */   public static void main(String[] args)
/*    */     throws Exception
/*    */   {
/* 21 */     int port = Integer.parseInt(args[0]);
/* 22 */     System.out.println("\t\t****************************************");
/* 23 */     System.out.println("\t\t*     ZTEsoft TCP  NE                  *");
/* 24 */     System.out.println("\t\t*     " + new Date() + "     *");
/* 25 */     System.out.println("\t\t*     VERSION : 1.0                    *");
/* 26 */     System.out.println("\t\t*     Port    : " + port + "                   *");
/* 27 */     System.out.println("\t\t****************************************");
/* 28 */     System.out.println("");
/* 29 */     run(port);
/*    */   }
/*    */ 
/*    */   public static void run(int port) throws Exception {
/* 33 */     EventLoopGroup bossGroup = new NioEventLoopGroup(BIZGROUPSIZE);
/* 34 */     EventLoopGroup workerGroup = new NioEventLoopGroup(4);
/*    */     try {
/* 36 */       ServerBootstrap b = new ServerBootstrap();
/* 37 */       b.group(bossGroup, workerGroup);
/* 38 */       b.channel(NioServerSocketChannel.class);
/* 39 */       b.option(ChannelOption.SO_BACKLOG, Integer.valueOf(1024));
/* 40 */       b.childHandler(new TcpServerChildHandler());
/*    */ 
/* 42 */       ChannelFuture f = b.bind(port).sync();
/* 43 */       f.channel().closeFuture().sync();
/*    */     }
/*    */     finally {
/* 46 */       workerGroup.shutdownGracefully();
/* 47 */       bossGroup.shutdownGracefully();
/*    */     }
/*    */   }
/*    */ }