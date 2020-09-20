package com.batch.repo;

import com.batch.entity.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContractRepo extends JpaRepository<Contract, String> {
}
