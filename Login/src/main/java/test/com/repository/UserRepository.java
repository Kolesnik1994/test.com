package test.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.com.model.User;

/**
 * User Repository
 * @author Vladislav K
 */
@Repository
public interface UserRepository extends JpaRepository <User, Long> {
	
	User findByEmail (String email);

}
