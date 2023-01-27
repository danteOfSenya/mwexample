package org.example.empik.repository

import org.springframework.boot.SpringBootConfiguration
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.ComponentScan
import org.springframework.transaction.annotation.EnableTransactionManagement

@TestConfiguration
@EnableTransactionManagement
@ComponentScan(value = ["org.example.empik"])
@SpringBootConfiguration
@EnableAutoConfiguration
@EnableCaching
class RepositorySpringContextIntegrationSpec {

}
