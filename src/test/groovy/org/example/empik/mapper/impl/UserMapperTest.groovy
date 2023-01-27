package org.example.empik.mapper.impl

import org.example.empik.mapper.api.IUserMapper
import org.example.empik.model.dto.github.GithubUserResponseDTO
import spock.lang.Specification
import spock.lang.Unroll

import java.time.ZonedDateTime

class UserMapperTest extends Specification {

    private IUserMapper mapper

    void setup() {
        mapper = new UserMapper()
    }

    @Unroll
    def "should map to UserResponseDTO"() {
        given:
        def now = ZonedDateTime.now()
        GithubUserResponseDTO githubUserResponseDTO = new GithubUserResponseDTO(id: 7L, login: 'login', name: 'name', type: 'type', avatarUrl: 'avatarUrl', createdAt: now, followers: followers, publicRepos: publicRepos)

        when:
        def userResponseDTO = mapper.toUserResponseDTO(githubUserResponseDTO)

        then:
        userResponseDTO.id == 7L
        userResponseDTO.login == 'login'
        userResponseDTO.name == 'name'
        userResponseDTO.type == 'type'
        userResponseDTO.avatarUrl == 'avatarUrl'
        userResponseDTO.createdAt == now
        userResponseDTO.calculations == calculations

        where:
        followers | publicRepos | calculations
        0         | 0           | 0
        7L        | 0           | 1.71428572
        0         | 7L          | 0
        7L        | 14L         | 13.71428576
        15L       | 2L          | 1.60000000
    }
}
