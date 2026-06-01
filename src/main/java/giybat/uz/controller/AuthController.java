package giybat.uz.controller;

import giybat.uz.dto.RegistrationDTO;
import giybat.uz.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration( @Valid @RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok().body(authService.registration(dto));
    }

    @GetMapping("/registration/verification/{verificationCode}")
    public ResponseEntity<String> regVerification(@PathVariable("verificationCode") String verificationCode) {
        return ResponseEntity.ok().body(authService.regVerification(verificationCode));
    }


}
