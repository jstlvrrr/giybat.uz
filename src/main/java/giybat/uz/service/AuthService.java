package giybat.uz.service;

import giybat.uz.dto.RegistrationDTO;
import giybat.uz.entity.ProfileEntity;
import giybat.uz.enums.GeneralStatus;
import giybat.uz.enums.ProfileRole;
import giybat.uz.exps.AppBadException;
import giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private ProfileRoleService profileRoleService;
    @Autowired
    private EmailSendingService emailSendingService;


    public String registration(RegistrationDTO dto) {

        Optional<ProfileEntity> optional = profileRepository.findByUsernameAndVisibleTrue(dto.getUsername());
        if (optional.isPresent()) {
            ProfileEntity profile = optional.get();
            if (profile.getStatus().equals(GeneralStatus.IN_REGISTRATION)) {
                profileRoleService.deleteRoles(profile.getId());
                profileRepository.delete(profile);
                // send sms
            }else {
                throw new AppBadException("Username already exists");
            }
        }

        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setUsername(dto.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        entity.setStatus(GeneralStatus.IN_REGISTRATION);
        entity.setVerificationCode(UUID.randomUUID().toString());
        entity.setVisible(true);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity); // save
        //Insert Roles
        profileRoleService.create(entity.getId(), ProfileRole.ROLE_USER);

        emailSendingService.sendRegistrationEmail(dto.getUsername(), entity.getVerificationCode());
        return "User created";
    }

    public String regVerification(String verificationCode) {
        int updatedCount = profileRepository.activateRegistration(
                verificationCode,
                GeneralStatus.IN_REGISTRATION,
                GeneralStatus.ACTIVE
        );
        if (updatedCount == 1) {
            return "User verified";
        }
        throw new AppBadException("Verification failed");
    }
}
