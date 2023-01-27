package org.example.empik.web.controller.impl;

import lombok.RequiredArgsConstructor;
import org.example.empik.model.dto.UserResponseDTO;
import org.example.empik.service.api.IUserService;
import org.example.empik.web.controller.api.IUserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController implements IUserController {

    private final IUserService userService;

    @Override public ResponseEntity<UserResponseDTO> get(String login) {
        UserResponseDTO userResponse = userService.getUser(login);

        return ResponseEntity.ok(userResponse);
    }
}
