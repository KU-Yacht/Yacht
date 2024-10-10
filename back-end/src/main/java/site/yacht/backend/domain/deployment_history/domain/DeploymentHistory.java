package site.yacht.backend.domain.deployment_history.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.common.BaseTimeEntity;
import site.yacht.backend.domain.application.domain.Application;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DeploymentHistory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private Application application;

    @Lob
    @Column(name = "generated_yaml", nullable = false)
    private String generatedYaml;

    @Column(name = "commit_id", nullable = false)
    private String commitId;

    @Column(name = "argo_workflow_id", nullable = false)
    private String argoWorkflowId;

    @Builder
    public DeploymentHistory(Application application, String generatedYaml, String commitId, String argoWorkflowId) {
        this.application = application;
        this.generatedYaml = generatedYaml;
        this.commitId = commitId;
        this.argoWorkflowId = argoWorkflowId;
    }
}
