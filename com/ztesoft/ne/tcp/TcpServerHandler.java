/*     */ package com.ztesoft.ne.tcp;
/*     */ 
/*     */ import io.netty.buffer.ByteBuf;
/*     */ import io.netty.buffer.Unpooled;
/*     */ import io.netty.channel.ChannelHandlerAdapter;
/*     */ import io.netty.channel.ChannelHandlerContext;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileReader;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ 
/*     */ public class TcpServerHandler extends ChannelHandlerAdapter
/*     */ {
/*  18 */   private static final String str = getRespStr("message/rtn.txt");
/*     */ 
/*     */   public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
/*     */     throws Exception
/*     */   {
/*  23 */     ctx.close();
/*     */   }
/*     */ 
/*     */   public void channelActive(ChannelHandlerContext ctx)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   public void channelInactive(ChannelHandlerContext ctx)
/*     */     throws Exception
/*     */   {
/*     */   }
/*     */ 
/*     */   public void channelRead(ChannelHandlerContext ctx, Object msg)
/*     */     throws Exception
/*     */   {
/*  58 */     ByteBuf buf = Unpooled.buffer(str.getBytes().length);
/*  59 */     buf.writeBytes(str.getBytes());
/*  60 */     ctx.write(buf);
/*     */   }
/*     */ 
/*     */   public void channelReadComplete(ChannelHandlerContext ctx)
/*     */     throws Exception
/*     */   {
/*  66 */     ctx.flush();
/*     */   }
/*     */ 
/*     */   public static String getRespStr(String filePath)
/*     */   {
/*  71 */     String fileName = filePath;
/*     */ 
/*  73 */     FileReader input = null;
/*     */ 
/*  75 */     ArrayList lists = new ArrayList();
/*     */ 
/*  77 */     String s = "";
/*     */ 
/*  79 */     char[] buffer = new char[1024];
/*     */     try {
/*  81 */       input = new FileReader(fileName);
/*  82 */       int k = 0;
/*  83 */       while ((k = input.read(buffer, 0, 1024)) > 0) {
/*  84 */         char[] bufferItems = new char[k];
/*     */ 
/*  86 */         System.arraycopy(buffer, 0, bufferItems, 0, k);
/*  87 */         lists.add(bufferItems);
/*     */       }
/*     */ 
/*  90 */       s = getRequest(lists);
/*     */     }
/*     */     catch (FileNotFoundException e) {
/*  93 */       e.printStackTrace();
/*     */     }
/*     */     catch (IOException e) {
/*  96 */       e.printStackTrace();
/*     */     }
/*     */ 
/*  99 */     return s;
/*     */   }
/*     */ 
/*     */   public static String getRequest(ArrayList bufferList) {
/* 103 */     StringBuffer retn = new StringBuffer();
/* 104 */     if (bufferList != null) {
/* 105 */       Iterator iter = bufferList.iterator();
/*     */ 
/* 107 */       while ((iter != null) && (iter.hasNext())) {
/* 108 */         Object o = iter.next();
/*     */ 
/* 110 */         String s = "";
/* 111 */         if (o instanceof byte[]) {
/* 112 */           byte[] buffer = (byte[])o;
/*     */ 
/* 114 */           s = new String(buffer);
/*     */         }
/*     */ 
/* 117 */         if (o instanceof char[]) {
/* 118 */           char[] buffer = (char[])o;
/*     */ 
/* 120 */           s = new String(buffer);
/*     */         }
/*     */ 
/* 123 */         retn.append(s);
/*     */       }
/*     */     }
/*     */ 
/* 127 */     return retn.toString();
/*     */   }
/*     */ }