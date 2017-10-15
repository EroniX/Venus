package venus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subjects")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Subject extends BaseEntity {

    @Column(nullable = false)
    private String Name;

    @Column(nullable = false, unique = true)
    private String Code;

    @ManyToOne
    @JoinColumn(name = "trainingid")
    private Training training;

    @OneToMany(mappedBy="subject", cascade={CascadeType.ALL})
    private List<Course> courses;
}
