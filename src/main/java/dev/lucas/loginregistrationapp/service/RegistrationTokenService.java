package dev.lucas.loginregistrationapp.service;

import dev.lucas.loginregistrationapp.model.RegistrationToken;
import dev.lucas.loginregistrationapp.repository.RegistrationTokenRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationTokenService {
    private final RegistrationTokenRepository registrationTokenRepository;

    public void saveConfirmationToken(RegistrationToken token) {
        registrationTokenRepository.save(token);
    }

    public Optional<RegistrationToken> getToken(String token) {
        return registrationTokenRepository.findByToken(token);
    }

    public int setConfirmedAt(String token) {
        return registrationTokenRepository.updateConfirmedAt(
                token, LocalDateTime.now());
    }
}
