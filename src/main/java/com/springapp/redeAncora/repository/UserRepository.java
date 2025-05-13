package com.springapp.redeAncora.repository;

import com.springapp.redeAncora.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
