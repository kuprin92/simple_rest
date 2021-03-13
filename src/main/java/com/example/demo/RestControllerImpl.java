package com.example.demo;

import com.example.demo.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class RestControllerImpl {

    private final UserService userService;

    public RestControllerImpl(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<?> getUsers(@RequestParam(defaultValue = "0") Integer pageNo,
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(defaultValue = "id") String sortBy) {
        PageRequest request = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        return ResponseEntity.ok(userService.findUsers(request));
    }

    @GetMapping("/group/")
    public ResponseEntity<?> getGroupUsers(@RequestHeader("group") String groupName) {
        return ResponseEntity.ok(userService.getUsersByGroupName(groupName));
    }


    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestHeader("name") String userName, @RequestHeader("group") String groupName) {
        return ResponseEntity.ok(userService.createUser(userName, groupName));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleException(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
