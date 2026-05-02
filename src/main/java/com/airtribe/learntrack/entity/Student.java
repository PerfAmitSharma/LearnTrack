package com.airtribe.learntrack.entity;

public class Student extends Person {

    private String batch;
    private boolean active;

    public Student(int id, String firstName, String lastName, String email, String batch) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String batch) {
        super(id, firstName, lastName, "");
        this.batch = batch;
        this.active = true;
    }

    public Student(int id, String firstName, String lastName, String email, String batch, boolean active) {
        super(id, firstName, lastName, email);
        this.batch = batch;
        this.active = active;
    }

    public String getBatch() {
        return batch;
    }

    public boolean isActive() {
        return active;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String getDisplayName() {
        return "Student: " + super.getDisplayName();
    }

    @Override
    public String toString() {
        return super.toString() + ", Batch: " + batch + ", Active: " + active;
    }
}
