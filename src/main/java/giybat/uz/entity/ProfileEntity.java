package giybat.uz.entity;

import giybat.uz.enums.GeneralStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Table(name = "profile")
@Entity
@Getter
@Setter
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "username")
    private String username; // email or phone

    @Column(nullable = false, name = "password")
    private String password;

    @Column(nullable = false, name = "status")
    @Enumerated(EnumType.STRING)
    private GeneralStatus status; // ACTIVE, BLOCK

    @Column(nullable = false, name = "visible")
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
