package com.ktds.devopshelper.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ktds.devopshelper.authentication.domain.Account;

//JpaRepository<Account, String> => <domain type, pk userId type>
@Repository
public interface AccountRepository extends JpaRepository<Account, String>{

}
