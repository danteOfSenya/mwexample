package org.example.empik.repository

import org.example.empik.model.entity.ApiCallEntity
import org.springframework.beans.factory.annotation.Autowired

class ApiCallRepositoryIntegrationSpec extends RepositoryBaseIntegrationSpec {

    @Autowired IApiCallRepository repository

    def "should find entity by user login"() {
        given:
        ApiCallEntity apiCall = prepareEntity()

        when:
        def optional = repository.findById(apiCall.id)

        then:
        optional.isPresent()
        optional.get().id == apiCall.id
    }

    ApiCallEntity prepareEntity() {
        repository.saveAndFlush(new ApiCallEntity().setId('it-test'))
    }
}
