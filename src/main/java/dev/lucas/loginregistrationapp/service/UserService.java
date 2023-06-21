package dev.lucas.loginregistrationapp.service;

import dev.lucas.loginregistrationapp.model.RegistrationToken;
import dev.lucas.loginregistrationapp.model.User;
import dev.lucas.loginregistrationapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RegistrationTokenService registrationTokenService;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("E-mail not found."));
    }

    public String signUpUser(User user) {
     /*   boolean userExists = userRepository
                .findByEmail(user.getEmail())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }


      */

        String encodedPassword = bCryptPasswordEncoder
                .encode(user.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);

        String token = UUID.randomUUID().toString();

        RegistrationToken registrationToken = new RegistrationToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15),
                user
        );

        registrationTokenService.saveRegistrationToken(
                registrationToken);

        return token;
    }

    public int enableUser(String email) {
        return userRepository.enableUser(email);
    }
}
