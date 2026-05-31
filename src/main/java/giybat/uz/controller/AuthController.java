package giybat.uz.controller;

import giybat.uz.dto.RegistrationDTO;
import giybat.uz.service.AuthService;
import jakarta.persistence.criteria.CriteriaBuilder;
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

    @GetMapping("/registration/verification/{profileId}")
    public ResponseEntity<String> regVerification(@Valid @RequestBody RegistrationDTO dto, @PathVariable Integer profileId) {
        return ResponseEntity.ok().body(authService.regVerification(profileId));
    }


}
