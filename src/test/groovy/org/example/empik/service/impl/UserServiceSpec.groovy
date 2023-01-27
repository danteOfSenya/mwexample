package org.example.empik.service.impl

import org.example.empik.mapper.api.IUserMapper
import org.example.empik.model.dto.UserResponseDTO
import org.example.empik.model.dto.github.GithubUserResponseDTO
import org.example.empik.model.entity.ApiCallEntity
import org.example.empik.repository.IApiCallRepository
import org.example.empik.service.api.IGithubUserRestClientService
import org.example.empik.service.api.IUserService
import spock.lang.Specification

class UserServiceSpec extends Specification {

    private IGithubUserRestClientService githubUserRestClientService
    private IUserMapper userMapper
    private IApiCallRepository apiCallRepository
    private IUserService service

    void setup() {
        githubUserRestClientService = Mock()
        userMapper = Mock()
        apiCallRepository = Mock()

        service = new UserService(githubUserRestClientService, userMapper, apiCallRepository)
    }

    def "should get user by login when ApiCallEntity exists in database"() {
        given:
        githubUserRestClientService.getUser('login') >> new GithubUserResponseDTO()
        userMapper.toUserResponseDTO(_ as GithubUserResponseDTO) >> new UserResponseDTO(id: 7L)
        apiCallRepository.findById('login') >> Optional.of(new ApiCallEntity(id: 'login'))

        when:
        def userResponseDTO = service.getUser('Login')

        then:
        userResponseDTO.id == 7L
        1 * apiCallRepository.saveAndFlush(_ as ApiCallEntity)
    }

    def "should get user by login when ApiCallEntity not exist in database"() {
        given:
        githubUserRestClientService.getUser('login') >> new GithubUserResponseDTO()
        userMapper.toUserResponseDTO(_ as GithubUserResponseDTO) >> new UserResponseDTO(id: 7L)
        apiCallRepository.findById('login') >> Optional.empty()

        when:
        def userResponseDTO = service.getUser('Login')

        then:
        userResponseDTO.id == 7L
        1 * apiCallRepository.saveAndFlush(_ as ApiCallEntity)
    }
}
