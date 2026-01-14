package br.com.leonardo.order_management_system.repository;

import br.com.leonardo.order_management_system.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {}
