package api.centralerrorapijava.user.controller;

import api.centralerrorapijava.user.model.User;
import api.centralerrorapijava.user.service.UserServiceImpl;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {
        @Autowired
        private UserServiceImpl userService;

        @PostMapping
        @ApiOperation("Create new user")
        @ApiResponses(value = {@ApiResponse(code = 201, message = "A new user Created")})
        public ResponseEntity<String> create(@Valid @RequestBody User user) {
                this.userService.save(user);
            return new ResponseEntity<>("A new user Created", HttpStatus.CREATED);
        }
}
