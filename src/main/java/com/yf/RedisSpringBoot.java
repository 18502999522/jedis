package com.yf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Component
public class RedisSpringBoot {

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    void testString(){
        //redisTemplate.opsForValue().set("age","90");
        Object value = redisTemplate.opsForValue().get("age");
        System.out.println(value);

    }
    void testObject(){
        /*Student student = new Student();
        student.setId(1);
        student.setName("张三");
        student.setNo("001");

        //  {id:1,name:"张三",no:"001"}

        redisTemplate.opsForValue().set("student",student);
        Object o = redisTemplate.opsForValue().get("student");
        System.out.println(o);*/


    }


}
