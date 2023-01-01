package com.samplejava.spring.service;

import com.samplejava.spring.model.UserRequest;
import com.samplejava.spring.model.Users;
import com.samplejava.spring.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SampleService {

    private static final Logger logger = LogManager.getLogger(SampleService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    SlackService slackService;

    @Transactional
    public Users saveDB(UserRequest userRequest) throws Exception{
        Users users = new Users();
        users.setUserId(userRequest.getUserId());
        users.setFirstName(userRequest.getUserName());
        Users saveResult = null;
        if (!userRepository.existsByUserId(userRequest.getUserId())) {
            try {
                saveResult = userRepository.save(users);
                userRepository.flush();
                slackService.sendMessage("successfully updated user in DB for user:" +userRequest.getUserId() ,
                        "SampleService", "saveDB");
            } catch (Exception e) {
                logger.info("Exception while updating DB");
                throw e;
            }
        }
        return saveResult;
    }
}
