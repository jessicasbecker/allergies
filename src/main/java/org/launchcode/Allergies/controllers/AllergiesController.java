package org.launchcode.Allergies.controllers;


import org.launchcode.Allergies.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class AllergiesController {

    @Autowired
    private UserDao userDao;
}
