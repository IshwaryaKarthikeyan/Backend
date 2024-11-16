package com.hexa.amazecare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hexa.amazecare.dto.GenUserDTO;
import com.hexa.amazecare.dto.LoginResponse;
import com.hexa.amazecare.dto.PatientDTO;
import com.hexa.amazecare.entity.GenUser;
import com.hexa.amazecare.entity.GenUserPrincipal;
import com.hexa.amazecare.entity.Patient;
import com.hexa.amazecare.repository.GenUserRepository;
import com.hexa.amazecare.repository.PatientRepository;
import com.hexa.amazecare.service.JWTService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private GenUserRepository userRepository;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    private PatientRepository patientRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    @Autowired
    private AuthenticationManager authenticationManager;

    // Register a new patient
    @PostMapping("/register/patient")
    public ResponseEntity<String> registerPatient(@RequestBody PatientDTO patientDTO) {
        // Check if the email already exists
        if (userRepository.findByEmail(patientDTO.getUser().getEmail()) != null) {
            return ResponseEntity.status(409).body("Email already registered");
        }

        // Create a new User for the patient
        GenUser user = new GenUser();
        user.setEmail(patientDTO.getUser().getEmail());
        user.setPassword(passwordEncoder.encode(patientDTO.getUser().getPassword()));
        user.setRole("PATIENT");

        // Create a new Patient
        Patient patient = new Patient();
        patient.setUser(user);
        patient.setFullName(patientDTO.getFullName());
        patient.setDateOfBirth(patientDTO.getDateOfBirth());
        patient.setGender(patientDTO.getGender());
        patient.setContactNumber(patientDTO.getContactNumber());

        patientRepository.save(patient);

        return ResponseEntity.ok("Patient registered successfully");
    }

    // Login for both patients and doctors
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody GenUserDTO userDTO) {
        try {
            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDTO.getEmail(), userDTO.getPassword())
            );

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            String token = jwtService.generateToken(userDTO.getEmail());

            // Get the authenticated user's details from GenUserPrincipal
            GenUserPrincipal userDetails = (GenUserPrincipal) authentication.getPrincipal();
            GenUser user = userDetails.getUser();  // Access the original GenUser object

            // Create the response object
            LoginResponse loginResponse = new LoginResponse(
                token, user.getEmail(), user.getRole(), user.getId()
            );

            // Return the response with token and user info
            return ResponseEntity.ok(loginResponse);

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            // Incorrect username or password
            return ResponseEntity.status(401).body("Invalid email or password. Please check your credentials.");

        } catch (org.springframework.security.authentication.LockedException e) {
            // Account is locked
            return ResponseEntity.status(423).body("Account is locked. Please contact support.");

        } catch (org.springframework.security.authentication.DisabledException e) {
            // Account is disabled
            return ResponseEntity.status(403).body("Account is disabled. Please contact support.");

        } catch (org.springframework.security.authentication.AccountExpiredException e) {
            // Account has expired
            return ResponseEntity.status(403).body("Account has expired. Please contact support.");

        } catch (Exception e) {
            // Other unexpected errors
            return ResponseEntity.status(500).body("An unexpected error occurred. Please try again later.");
        }
    }
}
