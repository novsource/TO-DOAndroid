package com.example.to_doandroid.Model;

public class Task {
    String taskDate; // Дата задачи
    String taskDoesTitle;// Название задачи
    String taskNote;
    Boolean taskCB; // Статус задачи: выполнена или нет

    public Task() {
    }

    public Task(String taskDate, String taskDoesTitle, String taskNote, boolean taskCB) {
        this.taskDate = taskDate;
        this.taskDoesTitle = taskDoesTitle;
        this.taskCB = taskCB;
        this.taskNote = taskNote;
    }

    public String getTaskDoesTitle() {
        return taskDoesTitle;
    }

    public Boolean getTaskCB() {
        return taskCB;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public String getTaskNote() {
        return taskNote;
    }

    public void setTaskNote(String taskNote) {
        this.taskNote = taskNote;
    }

    public void setTaskCB(Boolean taskCB) {
        this.taskCB = taskCB;
    }

    public void setTaskDoesTitle(String taskDoesTitle) {
        this.taskDoesTitle = taskDoesTitle;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }
}
