package org.example.empik.web.validation


import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.client.RestClientException
import spock.lang.Specification

import java.lang.reflect.Method

class ExceptionRestHandlerSpec extends Specification {

    private ExceptionRestHandler handler

    void setup() {
        Locale.setDefault(Locale.forLanguageTag('pl'))
        handler = new ExceptionRestHandler()
    }

    def "should handle input http message parse exception"() {
        when:
        def responseEntity = handler.handleInputHttpMessageParseException(new HttpMessageNotReadableException('test exception'))

        then:
        noExceptionThrown()
        responseEntity.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should handle method argument not valid exception"() {
        def methodParameter = Mock(MethodParameter)
        def executable = GroovyMock(Method)
        def bindingResult = Mock(BindingResult)
        methodParameter.getParameterIndex() >> 0
        methodParameter.getExecutable() >> executable
        executable.toString() >> 'some string'
        bindingResult.getErrorCount() >> 0
        bindingResult.getAllErrors() >> []
        bindingResult.getFieldErrors() >> []

        when:
        def responseEntity = handler.handleMethodArgumentNotValidException(new MethodArgumentNotValidException(methodParameter, bindingResult))

        then:
        noExceptionThrown()
        responseEntity.statusCode == HttpStatus.BAD_REQUEST
    }

    def "should handle rest client exception"() {
        when:
        def responseEntity = handler.handleRestClientException(new RestClientException('test exception', new Exception('test')))

        then:
        noExceptionThrown()
        responseEntity.statusCode == HttpStatus.NOT_FOUND
    }

    def "should handle any other runtime exception"() {
        when:
        def responseEntity = handler.handleRuntimeException(new NullPointerException('test NPE'))

        then:
        noExceptionThrown()
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }

    def "should handle any other exception"() {
        when:
        def responseEntity = handler.handleException(new Exception('test exception'))

        then:
        noExceptionThrown()
        responseEntity.statusCode == HttpStatus.INTERNAL_SERVER_ERROR
    }
}
