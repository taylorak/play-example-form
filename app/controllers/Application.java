package controllers;

import assemblies.Student;
import models.GradeLevel;
import models.GradePointAverage;
import models.Hobby;
import models.Major;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

/**
 * The controller for the home page of this application.
 *
 * @author Philip Johnson
 */
public class Application extends Controller {

  public static Result getIndex(long id) {
    Student student = (id == 0) ? new Student() : models.Student.findStudent(id);
    Form<Student> form = Form.form(Student.class);
    return ok(index.render(
      form,
      Hobby.makeHobbyMap(student),
      GradeLevel.getNameList(),
      GradePointAverage.makeGPAMap(student),
      Major.makeMajorMap(student)
    ));
  }

  public static Result postIndex() {

    Form<Student> form = Form.form(Student.class);
    // Retrieve the submitted form data from the request object.
    Form<Student> bound = form.bindFromRequest();

    if (bound.hasErrors()) {
      return badRequest(index.render(bound,
        Hobby.makeHobbyMap(null),
        GradeLevel.getNameList(),
        GradePointAverage.makeGPAMap(null),
        Major.makeMajorMap(null)
      ));
    }
    else {
      return ok(index.render(bound,
      Hobby.makeHobbyMap(bound.get()),
      GradeLevel.getNameList(),
      GradePointAverage.makeGPAMap(bound.get()),
      Major.makeMajorMap(bound.get())
    ));
    }
  }
}
