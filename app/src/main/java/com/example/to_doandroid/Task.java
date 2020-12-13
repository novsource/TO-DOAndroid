package com.example.to_doandroid;

public class Task {
    String taskDoesTitle;
    Boolean taskCB;

    public Task() {
    }

    public Task(String taskDoesTitle, boolean taskCB) {
        this.taskDoesTitle = taskDoesTitle;
        this.taskCB = taskCB;
    }

    public String getTaskDoesTitle() {
        return taskDoesTitle;
    }

    public Boolean getTaskCB() {
        return taskCB;
    }

    public void setTaskDoesTitle(String taskDoesTitle) {
        this.taskDoesTitle = taskDoesTitle;
    }
}
