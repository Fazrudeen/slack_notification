package com.samplejava.spring.controller;

import com.samplejava.spring.model.UserRequest;
import com.samplejava.spring.model.Users;
import com.samplejava.spring.service.SampleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class Controller {

    private SampleService sampleService;

    public Controller(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @RequestMapping(path = "/save_users", consumes = MediaType.APPLICATION_JSON_VALUE, method = {RequestMethod.POST})
    @ResponseStatus(HttpStatus.CREATED)
    public Users saveDB(@RequestBody UserRequest userRequest) throws Exception {
        return sampleService.saveDB(userRequest);
    }
}
