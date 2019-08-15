package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class User {

    private Long id;
    private String name;
    private String password;
}
