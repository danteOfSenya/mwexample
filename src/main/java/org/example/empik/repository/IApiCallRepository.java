package org.example.empik.repository;

import org.example.empik.model.entity.ApiCallEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IApiCallRepository extends JpaRepository<ApiCallEntity, String> {
}
