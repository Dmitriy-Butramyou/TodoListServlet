package model;

import java.util.Date;

public class Task {

    private Long id;
    private String name;
    private String description;
    private Date eventDate;
    private Date creationDateTime;
    private State state;
    private String originalFileName;
    private String generatedFileName;
    private String generatedFilePath;
    private Long userId;

    public void setId(Long id) {
        this.id = id;
    }

    public void setEventDate(Date eventDate) {
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

    public Date getEventDate() {
        return eventDate;
    }

    public Date getCreationDateTime() {
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
        private Date eventDate;
        private Date creationDateTime;
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

        public Builder eventDate(Date eventDate) {
            this.eventDate = eventDate;
            return this;
        }

        public Builder creationDateTime(Date creationDateTime) {
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
