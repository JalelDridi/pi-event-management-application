package com.esprit.usermicroservice.servicesImpl;

import com.esprit.usermicroservice.dtos.AuthenticationRequest;
import com.esprit.usermicroservice.dtos.AuthenticationResponse;
import com.esprit.usermicroservice.dtos.RegistrationRequest;
import com.esprit.usermicroservice.dtos.UserNotifDto;
import com.esprit.usermicroservice.entities.Token;
import com.esprit.usermicroservice.entities.User;
import com.esprit.usermicroservice.repositories.RoleRepository;
import com.esprit.usermicroservice.repositories.TokenRepository;
import com.esprit.usermicroservice.repositories.UserRepository;
import com.esprit.usermicroservice.services.JwtService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;
    private final TokenRepository tokenRepository;

    @Value("${application.mailing.frontend.activation-url}")
    private String activationUrl;

    public void register(RegistrationRequest request) throws MessagingException {
        var userRole = roleRepository.findByName("USER")
                // todo - better exception handling
                .orElseThrow(() -> new IllegalStateException("ROLE USER was not initiated"));
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();
        userRepository.save(user);
        sendValidationEmail(user);
    }
////hedhiiii
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        User user = (User) auth.getPrincipal();
        String userId = user.getUserID(); // Assuming user ID is a field in the User entity

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", userId); // Add the user ID to the claims

        String token = jwtService.generateToken(claims, user);

        return AuthenticationResponse.builder()
                .token(token)
                .userId(userId)
                .build();
    }

    //@Transactional
    public void activateAccount(String token) throws MessagingException {
        // Log the token value received as input
        System.out.println("Received activation token: " + token);

        Token savedToken = tokenRepository.findByToken(token)
                // todo exception has to be defined
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        // Log the token retrieved from the repository
        System.out.println("Token retrieved from repository: " + savedToken.getToken());

        if (LocalDateTime.now().isAfter(savedToken.getExpiresAt())) {
            sendValidationEmail(savedToken.getUser());
            throw new RuntimeException("Activation token has expired. A new token has been send to the same email address");
        }

        var user = userRepository.findById(savedToken.getUser().getUserID())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        user.setEnabled(true);
        userRepository.save(user);

        savedToken.setValidatedAt(LocalDateTime.now());
        tokenRepository.save(savedToken);
    }

    private String generateAndSaveActivationToken(User user) {
        // Generate a token
        String generatedToken = generateActivationCode(6);
        var token = Token.builder()
                .token(generatedToken)
                .createdAt(LocalDateTime.now())
                .expiresAt(LocalDateTime.now().plusMinutes(15))
                .user(user)
                .build();
        tokenRepository.save(token);

        return generatedToken;
    }

    private void sendValidationEmail(User user) throws MessagingException {
        var newToken = generateAndSaveActivationToken(user);
        String notificationServiceUrl = "http://apigateway/notification/confirm-user";
        UserNotifDto userNotifDto = new UserNotifDto();
        // Set:
        userNotifDto.setContent(newToken);
        userNotifDto.setSubject("Acccount activation code");
        userNotifDto.setFullName(user.getFullName());
        userNotifDto.setEmail(user.getEmail());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<UserNotifDto> requestEntity = new HttpEntity<>(userNotifDto, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(
                notificationServiceUrl,
                requestEntity,
                String.class
        );

        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            // Email sent successfully
            System.out.println("Confirmation email sent successfully.");
        } else {
            // Failed to send email
            throw new RuntimeException("Failed to send confirmation email. Status code: " + responseEntity.getStatusCode());
        }
//        emailService.sendEmail(
//                user.getEmail(),
//                user.getFullName(),
//                EmailTemplateName.ACTIVATE_ACCOUNT,
//                activationUrl,
//                newToken,
//                "Account activation"
//        );
    }

    private String generateActivationCode(int length) {
        String characters = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();

        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(characters.length());
            codeBuilder.append(characters.charAt(randomIndex));
        }
        // Log the generated token
        System.out.println("Generated activation token: " + codeBuilder.toString());

        return codeBuilder.toString();
    }
}
