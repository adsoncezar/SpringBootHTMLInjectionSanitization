package com.xss.AnnotationSanitize.repository;

import com.xss.AnnotationSanitize.entity.Deposity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeposityRepository extends JpaRepository<Deposity, Long> {

}
