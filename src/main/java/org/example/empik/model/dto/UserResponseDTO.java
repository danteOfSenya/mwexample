package org.example.empik.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Accessors(chain = true)
public class UserResponseDTO implements Serializable {
    @Serial private static final long serialVersionUID = -2307681103755578046L;

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUrl;
    private ZonedDateTime createdAt;
    private BigDecimal calculations;
}
