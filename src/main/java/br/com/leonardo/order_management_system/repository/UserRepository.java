package br.com.leonardo.order_management_system.repository;

import br.com.leonardo.order_management_system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
