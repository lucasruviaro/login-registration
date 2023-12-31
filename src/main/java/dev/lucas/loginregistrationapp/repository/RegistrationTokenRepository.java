package dev.lucas.loginregistrationapp.repository;

import dev.lucas.loginregistrationapp.model.RegistrationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface RegistrationTokenRepository extends JpaRepository<RegistrationToken, Long> {


    Optional<RegistrationToken> findByToken(String token);

    @Transactional
    @Modifying
    @Query("UPDATE RegistrationToken c " +
            "SET c.confirmedAt = ?2 " +
            "WHERE c.token = ?1")
    int updateConfirmedAt(String token,
                          LocalDateTime confirmedAt);
}
