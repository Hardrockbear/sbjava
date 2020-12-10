package model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @Column (name = "name",nullable = false)
    private String name;

    @OneToOne(targetEntity = Avatar.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @OneToMany(targetEntity = Email.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "student_id")
    private List<Email> emails;

    @ManyToMany(targetEntity = Course.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "student_courses", joinColumns = @JoinColumn(name = "student_id"),inverseJoinColumns = @JoinColumn(name = "course_id"))
    private List<Course> courses;

    public Student(){}

    public Student(String name, Avatar avatar){
        this.name = name;
        this.avatar = avatar;
    }

    public Student(String name, Avatar avatar, List<Email> emails){
        this.name = name;
        this.avatar = avatar;
        this.emails = emails;
    }

    public Student(String name, Avatar avatar, List<Email> emails, List<Course> courses){
        this.name = name;
        this.avatar = avatar;
        this.emails = emails;
        this.courses = courses;
    }

    public long getId() {
        return id;
    }
    public List<Email> getEmails() {
        return emails;
    }
    public void setEmails( List<Email> emails ) {
        this.emails = emails;
    }
    public List<Course> getCourses() {
        return courses;
    }
    public void setCourses( List<Course> courses ) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
