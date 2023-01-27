package org.example.empik.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.empik.model.dto.exception.GithubExceptionDTO;
import org.example.empik.model.dto.github.GithubUserResponseDTO;
import org.example.empik.service.api.IGithubUserRestClientService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class GithubUserRestClientService implements IGithubUserRestClientService {

    private final RestTemplate githubRestTemplate;
    private final Gson gson;

    @Value(value = "${github.url.users.path}")
    private String usersPath;

    @Override public GithubUserResponseDTO getUser(String login) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("login", login);
            ResponseEntity<GithubUserResponseDTO> forEntity = githubRestTemplate.getForEntity(usersPath, GithubUserResponseDTO.class, params);
            return forEntity.getBody();
        } catch (HttpClientErrorException exception) {
            GithubExceptionDTO githubExceptionDTO = gson.fromJson(exception.getResponseBodyAsString(), GithubExceptionDTO.class);
            log.info("Exception. Response: {}", githubExceptionDTO);

            throw new RestClientException(githubExceptionDTO.getMessage(), exception);
        }
    }
}
