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
public class RaceController {
    @GetMapping(path = "/race")
    public @ResponseBody ArrayList<Race> getRaces(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Race> list = new ArrayList<Race>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM races");
            while(rs.next()) {
                list.add(new Race(
                        rs.getInt("id"),
                        rs.getString("name")
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

    @GetMapping(path = "/race/{raceId}")
    public @ResponseBody Race getRaceById(@PathVariable("raceId") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Race race = new Race();


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM races WHERE id=" + id);
            rs.next();
            race = new Race(rs.getInt("id"),
                    rs.getString("name")
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
        return race;
    }

    @PostMapping(path = "/race")
    public void addRace(@RequestBody Race race, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM races");
            rs.next();
            int id = rs.getInt("COUNT(*)") + 1;
            rs.close();
            rs = stmt.executeQuery("SELECT * FROM races");
            stmt.execute("INSERT INTO races VALUES("+ id + ", '" + race.getName() + "')");

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

    @PostMapping(path = "/race/{raceId}")
    public void updateRace(@PathVariable("raceId") String id, @RequestBody Race race, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("UPDATE races SET name = '" + race.getName() + "' WHERE id = " + id);

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

    @DeleteMapping(path = "/race/{raceId}")
    public void deleteRace(@PathVariable("raceId") String id, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM races WHERE id = " + id);

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
