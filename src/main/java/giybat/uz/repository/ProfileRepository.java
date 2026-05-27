package giybat.uz.repository;

import giybat.uz.entity.ProfileEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    // select * from profile where username =? and visible = true
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);
}
