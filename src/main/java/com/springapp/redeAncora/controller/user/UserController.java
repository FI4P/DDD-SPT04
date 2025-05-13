package com.springapp.redeAncora.controller.user;

import com.springapp.redeAncora.domain.User;
import com.springapp.redeAncora.dto.user.UserRequestDTO;
import com.springapp.redeAncora.dto.user.UserResponseDTO;
import com.springapp.redeAncora.sevice.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@Controller
@ResponseBody
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody UserRequestDTO dto){
        User user = new User(null, dto.name(), dto.email(), dto.password(), dto.role() );
        User savedUser = userService.save(user);

        return ResponseEntity.ok(new UserResponseDTO(savedUser.getId(), savedUser.getName(), savedUser.getEmail(), savedUser.getRole()));
    }

    @GetMapping
    public String teste(){
        return "teste";
    }

}
