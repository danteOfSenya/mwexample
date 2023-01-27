package org.example.empik.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Getter
@Setter
@Table(name = ApiCallEntity.TABLE_NAME)
@Accessors(chain = true)
public class ApiCallEntity {

    public static final String TABLE_NAME = "api_call";
    public static final String COLUMN_LOGIN = "LOGIN";
    public static final String COLUMN_REQUEST_COUNT = "REQUEST_COUNT";

    @Id @Column(name = COLUMN_LOGIN) private String id;
    @Column(name = COLUMN_REQUEST_COUNT) private Long requestCounter = 0L;
}
