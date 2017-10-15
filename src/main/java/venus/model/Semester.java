package venus.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "semesters")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Semester extends BaseEntity {
/*
    @Column(nullable = false)
    private LocalDate from;

    @Column(nullable = false)
    private LocalDate to;*/

    @OneToMany(mappedBy="semester", cascade={CascadeType.ALL})
    private List<Course> courses;
}
