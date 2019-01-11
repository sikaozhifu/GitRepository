package com.test.testjson;

import com.alibaba.fastjson.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
    private Person person;
    @Before
    public void setUp(){
        person = new Person();
        person.setName("zhangsan");
        person.setAge("23");
        person.setDesc("this is a test");
    }
    @Test
    public void test() {
        String jsonStr = JSONObject.toJSONString(person);
        System.out.println("bean to json:" + jsonStr);

        //改变json的key为大写
        jsonStr = jsonStr.toUpperCase();

        System.out.println("需要转换的json:" + jsonStr);
        person = JSONObject.toJavaObject(JSONObject.parseObject(jsonStr), Person.class);
        System.out.println("json to bean:" + person.getName());
    }
}
