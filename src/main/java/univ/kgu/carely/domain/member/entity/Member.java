package univ.kgu.carely.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import univ.kgu.carely.domain.common.embeded.Address;
import univ.kgu.carely.domain.common.embeded.Skill;
import univ.kgu.carely.domain.common.enums.MemberType;
import univ.kgu.carely.domain.team.entity.TeamMate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate birth;

    private String story;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MemberType memberType;

    @Column(nullable = false)
    private Boolean isVisible;

    @Column(nullable = false)
    @ColumnDefault("false")
    @Builder.Default
    private Boolean isVerified = false;

    @Builder.Default
    private String profileImage = String.valueOf((int) (Math.random() * 10) + 1);

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    private LocalDateTime createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Embedded
    private Address address;

    @Embedded
    private Skill skill;

    // 연관관계 매핑

    @OneToMany(mappedBy = "member")
    @Builder.Default
    private Set<TeamMate> teamMates = new HashSet<>();
}
