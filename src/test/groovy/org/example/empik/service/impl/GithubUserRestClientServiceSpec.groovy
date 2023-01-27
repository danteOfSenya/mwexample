package org.example.empik.service.impl

import com.google.gson.Gson
import org.example.empik.model.dto.github.GithubUserResponseDTO
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import java.nio.charset.StandardCharsets

class GithubUserRestClientServiceSpec extends Specification {

    private RestTemplate githubRestTemplate
    private Gson gson
    private GithubUserRestClientService service

    void setup() {
        githubRestTemplate = GroovyMock()
        gson = new Gson()

        service = new GithubUserRestClientService(githubRestTemplate, gson)
        service.usersPath = 'user/path'
    }

    def "should get user from rest when no exception occurred"() {
        given:
        githubRestTemplate.getForEntity(_ as String, GithubUserResponseDTO.class, _) >> new ResponseEntity<GithubUserResponseDTO>(new GithubUserResponseDTO(id: 7L), HttpStatus.OK)

        when:
        def responseDTO = service.getUser('login')

        then:
        noExceptionThrown()
        responseDTO.id == 7L
    }

    def "should throw RestClientException when exception occurred"() {
        given:
        githubRestTemplate.getForEntity(_ as String, GithubUserResponseDTO.class, _) >> {
            throw new HttpClientErrorException(
                    HttpStatusCode.valueOf(404),
                    'exception',
                    '{"message": "Not Found", "documentation_url": "https://docs.github.com/rest/reference/users#get-a-user"}'.getBytes(StandardCharsets.UTF_8),
                    StandardCharsets.UTF_8
            )
        }

        when:
        service.getUser('login')

        then:
        thrown(RestClientException)
    }
}
