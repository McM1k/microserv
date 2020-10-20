package com.example.springboot;

import org.h2.tools.RunScript;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.ApplicationContext;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute(
                    "DROP TABLE IF EXISTS cats;\n" +
                    "DROP TABLE IF EXISTS owners;\n" +
                    "DROP TABLE IF EXISTS races;\n" +
                    "\n" +
                    "\tCREATE TABLE cats (\n" +
                    "\t  id INTEGER AUTO_INCREMENT  PRIMARY KEY,\n" +
                    "\t  name VARCHAR(250) NOT NULL,\n" +
                    "\t  race INTEGER NOT NULL,\n" +
                    "\t  color VARCHAR(250) NOT NULL,\n" +
                    "\t  owner INT DEFAULT NULL\n" +
                    "\t);\n" +
                    "\n" +
                    "\tCREATE TABLE owners (\n" +
                    "     \t id INTEGER AUTO_INCREMENT  PRIMARY KEY,\n" +
                    "     \t name VARCHAR(250) NOT NULL\n" +
                    "    );\n" +
                    "\n" +
                    "    CREATE TABLE races (\n" +
                    "        id INTEGER AUTO_INCREMENT  PRIMARY KEY,\n" +
                    "        name VARCHAR(250) NOT NULL\n" +
                    "    );\n" +
                    "\n" +
                    "\tINSERT INTO cats (id, name, race, color, owner) VALUES\n" +
                    "\t  (1, 'no_owner', 1, 'white', NULL),\n" +
                    "\t  (2, 'hamb', 2, 'orange', 1),\n" +
                    "\t  (3, 'catto', 3, 'black', 1),\n" +
                    "\t  (4, 'garfield', 1, 'orange', 2);\n" +
                    "\n" +
                    "\tINSERT INTO owners (id, name) VALUES\n" +
                    "      (1, 'meme'),\n" +
                    "      (2, 'john');\n" +
                    "\n" +
                    "\tINSERT INTO races (id, name) VALUES\n" +
                    "\t  (1, 'normal'),\n" +
                    "\t  (2, 'siamese'),\n" +
                    "\t  (3, 'goblin');"
            );
            //stmt.execute("RUNSCRIPT FROM './src/main/resources/data.sql'");
            //RunScript.execute(conn, new FileReader("./src/main/resources/data.sql"));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        //} catch (FileNotFoundException e) {
         //   e.printStackTrace();
        } finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }

        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return args -> {
            System.out.println("Beans :");
            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println("");
            }
        };
    }
}
