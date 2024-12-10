package com.scaler.backendproject;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello, I am Purusharth ";
    }

    @RequestMapping(value = "/hello/{id}", method = RequestMethod.GET)
    public String sayHelloPersonal(@PathVariable("id") String id) {
        return "Hello, My name is " + id;
    }
}

//RestController
//RequestMapping
//RequestMethod
//PathVariable
