package com.github.smdj.marusei.service;

import com.github.smdj.marusei.domain.Account;
import com.github.smdj.marusei.domain.Credential;
import com.github.smdj.marusei.jpa.entity.AccountEntity;
import com.github.smdj.marusei.jpa.entity.CredentialEntity;
import com.github.smdj.marusei.jpa.repository.AccountRepository;
import com.github.smdj.marusei.jpa.repository.CredentialRepository;
import com.github.smdj.marusei.service.params.CreateAccountParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
class AccountServiceImpl implements AccountService {
    private static final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CredentialRepository credentialRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Account create(CreateAccountParams createAccountParams) {
        if (log.isTraceEnabled()) {
            log.trace("createAccountParams = {}", createAccountParams);
        }

        Instant now = Instant.ofEpochMilli(System.currentTimeMillis());
        Account account = new AccountEntity(createAccountParams.getEmail(), createAccountParams.getNickname(), now);
        account = accountRepository.saveAndFlush((AccountEntity) account);

        Credential credentialEmail = new CredentialEntity(account,
                createAccountParams.getEmail(),
                passwordEncoder.encode(createAccountParams.getPassword()),
                now);
        credentialRepository.saveAndFlush((CredentialEntity) credentialEmail);
        Credential credentialNickname = new CredentialEntity(account,
                createAccountParams.getNickname(),
                passwordEncoder.encode(createAccountParams.getPassword()),
                now);
        credentialRepository.saveAndFlush((CredentialEntity) credentialNickname);

        return account;
    }
}
