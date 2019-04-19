package com.example.demo.controller.Score_query;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Score_query {
    @RequestMapping("/score_query")
    public String sore_query(){
        return "index(groupFour)/score_query";
    }
}
