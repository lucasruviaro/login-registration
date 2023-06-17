package dev.lucas.loginregistrationapp.controller;

import dev.lucas.loginregistrationapp.dto.RegistrationRequest;
import dev.lucas.loginregistrationapp.service.RegistrationService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping("/")
    public String register(@RequestBody RegistrationRequest registrationRequest){
        return registrationService.register(registrationRequest);
    }
}
