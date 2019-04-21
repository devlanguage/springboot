package org.third.spring.boot.hello.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.third.spring.boot.hello.domain.HelloPerson;
 
@RestController
public class PersonController {
    
    @RequestMapping(value="/search",produces={MediaType.APPLICATION_JSON_VALUE})
    public HelloPerson search(String personName){
        
        return new HelloPerson(personName, 32, "hefei");
        
    }
 
}
