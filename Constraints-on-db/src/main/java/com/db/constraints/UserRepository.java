package com.db.constraints;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

	@Procedure(name = "CreateSpecialUserAndGrantPermission")
	void createSpecialUserAndGrantPermission(
			@Param(value = "p_username") String username, 
			@Param(value = "p_password") String password
		);
	
	@Procedure(name = "GrantCreateUserPermission")
	void grantCreateUserPermission(
			@Param(value = "p_username") String username
			);
}
