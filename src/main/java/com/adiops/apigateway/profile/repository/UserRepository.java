package com.adiops.apigateway.profile.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adiops.apigateway.profile.entity.User;
import com.adiops.apigateway.profile.entity.UserEntity;

/**
 * This interface is responsible for JPA repository binding and querying the data.
 * @author Deepak Pal
 *
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	User findByUserName(String userId);

}
