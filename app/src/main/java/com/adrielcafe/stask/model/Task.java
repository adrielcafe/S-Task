package com.adrielcafe.stask.model;

import com.orm.SugarRecord;

public class Task extends SugarRecord<Task> {
    private String title;
    private String description;
    private boolean completed;

    public Task(){

    }

    public Task(String title, String description, boolean completed){
        this.setTitle(title);
        this.setDescription(description);
        this.setCompleted(completed);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}