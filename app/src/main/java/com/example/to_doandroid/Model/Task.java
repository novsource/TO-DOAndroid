package com.example.to_doandroid.Model;

public class Task {
    String taskDoesTitle;
    String date;
    Boolean taskCB;

    public Task() {
    }

    public Task(String taskDoesTitle, String date, boolean taskCB) {
        this.taskDoesTitle = taskDoesTitle;
        this.date = date;
        this.taskCB = taskCB;
    }

    public String getTaskDoesTitle() {
        return taskDoesTitle;
    }

    public Boolean getTaskCB() {
        return taskCB;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTaskCB(Boolean taskCB) {
        this.taskCB = taskCB;
    }

    public void setTaskDoesTitle(String taskDoesTitle) {
        this.taskDoesTitle = taskDoesTitle;
    }
}
