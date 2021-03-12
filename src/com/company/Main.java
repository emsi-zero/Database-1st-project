package com.company;

import redis.clients.jedis.Jedis;

public class Main {


    class DBHandler{
        Jedis jedis;
        DBHandler(String host){
            jedis = new Jedis(host);
        }
    }


    public static void main(String[] args) {

    }
}
