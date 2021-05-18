package com.geekbang.shengfq.week07.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoDBJDBC{
   public static void main( String args[] ){
      try{
       // 连接到 mongodb 服务
         MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

         // 连接到数据库
         MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
       System.out.println("Connect to database successfully");
          MongoCollection<Document> collection= mongoDatabase.getCollection("test");
          FindIterable<Document> iterable=collection.find();

      }catch(Exception e){
        System.err.println( e.getClass().getName() + ": " + e.getMessage() );
     }
   }
}
