play-example-form
=================

![screenshot](https://raw.github.com/ics-software-engineering/play-example-form/master/doc/play-example-form-homepage.png)


Overview
--------

This application provides an example of form processing with the following features:

  * [WebJars](http://www.webjars.org/) to download dependencies.
  * [Twitter Bootstrap 2.3.2](http://getbootstrap.com/2.3.2/)
  * Multiple twitter bootstrap-specific [helper templates](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/bootstrap)
  * Use of multi-valued form controls ([checkboxes](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/bootstrap/checkboxes.scala.html) and [multi-select](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/bootstrap/select.scala.html))
  * Separation of the form's [backing class](https://github.com/ics-software-engineering/play-example-form/blob/master/app/assemblies/StudentFormData.java) from the [model classes](https://github.com/ics-software-engineering/play-example-form/tree/master/app/models).
  * All validation done through a [validate() method](https://github.com/ics-software-engineering/play-example-form/blob/master/app/assemblies/Student.java#L46-97).
    
The design of this example differs in two significant ways from the "standard" Play examples. 

  1. **Distinct model and form classes.**  Most examples of form processing in Play "overload" the 
     model classes to serve two duties:  (1) specification of the database schema structure; and 
     (2) provide the "backing class" for forms.  Requiring a single class to perform these two duties 
     works well only when both the models and the forms are simple. In this example system, the
     views.formdata package provides classes for form processing, and the models package provides
     classes for database schemas.

  2. **Explicit field constructors for Twitter Bootstrap 2.x.**  The canonical recommendation for users of 
     Twitter Bootstrap 2.x is to create an implicit field constructor.  The problem with this recommendation
     is that a single implicit field constructor cannot satisfy all of Twitter Bootstrap's layout  
     requirements for form controls (for example, multiple checkboxes). This example illustrates
     a more general solution in which normal ("explicit") scala templates are defined in the 
     [views.bootstrap package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/bootstrap) for each of the Twitter Bootstrap controls. As a side benefit, the 
     code is significantly easier to understand and debug for Java-based Play framework users.  

Steps to understanding the system
---------------------------------

**Play with the application**

Begin by downloading the code, invoking "play run" in your shell, then retrieving http://localhost:9000 
to retrieve the single form as illustrated at the top of this page. The form displays the fields
associated with a "Student":  Name, Password, Hobbies, Level, GPA, and Majors.  Some of these
are required, some are optional. 

When you submit a valid version of the form, the system will redisplay the form with exactly the 
same data that you entered. 

** Look at the controller **

Now review the controller class [Application](https://github.com/ics-software-engineering/play-example-form/blob/master/app/controllers/Application.java)
has just two methods: getIndex() which displays the form in the index page and postIndex() that processes a form submission
from the index page. 

The getIndex method takes a Student ID as a parameter. If the value is 0, then an empty form is
displayed, otherwise the form is displayed pre-filled with the data associated with the Student ID.
For example, you can retrieve the data for the student with ID 1 using: http://localhost:9000?id=1.
The system instantiates a couple of students on startup. 

By looking at the controller, you can see that 
        
Playing with the application
----------------------------

You can play with a live version of the application at: http://play-form-kludge.philipmjohnson.cloudbees.net

For examples of prefilling the form in a valid manner, retrieve the following URLs:

  * http://play-form-kludge.philipmjohnson.cloudbees.net?id=1
  * http://play-form-kludge.philipmjohnson.cloudbees.net?id=2

Press submit, then look below the form to see the valid Student instance that was constructed.

For an example of prefilling the form in an invalid manner, retrieve the following URL:

  * http://play-form-kludge.philipmjohnson.cloudbees.net?id=3

Press submit, then look below the form to see the resulting (invalid) Student instance.
    








