package com.example.demo.controller.Score_management_system;



import com.example.demo.domain.GpaEntity;
import com.example.demo.domain.ScoreEntity;
import com.example.demo.service.Choose_courseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
public class GPA_query {
    @Autowired
    private Choose_courseService choose_courseService;

    @RequestMapping(value = "/GPA_query",method = RequestMethod.GET)
    public String gpa_query(){


        return "/index(groupFour)/GPA_query";
    }

}
