package study.study1.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member2 extends BaseEntity{

    @Id
    private String email;

    private String password;

    private String name;
}
