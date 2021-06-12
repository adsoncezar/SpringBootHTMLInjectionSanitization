package com.xss.filtersanitize.repository;

import com.xss.filtersanitize.entity.Deposity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeposityRepository extends JpaRepository<Deposity, Long> {

}
