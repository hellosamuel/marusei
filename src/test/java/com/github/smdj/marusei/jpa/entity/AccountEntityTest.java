package com.github.smdj.marusei.jpa.entity;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AccountEntityTest {
    private static final Logger log = LoggerFactory.getLogger(AccountEntityTest.class);

    @Test
    public void test_new() {
        // given
        String email = "hellosamuel@google.com";
        String nickname = "hellosamuel";
        Instant createdAt = Instant.now();

        log.debug("Method_Step = {}, email = {}, nickname = {}, createdAt = {}", "given", email, nickname, createdAt);

        // when
        AccountEntity accountEntity = new AccountEntity(email, nickname, createdAt);

        log.debug("Method_Step = {}, accountEntity = {}", "when", accountEntity);

        // then
        assertThat(accountEntity)
                .isNotNull()
                .extracting(AccountEntity::getId, AccountEntity::getEmail, AccountEntity::getNickname, AccountEntity::getCreatedAt, AccountEntity::getUpdatedAt)
                .containsSequence(0, email, nickname, createdAt, createdAt);
    }

    @Test
    public void test_new_with_nulls() {
        assertThatThrownBy(() -> new AccountEntity(null, null, null))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
