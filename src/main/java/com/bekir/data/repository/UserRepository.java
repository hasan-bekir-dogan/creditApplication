package com.bekir.data.repository;

import com.bekir.business.dto.UserDto;
import com.bekir.business.services.UserServices;
import com.bekir.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query(value = "SELECT * FROM users u WHERE u.identity_number = :identityNumber and u.birth_date = :birthDate", nativeQuery = true)
    UserEntity findUserByIdentityNumberAndBirthDate(@Param("identityNumber") Long identityNumber,@Param("birthDate") String birthDate);
}
