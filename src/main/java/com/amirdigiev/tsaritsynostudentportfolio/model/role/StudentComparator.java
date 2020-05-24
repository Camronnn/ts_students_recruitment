package com.amirdigiev.tsaritsynostudentportfolio.model.role;

import java.util.Comparator;

public class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student student1, Student student2) {
        return Integer.compare(student1.getRating(), student2.getRating());
    }
}
