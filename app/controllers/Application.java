package controllers;

import models.Student;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

import java.util.Map;

/**
 * The controller for the home page of this application.
 *
 * @author Philip Johnson
 */
public class Application extends Controller {

  public static Result getIndex(long id) {
    Student student = (id == 0) ? new Student() : Student.findStudent(id);
    Form<Student> form = Form.form(Student.class);
    return ok(index.render(form));
  }

  public static Result postIndex() {

    Form<Student> form = Form.form(Student.class);
    // Retrieve the submitted form data from the request object.
    Form<Student> bound = form.bindFromRequest();

    if (bound.hasErrors()) {
      return badRequest(index.render(bound));
    }
    else {
      return ok(index.render(bound));
    }
  }
}
