package com.example.springboot;

import org.h2.tools.RunScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;
import java.io.FileReader;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CatController {
    @GetMapping(path = "/cat")
    public @ResponseBody ArrayList<Cat> getCats(
            @PathParam("owner") String owner,
            @PathParam("race") String race,
            HttpServletRequest request,
            HttpServletResponse response
    ) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Cat> list = new ArrayList<Cat>();


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();

            String sql = "SELECT * FROM cats";
            if (owner != null || race != null) {
                sql += " WHERE ";
                if (owner != null) {
                    rs = stmt.executeQuery("SELECT id FROM owners WHERE name = '" + owner + "'");
                    rs.next();
                    owner = rs.getInt("id") + "";
                    rs.close();
                    sql += "owner = '" + owner + "'";
                    if (race != null) { sql += " AND "; }
                }
                if (race != null) {
                    rs = stmt.executeQuery("SELECT id FROM races WHERE name = '" + race + "'");
                    rs.next();
                    race = rs.getInt("id") + "";
                    rs.close();
                    sql += "race = '" + race + "'";
                }
            }
            rs = stmt.executeQuery(sql);
            while(rs.next()) {
                list.add(new Cat(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getInt("race"),
                    rs.getInt("owner")
                ));
            }
            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return list;
    }

    @GetMapping(path = "/cat/{catId}")
    public @ResponseBody Cat getCatById(@PathVariable("catId") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Cat cat = new Cat();


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM cats WHERE id=" + id);
            rs.next();
            cat = new Cat(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("color"),
                    rs.getInt("race"),
                    rs.getInt("owner")
            );

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
        return cat;
    }

    @PostMapping(path = "/cat")
    public void addCat(@RequestBody Cat cat, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM cats");
            rs.next();
            int id = rs.getInt("COUNT(*)") + 1;
            rs.close();
            rs = stmt.executeQuery("SELECT * FROM cats");
            stmt.execute("INSERT INTO cats VALUES("+ id + ", '" + cat.getName() + "', " + cat.getRace() + ", '"
                    + cat.getColor().toString() + "', " + cat.getOwner() +")");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    @PostMapping(path = "/cat/{catId}")
    public void updateCat(@PathVariable("catId") String id, @RequestBody Cat cat, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("UPDATE cats SET name = '" + cat.getName() + "', race = " + cat.getRace()
                    + ", color = '" + cat.getColor().toString() + "', owner = " + cat.getOwner()
                    +" WHERE id = " + id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }

    @DeleteMapping(path = "/cat/{catId}")
    public void deleteCat(@PathVariable("catId") String id, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM cats WHERE id = " + id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                if (stmt!=null) stmt.close();
                if (conn!=null) conn.close();
            } catch (SQLException se){
                se.printStackTrace();
            }
        }
    }
}
