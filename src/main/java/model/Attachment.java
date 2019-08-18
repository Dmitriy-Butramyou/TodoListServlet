package model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Attachment {

    private Long id;
    private Long taskId;
    private String originalName;
    private String generatedName;
    private String generatedPath;

    public Attachment(Long taskId, String originalName, String generatedName, String generatedPath) {
        this.taskId = taskId;
        this.originalName = originalName;
        this.generatedName = generatedName;
        this.generatedPath = generatedPath;
    }
}
