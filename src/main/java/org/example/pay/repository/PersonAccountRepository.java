package org.example.pay.repository;

import org.example.pay.dto.PersonAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonAccountRepository extends JpaRepository<PersonAccount, String> {
}
