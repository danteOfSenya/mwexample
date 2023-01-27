package org.example.empik.web.controller.impl

import org.example.empik.model.dto.UserResponseDTO
import org.example.empik.service.api.IUserService
import org.example.empik.web.controller.api.IUserController
import org.springframework.http.HttpStatus
import spock.lang.Specification

class UserControllerSpec extends Specification {

    private IUserService userService
    private IUserController controller

    void setup() {
        userService = Mock()
        controller = new UserController(userService)
    }

    def "should get user"() {
        when:
        controller.get('login')

        then:
        1 * userService.getUser(_)
    }

    def "should get user by login"() {
        given:
        def id = 1L
        userService.getUser('login') >> new UserResponseDTO(id: id)

        when:
        def responseEntity = controller.get('login')

        then:
        responseEntity.statusCode == HttpStatus.OK
        responseEntity.body.id == id
    }
}
