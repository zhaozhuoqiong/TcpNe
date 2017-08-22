/*    */ package com.ztesoft.ne.tcp;
/*    */ 
/*    */ import io.netty.channel.ChannelHandler;
/*    */ import io.netty.channel.ChannelInitializer;
/*    */ import io.netty.channel.ChannelPipeline;
/*    */ import io.netty.channel.socket.SocketChannel;
/*    */ 
/*    */ public class TcpServerChildHandler extends ChannelInitializer<SocketChannel>
/*    */ {
/*    */   protected void initChannel(SocketChannel ch)
/*    */     throws Exception
/*    */   {
/* 11 */     ChannelPipeline pipeline = ch.pipeline();
/* 12 */     pipeline.addLast(new ChannelHandler[] { new TcpServerHandler() });
/*    */   }
/*    */ }