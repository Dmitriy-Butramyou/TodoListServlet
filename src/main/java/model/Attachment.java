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
    private String mimeType;
}
