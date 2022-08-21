package com.example.coffeebe.app.controllers.author;

import com.example.coffeebe.app.controllers.BaseController;
import com.example.coffeebe.app.dtos.request.DTO;
import com.example.coffeebe.app.dtos.request.LoginRequest;
import com.example.coffeebe.app.dtos.request.RegisterRequest;
import com.example.coffeebe.app.dtos.request.impl.UserFilterDto;
import com.example.coffeebe.app.dtos.responses.CustomPage;
import com.example.coffeebe.app.dtos.responses.UserResponse;
import com.example.coffeebe.domain.entities.author.User;
import com.example.coffeebe.domain.services.impl.author.RoleService;
import com.example.coffeebe.domain.services.impl.author.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<User, Long, UserResponse, UserFilterDto> {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	UserService userService;

    public UserController() {
        super(UserResponse.class, UserFilterDto.class);
    }
	
    @GetMapping("/init-role")
    public void initRole(){
        roleService.initRole();
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest registerRequest){
        return userService.register(registerRequest);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws Exception {
        return userService.login(loginRequest);
    }

    @GetMapping("/info")
    public UserResponse getUser() throws Exception {
        return userService.getUserLogin();
    }

    @PatchMapping
    public UserResponse update(HttpServletRequest request, @Valid @RequestBody DTO userDto){
        return userService.update(request,userDto);
    }

    @PostMapping("{id}/status")
    public UserResponse changeStatus(@PathVariable Long id){
        return userService.changeStatus(id);
    }
}
