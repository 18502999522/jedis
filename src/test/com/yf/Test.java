package com.yf;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {

    @Autowired
    RedisSpringBoot redisSpringBoot;

    @org.junit.Test
    public void test(){
        redisSpringBoot.testString();
    }

    @org.junit.Test
    public void test2(){
        redisSpringBoot.testObject();
    }




}
