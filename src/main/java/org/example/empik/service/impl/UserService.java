package org.example.empik.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.empik.mapper.api.IUserMapper;
import org.example.empik.model.dto.UserResponseDTO;
import org.example.empik.model.dto.github.GithubUserResponseDTO;
import org.example.empik.model.entity.ApiCallEntity;
import org.example.empik.repository.IApiCallRepository;
import org.example.empik.service.api.IGithubUserRestClientService;
import org.example.empik.service.api.IUserService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final IGithubUserRestClientService githubUserRestClientService;
    private final IUserMapper userMapper;
    private final IApiCallRepository apiCallRepository;

    @Override public UserResponseDTO getUser(String login) {
        String githubLogin = login.toLowerCase();
        GithubUserResponseDTO responseDTO = githubUserRestClientService.getUser(githubLogin);

        updateCounter(githubLogin);

        return userMapper.toUserResponseDTO(responseDTO);
    }

    private void updateCounter(String login) {
        Optional<ApiCallEntity> optionalApiCallEntity = apiCallRepository.findById(login);
        ApiCallEntity apiCallEntity = optionalApiCallEntity.orElseGet(() -> new ApiCallEntity().setId(login));
        apiCallEntity.setRequestCounter(apiCallEntity.getRequestCounter() + 1);
        apiCallRepository.saveAndFlush(apiCallEntity);
    }
}
