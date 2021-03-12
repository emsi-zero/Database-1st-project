package com.company;

import redis.clients.jedis.Jedis;

public class Main {


    class DBHandler{
        Jedis jedis;
        DBHandler(String host){
            jedis = new Jedis(host);
        }

        public boolean create(String key, String value){

            return false;
        }

        public boolean fetch(String key){

            return false;
        }

        public boolean update(String key, String value){

            return false;
        }

        public boolean delete(String key){

            return false;
        }

    }


    public static void main(String[] args) {

    }
}
