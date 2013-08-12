package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple model class used for form data manipulation.
 */
public class Student {
  private long id;

  private String name;
  private String password;
  private List<Hobby> hobbies = new ArrayList<>(); // Hobbies are optional.
  private GradeLevel level;
  private GradePointAverage gpa;
  private List<Major> majors = new ArrayList<>(); // Majors are optional.

  public Student() {
  }

  public Student(long id, String name, String password, GradeLevel level, GradePointAverage gpa) {
    this.id = id;
    this.name = name;
    this.password = password;
    this.level = level;
    this.gpa = gpa;
  }

  public boolean hasHobby(String hobbyName) {
    for (Hobby hobby : this.hobbies) {
      if (hobbyName.equals(hobby.getName()))
        return true;
    }
    return false;
  }

  public void addHobby(Hobby hobby) {
    this.hobbies.add(hobby);
  }

  public boolean hasMajor(String majorName) {
    for (Major major : this.getMajors()) {
      if (majorName.equals(major.getName()))
        return true;
    }
    return false;
  }

  public String toString() {
    return String.format("[Student name: '%s' Password: '%s' Hobbies: %s Grade Level: %s GPA: %s Majors: %s]", this.getName(),
        this.getPassword(), this.hobbies, this.level, this.gpa, this.getMajors());
  }

  /**
   * @return the name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name the name to set
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * @return the password
   */
  public String getPassword() {
    return password;
  }

  /**
   * @param password the password to set
   */
  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * @return the level
   */
  public GradeLevel getLevel() {
    return level;
  }

  /**
   * @param level the level to set
   */
  public void setLevel(GradeLevel level) {
    this.level = level;
  }

  /**
   * @return the gpa
   */
  public GradePointAverage getGpa() {
    return gpa;
  }

  /**
   * @param gpa the gpa to set
   */
  public void setGpa(GradePointAverage gpa) {
    this.gpa = gpa;
  }

  /**
   * @return the majors
   */
  public List<Major> getMajors() {
    return majors;
  }

  /**
   * @param majors the majors to set
   */
  public void setMajors(List<Major> majors) {
    this.majors = majors;
  }

  public void addMajor(Major major) {
    this.majors.add(major);
  }

  // Fake a database of students.
  private static List<Student> allStudents = new ArrayList<>();

  public static assemblies.Student findStudent(long id) {
    for (Student student : allStudents) {
      if (student.id == id) {
        return new assemblies.Student(student.name, student.password, student.level, student.gpa, student.hobbies, student.majors);
      }
    }
    throw new RuntimeException("Couldn't find student");
  }
  public static Student getById(long id) {
    for (Student student : allStudents) {
      if (student.id == id) {
        return student;
      }
    }
    throw new RuntimeException("Couldn't find student");
  }

  static {
    // Valid student. No optional data supplied.
    allStudents.add(new Student(1L, "Joe Good", "mypassword", GradeLevel.findLevel("Freshman"), GradePointAverage.findGPA("4.0")));
    // Valid student with optional data.
    allStudents.add(new Student(2L, "Alice Good", "mypassword", GradeLevel.findLevel("Sophomore"), GradePointAverage.findGPA("4.0")));
    getById(2L).addHobby(Hobby.findHobby("Biking"));
    getById(2L).addHobby(Hobby.findHobby("Surfing"));
    getById(2L).addMajor(Major.findMajor("Chemistry"));
    getById(2L).addMajor(Major.findMajor("Physics"));
    // Invalid student. Password is too short.
    allStudents.add(new Student(3L, "Frank Bad", "pass", GradeLevel.findLevel("Freshman"), GradePointAverage.findGPA("4.0")));
  }

}
