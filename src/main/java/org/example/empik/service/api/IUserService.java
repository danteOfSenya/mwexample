package org.example.empik.service.api;

import org.example.empik.model.dto.UserResponseDTO;

public interface IUserService {

    UserResponseDTO getUser(String login);
}
