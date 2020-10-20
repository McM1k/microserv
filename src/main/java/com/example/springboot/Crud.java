package com.example.springboot;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.beans.Statement;

@RestController
public class Crud {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

/*

    @RequestMapping("/races")
    public String getRaces() {

    }

    @RequestMapping("/races/{racesId}")
    public String getRaceById() {

    }

    @RequestMapping("/races")
    public String addRace() {

    }

    @RequestMapping("/races/{racesId}")
    public String updateRace() {

    }

    @RequestMapping("/races/{racesId}")
    public String deleteRace() {

    }

    @RequestMapping("/owner")
    public String getOwners() {

    }

    @RequestMapping("/owner/{ownerId}")
    public String getOwnerById() {

    }

    @RequestMapping("/owner")
    public String addOwner() {

    }

    @RequestMapping("/owner/{ownerId}")
    public String updateOwners() {

    }

    @RequestMapping("/owner/{ownerId}")
    public String deleteOwners() {

    }
    */
}
