package com.example.demo.domain;

import java.util.Comparator;

public class Score_sort_down implements Comparator<ScoreEntity> {
    @Override
    public int compare(ScoreEntity o1,ScoreEntity o2){
        if(o1.getTotal()>o2.getTotal()){
            return -1;
        }
        return 1;
    }
}
