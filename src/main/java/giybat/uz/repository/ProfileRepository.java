package giybat.uz.repository;

import giybat.uz.entity.ProfileEntity;
import giybat.uz.enums.GeneralStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    // select * from profile where username =? and visible = true
    Optional<ProfileEntity> findByUsernameAndVisibleTrue(String username);

    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);

    @Modifying
    @Transactional
    @Query("update ProfileEntity p set p.status = ?3, p.verificationCode = null where p.verificationCode = ?1 and p.status = ?2 and p.visible = true")
    int activateRegistration(String verificationCode, GeneralStatus currentStatus, GeneralStatus activeStatus);
}
