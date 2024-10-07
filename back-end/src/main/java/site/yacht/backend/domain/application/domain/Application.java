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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Region region;

    @Lob
    @Column(nullable = false)
    private String valueYaml;

    @Column(nullable = false)
    private String name;

    private String description;

    @Column(nullable = false, name = "git_url")
    private String gitUrl;

    @Column(nullable = false)
    private String namespace;

    @Column(nullable = false, name = "replica_number")
    private int replicaNumber;
    private int cpu;
    private int memory;
    private int port;

    @Builder
    public Application(Project project, User user, Template template, Region region, String valueYaml, String name,
                       String description, String gitUrl, String namespace, int replicaNumber, int cpu, int memory, int port) {
        this.project = project;
        this.user = user;
        this.template = template;
        this.region = region;
        this.valueYaml = valueYaml;
        this.name = name;
        this.description = description;
        this.gitUrl = gitUrl;
        this.namespace = namespace;
        this.replicaNumber = replicaNumber;
        this.cpu = cpu;
        this.memory = memory;
        this.port = port;
    }
}
