package site.yacht.backend.domain.application.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.common.BaseTimeEntity;
import site.yacht.backend.domain.project.domain.Project;
import site.yacht.backend.domain.template.domain.Template;
import site.yacht.backend.domain.user.domain.User;

@Entity
@Getter
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "PROJECT_NAME_UNIQUE", columnNames = {"project_id", "name"})
})
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Application extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id", nullable = false)
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "template_id", nullable = false)
    private Template template;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, name = "git_url")
    private String gitUrl;

    @Lob
    @Column(nullable = false)
    private String valueYaml;

    @Builder
    public Application(Project project, User user, Template template, String name, String description, String gitUrl, String valueYaml) {
        this.project = project;
        this.user = user;
        this.template = template;
        this.name = name;
        this.description = description;
        this.gitUrl = gitUrl;
        this.valueYaml = valueYaml;
    }
}
