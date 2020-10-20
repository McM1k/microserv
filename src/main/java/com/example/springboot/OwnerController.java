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
public class OwnerController {
    @GetMapping(path = "/owner")
    public @ResponseBody ArrayList<Owner> getOwners(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Owner> list = new ArrayList<Owner>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();

            rs = stmt.executeQuery("SELECT * FROM owners");
            while(rs.next()) {
                list.add(new Owner(
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

    @GetMapping(path = "/owner/{ownerId}")
    public @ResponseBody Owner getOwnerById(@PathVariable("ownerId") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Owner owner = new Owner();


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM owners WHERE id=" + id);
            rs.next();
            owner = new Owner(rs.getInt("id"),
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
        return owner;
    }

    @PostMapping(path = "/owner")
    public void addOwner(@RequestBody Owner owner, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) FROM owners");
            rs.next();
            int id = rs.getInt("COUNT(*)") + 1;
            rs.close();
            rs = stmt.executeQuery("SELECT * FROM owners");
            stmt.execute("INSERT INTO owners VALUES("+ id + ", '" + owner.getName() + "')");

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

    @PostMapping(path = "/owner/{ownerId}")
    public void updateOwner(@PathVariable("ownerId") String id, @RequestBody Owner owner, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("UPDATE owners SET name = '" + owner.getName() + "' WHERE id = " + id);

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

    @DeleteMapping(path = "/owner/{ownerId}")
    public void deleteOwner(@PathVariable("ownerId") String id, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:./src/main/resources/db", "sa", "");
            stmt = conn.createStatement();
            stmt.execute("DELETE FROM owners WHERE id = " + id);

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
