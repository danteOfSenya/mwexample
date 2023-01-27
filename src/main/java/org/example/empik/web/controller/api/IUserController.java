package org.example.empik.web.controller.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import org.example.empik.model.dto.UserResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "Users", description = "Users management (?)")
@RequestMapping(path = "/users", produces = APPLICATION_JSON_VALUE)
public interface IUserController {

    @Operation(description = "Get user by login.")
    @GetMapping(path = "/{login}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success. User returned."),
            @ApiResponse(responseCode = "400", description = "Request validation error"),
            @ApiResponse(responseCode = "500", description = "Server error")
    })
    ResponseEntity<UserResponseDTO> get(
            @Parameter(description = "User login", required = true) @NotEmpty @PathVariable(name = "login") String login
    );
}
