package org.example.empik.model.dto.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Accessors(chain = true)
@Data
@NoArgsConstructor
public class GithubExceptionDTO implements Serializable {
    @Serial private static final long serialVersionUID = 1282770906955712302L;

    @JsonProperty("message")
    private String message;

    @JsonProperty("documentation_url")
    private String documentationUrl;
}
