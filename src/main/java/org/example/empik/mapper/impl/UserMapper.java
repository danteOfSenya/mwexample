package org.example.empik.mapper.impl;

import org.example.empik.mapper.api.IUserMapper;
import org.example.empik.model.dto.UserResponseDTO;
import org.example.empik.model.dto.github.GithubUserResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class UserMapper implements IUserMapper {

    private static final long ITS_JUST_SIX = 6L;
    private static final long AND_THATS_TWO = 2L;
    private static final int SCALE = 8;

    @Override public UserResponseDTO toUserResponseDTO(GithubUserResponseDTO githubUserResponseDTO) {
        return new UserResponseDTO()
                .setId(githubUserResponseDTO.getId())
                .setLogin(githubUserResponseDTO.getLogin())
                .setName(githubUserResponseDTO.getName())
                .setType(githubUserResponseDTO.getType())
                .setAvatarUrl(githubUserResponseDTO.getAvatarUrl())
                .setCreatedAt(githubUserResponseDTO.getCreatedAt())
                .setCalculations(prepareCalculations(githubUserResponseDTO));
    }

    private BigDecimal prepareCalculations(GithubUserResponseDTO githubUserResponseDTO) {
        if (githubUserResponseDTO.getFollowers() == 0) {
            return BigDecimal.ZERO;
        }

        return BigDecimal.valueOf(ITS_JUST_SIX)
                .divide(BigDecimal.valueOf(githubUserResponseDTO.getFollowers()), SCALE, RoundingMode.HALF_EVEN)
                .multiply(
                        BigDecimal.valueOf(AND_THATS_TWO)
                                .add(BigDecimal.valueOf(githubUserResponseDTO.getPublicRepos()))
                );
    }
}
