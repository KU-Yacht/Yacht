package site.yacht.backend.domain.template.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import site.yacht.backend.common.BaseTimeEntity;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Template extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String path;

    @Column(nullable = false, unique = true)
    private String title;

    @Column
    private String description;

    public Template(String path, String title, String description) {
        this.path = path;
        this.title = title;
        this.description = description;
    }

}
