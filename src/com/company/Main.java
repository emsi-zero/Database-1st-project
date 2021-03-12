package com.company;

import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {


    static class DBHandler{
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
            try{
                return jedis.lrange(key , 0 , 0).get(0);
            }catch (IndexOutOfBoundsException e){
                return null;
            }
        }

        public boolean update(String key, String value){
            if (fetch(key) != null){
                if (delete(key)){
                    return create(key,value);
                }
                return false;
            }
            return false;
        }

        public boolean delete(String key){
            if (jedis.lpop(key)!= null){
                return true;
            }
            return false;
        }

        public void initiate(FileReader reader){
            try {

                BufferedReader br = new BufferedReader(reader);
                String line;
                while ((line = br.readLine()) != null) {
                    String [] data = line.split(",");
                    create(data[0] , data[1]);
                }
            }catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args) {
        DBHandler db = new DBHandler("localhost");
        try {
            db.initiate(new FileReader("C:\\Users\\fama\\Desktop\\Study 4th term\\Databases\\Projects\\Project0\\NYSE_20210301.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        while(true){

            String [] input = scanner.nextLine().split(" ");
            try {
                switch (input[0]) {
                    case "create":
                        System.out.println(db.create(input[1], input[2]));
                        break;
                    case "fetch":
                        String s = db.fetch(input[1]);
                        if (s != null) {
                            System.out.println("true");
                            System.out.println(s);
                        } else {
                            System.out.println("false");
                        }
                        break;
                    case "update":
                        System.out.println(db.update(input[1], input[2]));
                        break;
                    case "delete":
                        System.out.println(db.delete(input[1]));
                        break;
                    default:
                        break;
                }
            }catch (Exception e){
                continue;
            }
            System.out.println();

        }


    }
}
