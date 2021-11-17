package com.example.axontest.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AccountViewRepo extends JpaRepository<AccountView, UUID> {
}
