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
    
There are two features of the Play framework that are conspicuously absent from this implementation:
  1. Annotation-based form validation.
  2. Custom data binding.

These seem like cool features, and they probably work great for situations in which you do not need
to display and validate lists of objects in your form fields. I could not figure out to get them to 
work for lists of checkboxes and multiple-selection lists.  The advantage of this code is that it is
simple to understand and simple to debug.  

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

The getIndex method takes a Student ID as a parameter. If the value is 0, then the 
        
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
    








