package com.ms.email.application.ports;

import com.ms.email.application.domain.Email;
import java.util.Optional;
import java.util.UUID;

public interface EmailRepositoryPort {

    Email save(Email email);
    Optional<Email> findById(UUID emailId);
}
