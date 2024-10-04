package site.yacht.backend.domain.user_project.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.common.BaseTimeEntity;
import site.yacht.backend.domain.project.domain.Project;
import site.yacht.backend.domain.user.domain.User;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_id", "project_id"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserProject extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public UserProject(User user, Project project, Role role) {
        this.user = user;
        this.project = project;
        this.role = role;
    }

    public boolean hasNotInvitationPermission() {
        return role != Role.ROLE_ADMIN;
    }
}
