package com.adrielcafe.stask.model;

import com.orm.SugarRecord;

public class Task extends SugarRecord<Task> {
    public String title;
    public String description;
    public boolean completed;

    public Task(){ }
}