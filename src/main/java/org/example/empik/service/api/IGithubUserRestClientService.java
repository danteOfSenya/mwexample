package org.example.empik.service.api;

import org.example.empik.model.dto.github.GithubUserResponseDTO;

public interface IGithubUserRestClientService {

    GithubUserResponseDTO getUser(String login);
}
