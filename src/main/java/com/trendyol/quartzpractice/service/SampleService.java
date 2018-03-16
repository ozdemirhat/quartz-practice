package com.trendyol.quartzpractice.service;

import org.springframework.stereotype.Service;

@Service
public class SampleService {

    public Integer add (Integer a , Integer b){
        //System.out.println(a+b);
        return a + b;
    }

    public String sysout(){
        return "This one is a practice";
    }
}
