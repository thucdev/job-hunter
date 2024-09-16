package vn.hoidanit.jobhunter.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.turkraft.springfilter.boot.Filter;

import vn.hoidanit.jobhunter.domain.User;
import vn.hoidanit.jobhunter.domain.dto.ResUserDTO;
import vn.hoidanit.jobhunter.domain.dto.ResultPagingationDTO;
import vn.hoidanit.jobhunter.service.UserService;
import vn.hoidanit.jobhunter.service.error.InValidException;
import vn.hoidanit.jobhunter.util.annotation.ApiMessage;

@RestController
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public UserController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/user")
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        String hashPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        User res = this.userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @GetMapping("/users")
    @ApiMessage("Fetch all users")
    public ResultPagingationDTO getUsers(@Filter Specification<User> spec, Pageable pageable) {
        return this.userService.getUsers(spec,pageable);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<ResUserDTO> getUser(@PathVariable long id) throws InValidException {

        User user = this.userService.getUserById(id);
        if (user == null) {
            throw new InValidException("null user");
        }
        return ResponseEntity.ok().body(this.userService.convertTResUserDTO(user));
    }

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable long id, @RequestBody User user) {
        user.setId(id);
        return this.userService.updateUser(user);
    }
}
