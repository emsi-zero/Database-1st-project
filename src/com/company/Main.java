package com.company;

import redis.clients.jedis.Jedis;

public class Main {


    class DBHandler{
        Jedis jedis;
        DBHandler(String host){
            jedis = new Jedis(host);
        }

        public boolean create(String key, String value){
            if (fetch(key) == null){
                jedis.lpush(key , value);
                return true;
            }
            return false;
        }

        public String fetch(String key){

            return null;
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
