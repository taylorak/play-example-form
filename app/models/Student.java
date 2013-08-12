package models;

import play.data.validation.Constraints.Required;
import play.data.validation.ValidationError;
import scala.annotation.meta.field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple model class used for form data manipulation.
 */
public class Student {
  private long id;

  @Required
  private String name = "";
  @Required
  private String password = "";
  private List<Hobby> hobbies = new ArrayList<>(); // Hobbies are optional.
  @Required
  private GradeLevel level = null;
  @Required
  private GradePointAverage gpa = null;
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

  /**
   * Validates Form<Student>
   *
   * @return list of errors
   */
  public List<ValidationError> validate() {

    List<ValidationError> errors = new ArrayList();

    // Password must be at least 5 characters
    if (password.length() < 5) {
      errors.add(new ValidationError("password", "A password of at least five characters is required."));
    }

    // Hobbies are Optional, but if supplied must exist in database.
    if (hobbies.size() > 0) {
      for (Hobby hobby : hobbies) {
        if (Hobby.findHobby(hobby.getName()) == null) {
          errors.add(new ValidationError("Hobbies", "Supplied hobby is not defined."));
        }
      }
    }

    // Process Grade Level. Required and must exist in database.

    if (GradeLevel.findLevel(level.getName()) == null) {
      errors.add(new ValidationError("Level", "Supplied grade level is not defined."));
    }

    // Process GPA. Required and must exist in database.
    if (GradePointAverage.findGPA(gpa.getName()) == null) {
      errors.add(new ValidationError("GPA", "Supplied GPA is not defined."));
    }

    // Process Majors. Optional, but if supplied must exist in database.
    if (majors.size() > 0) {
      for (Major major : majors) {
        if (Major.findMajor(major.getName()) == null) {
          errors.add(new ValidationError("Major", "Supplied major is not defined."));
        }
      }
    }

    return errors;
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

  public static Student findStudent(long id) {
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
    findStudent(2L).addHobby(Hobby.findHobby("Biking"));
    findStudent(2L).addHobby(Hobby.findHobby("Surfing"));
    findStudent(2L).addMajor(Major.findMajor("Chemistry"));
    findStudent(2L).addMajor(Major.findMajor("Physics"));
    // Invalid student. Password is too short.
    allStudents.add(new Student(3L, "Frank Bad", "pass", GradeLevel.findLevel("Freshman"), GradePointAverage.findGPA("4.0")));
  }

}
