package org.example.empik.mapper.api;

import org.example.empik.model.dto.UserResponseDTO;
import org.example.empik.model.dto.github.GithubUserResponseDTO;

public interface IUserMapper {
    UserResponseDTO toUserResponseDTO(GithubUserResponseDTO githubUserResponseDTO);
}
