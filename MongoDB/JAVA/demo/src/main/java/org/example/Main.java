package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("src/main/resources/application.properties"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String bdName = properties.getProperty("mongo.database.name");
        String host = properties.getProperty("mongo.host");
        String port = properties.getProperty("mongo.port");


        ConnectionString connectionString = new ConnectionString("mongodb://"+host+":"+ port);

        MongoClientSettings connString = MongoClientSettings.builder().applyConnectionString(connectionString).build();

        try (MongoClient mongoClient = MongoClients.create(connString)){

            Mong


        }

    }