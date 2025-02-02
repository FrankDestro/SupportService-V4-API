package com.dev.ServiceHelp.repository;

import com.dev.ServiceHelp.models.entities.SLA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SLARepository extends JpaRepository<SLA, Long> {

}
