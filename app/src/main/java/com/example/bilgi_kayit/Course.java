package com.example.bilgi_kayit;

public class Course {
    private String name;
    private String note;
    private String studentCount;
    private String noteAvg;


    public Course(String name, String note, String studentCount, String noteAvg) {
        this.name = name;
        this.note = note;
        this.studentCount = studentCount;
        this.noteAvg = noteAvg;
    }

    public String getStudentCount() {
        return studentCount;
    }

    public String getNoteAvg() {
        return noteAvg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}

