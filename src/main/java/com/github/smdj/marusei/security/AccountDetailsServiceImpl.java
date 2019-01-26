package com.github.smdj.marusei.security;

import com.github.smdj.marusei.domain.Credential;
import com.github.smdj.marusei.jpa.repository.CredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
public class AccountDetailsServiceImpl implements AccountDetailsService {
    private static final Logger log = LoggerFactory.getLogger(AccountDetailsServiceImpl.class);

    @Autowired
    private CredentialRepository credentialRepository;

    @Override
    public AccountDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (log.isTraceEnabled()) {
            log.trace("loadUserByUsername args = username={}", username);
        }

        Credential credential = credentialRepository.findOneByPublicKey(username);

        if (null == credential) {
            if (log.isInfoEnabled()) {
                log.info("log-in load fail = username={}", username);
            }
            throw new UsernameNotFoundException(format("account does not exists = username=%s", username));
        }

        if (log.isInfoEnabled()) {
            log.info(format("log-in load success = username=%s", username));
        }

        AccountDetails details = new BasicAccountDetails(
                credential.getAccount().getId(),
                credential.getAccount().getNickname(),
                credential.getAccount().getEmail(),
                credential.getSecretHash()
        );

        if (log.isInfoEnabled()) {
            log.trace("loadUserByUsername return = {}", details);
        }

        return details;
    }
}
