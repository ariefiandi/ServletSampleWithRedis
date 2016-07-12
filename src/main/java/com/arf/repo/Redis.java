/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.arf.repo;

import redis.clients.jedis.Jedis;

/**
 *
 * @author AriefiandiN
 */
public class Redis {
    
    private Jedis connection;

    public Jedis getConnection() {
        return connection;
    }
    
    public Redis() {
        try{
            this.connection = new Jedis("localhost", 6379);
            System.out.println("Connection to server sucessfully");
            //check whether server is running or not
            System.out.println("Server is running: "+ this.connection.ping());
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
    
    public boolean Insert(String Key,String Content){
        boolean result = false;
        try{
            this.connection.lpush(Key, Content);
            result = true;
        }
        catch(Exception e){
            System.err.println(e);  
        }
        finally{
            return result;
        }
    }
    
    public void Close(){
        try{
            this.connection.close();
        }
        catch(Exception e){
            System.err.println(e);
        }
    }
    
}
