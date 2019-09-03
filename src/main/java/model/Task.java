package model;

public class Task {

    private Long id;
    private String name;
    private String description;
    private Long eventDate;
    private Long creationDateTime;
    private State state;
    private String originalFileName;
    private String generatedFileName;
    private String generatedFilePath;
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventDate(Long eventDate) {
        this.eventDate = eventDate;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Long getEventDate() {
        return eventDate;
    }

    public Long getCreationDateTime() {
        return creationDateTime;
    }

    public State getState() {
        return state;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getGeneratedFileName() {
        return generatedFileName;
    }

    public String getGeneratedFilePath() {
        return generatedFilePath;
    }

    public Long getUserId() {
        return userId;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public void setGeneratedFileName(String generatedFileName) {
        this.generatedFileName = generatedFileName;
    }

    public void setGeneratedFilePath(String generatedFilePath) {
        this.generatedFilePath = generatedFilePath;
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", eventDate=" + eventDate +
                ", creationDateTime=" + creationDateTime +
                ", state=" + state +
                ", originalFileName='" + originalFileName + '\'' +
                ", generatedFileName='" + generatedFileName + '\'' +
                ", generatedFilePath='" + generatedFilePath + '\'' +
                ", userId=" + userId;
    }

    private Task(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.description = builder.description;
        this.eventDate = builder.eventDate;
        this.creationDateTime = builder.creationDateTime;
        this.state = builder.state;
        this.originalFileName = builder.originalFileName;
        this.generatedFileName = builder.generatedFileName;
        this.generatedFilePath = builder.generatedFilePath;
        this.userId = builder.userId;
    }
    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Long eventDate;
        private Long creationDateTime;
        private State state;
        private String originalFileName;
        private String generatedFileName;
        private String generatedFilePath;
        private Long userId;

        public Builder() {
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder eventDate(Long eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder creationDateTime(Long creationDateTime) {
            this.creationDateTime = creationDateTime;
            return this;
        }

        public Builder state(State state) {
            this.state = state;
            return this;
        }

        public Builder originalFileName(String originalFileName) {
            this.originalFileName = originalFileName;
            return this;
        }

        public Builder generatedFileName(String generatedFileName) {
            this.generatedFileName = generatedFileName;
            return this;
        }

        public Builder generatedFilePath(String generatedFilePath) {
            this.generatedFilePath = generatedFilePath;
            return this;
        }

        public Builder userId(Long userId) {
            this.userId = userId;
            return this;
        }
        public Task build() {
            return new Task(this);
        }
    }
}
