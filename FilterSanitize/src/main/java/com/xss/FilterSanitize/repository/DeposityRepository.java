package com.xss.FilterSanitize.repository;

import com.xss.FilterSanitize.entity.Deposity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeposityRepository extends JpaRepository<Deposity, Long> {

}
