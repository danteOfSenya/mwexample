package org.example.empik.model.dto.exception;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@Schema(description = "Represents unsuccessful response")
public class UnsuccessfulResponse implements Serializable {
    @Serial private static final long serialVersionUID = 4591907457618256177L;

    @Schema(description = "More detailed message that describes cause of the problem.")
    private String message;
}
