package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Task {

    private Long id;
    private String name;
    private String description;
    private Long eventDate;
    private Long creationDateTime;
    private State state;
    private Long userId;

    public Task(String name, String description, Long eventDate, Long creationDateTime, State state) {
        this.name = name;
        this.description = description;
        this.eventDate = eventDate;
        this.creationDateTime = creationDateTime;
        this.state = state;
    }
}
