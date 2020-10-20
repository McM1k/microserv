package com.example.springboot;

import org.h2.tools.RunScript;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileReader;
import java.net.http.HttpResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CatController {
    @GetMapping(path = "/cat")
    public @ResponseBody ArrayList<Cat> getCats(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        ArrayList<Cat> list = new ArrayList<Cat>();

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            rs = stmt.executeQuery("SELECT id, name, race, color, owner FROM cats");

            while(rs.next()) {
                list.add(new Cat(
                    rs.getInt("id"),
                    rs.getString("first"),
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
        }
        return list;
    }

    @GetMapping(path = "/cat/{catId}")
    public @ResponseBody Cat getCatById(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Cat cat = new Cat();


        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            rs = stmt.executeQuery("SELECT id, name, race, color, owner FROM cats WHERE id=" + request.getParameter("catId"));

            cat = new Cat(rs.getInt("id"),
                    rs.getString("first"),
                    rs.getString("color"),
                    rs.getInt("race"),
                    rs.getInt("owner")
            );

            rs.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return cat;
    }

    @PostMapping("/cat")
    public void addCat(@RequestBody Cat cat) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            rs = stmt.executeQuery("SELECT id, name, race, color, owner FROM cats");
            int id = rs.getFetchSize() + 1;
            stmt.execute("INSERT INTO cats VALUES("+ id + ", '" + cat.getName() + "', " + cat.getRace() + ", '"
                    + cat.getColor().toString() + "', " + cat.getOwner() +")");

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/cat/{catId}")
    public void updateCats(@RequestBody Cat cat, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt.execute("UPDATE Cats SET name = '" + cat.getName() + "', race = " + cat.getRace()
                    + ", color = '" + cat.getColor().toString() + "', owner = " + cat.getOwner()
                    +" WHERE id = " + request.getParameter("catId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/cat/{catId}")
    public void deleteCats(HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("org.h2.Driver");
            conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            stmt.execute("DELETE FROM Cats WHERE id = " + request.getParameter("catId"));

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
