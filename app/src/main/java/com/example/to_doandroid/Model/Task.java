package com.example.to_doandroid.Model;

public class Task {
    int id; // Уникальный идентификатор
    String date; // Дата задачи
    String title;// Название задачи
    String note; // заметка задачи
    Boolean status; // Статус задачи: выполнена или нет

    public Task() {
    }

    public Task(String date, String title, String note, boolean status) {
        this.date = date;
        this.title = title;
        this.status = status;
        this.note = note;
    }

    public int getTaskId() {
        return id;
    }

    public void setTaskId(int id) {
        this.id = id;
    }

    public String getTaskDoesTitle() {
        return title;
    }

    public Boolean getTaskCB() {
        return status;
    }

    public String getTaskDate() {
        return date;
    }

    public String getTaskNote() {
        return note;
    }

    public void setTaskNote(String note) {
        this.note = note;
    }

    public void setTaskCB(Boolean status) {
        this.status = status;
    }

    public void setTaskDoesTitle(String title) {
        this.title = title;
    }

    public void setTaskDate(String date) {
        this.date = date;
    }
}
