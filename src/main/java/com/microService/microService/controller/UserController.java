package com.microService.microService.controller;

import com.microService.microService.vo.RequestUser;
import com.microService.microService.dto.UserDto;
import com.microService.microService.service.UserServiceImpl;
import com.microService.microService.vo.ResponseUser;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {

    private Environment env;
    private UserServiceImpl userService;

    @Autowired
    public UserController(Environment env,UserServiceImpl userService){

        this.userService = userService;
        this.env = env;
    }

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return env.getProperty("greeting.message");
    }

    @PostMapping("/users")
    public ResponseEntity<ResponseUser> createUser(@RequestBody RequestUser user){
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto= mapper.map(user, UserDto.class);

        userService.createUser(userDto);
        ResponseUser responseUser = mapper.map(userDto,ResponseUser.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
