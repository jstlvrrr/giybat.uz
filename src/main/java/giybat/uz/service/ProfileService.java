package giybat.uz.service;

import giybat.uz.entity.ProfileEntity;
import giybat.uz.exps.AppBadException;
import giybat.uz.repository.ProfileRepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;

    public ProfileEntity getById(Integer id) {
//        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
//        if (optional.isEmpty()) {
//            throw new AppBadException("Profile not found");
//        }
//        return optional.get();

        return profileRepository.findByIdAndVisibleTrue(id).orElseThrow(() -> new AppBadException("Profile not found"));
    }
}
