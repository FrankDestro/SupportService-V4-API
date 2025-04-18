package com.dev.ServiceHelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.ServiceHelp.models.entities.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Role findByAuthority(String authority);

}
