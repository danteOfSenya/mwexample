package org.example.empik.repository

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.annotation.Transactional
import spock.lang.Specification

@SpringBootTest
@ContextConfiguration(classes = [RepositorySpringContextIntegrationSpec.class])
@Transactional
class RepositoryBaseIntegrationSpec extends Specification {

}
