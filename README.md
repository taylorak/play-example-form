play-example-form
=================

![screenshot](https://raw.github.com/ics-software-engineering/play-example-form/master/doc/play-example-form-homepage.png)

Overview
--------

This application provides an example of form processing with the following features:

  * [WebJars](http://www.webjars.org/) to download dependencies.
  * [Twitter Bootstrap 2.3.2](http://getbootstrap.com/2.3.2/)
  * Individual twitter bootstrap-specific [helper templates](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/bootstrap)
  * Separation of [form backing classes](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/formdata) from [model classes](https://github.com/ics-software-engineering/play-example-form/tree/master/app/models).
  * All validation done through a [validate() method](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/formdata/StudentFormData.java#L57-123).
    
The design of this example differs in two significant ways from the traditional Play form processing examples. 

  1. **Distinct model and form classes.**  Most examples of form processing in Play "overload" the 
     model classes to serve two tasks:  (1) specify the database schema structure; and 
     (2) provide the "backing class" for forms.  Requiring a single class to perform these two tasks
     works well only when the models and forms are both simple and similar in structure. In this example system, the
     [views.formdata package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/formdata) provides 
     classes for form processing, and the [models package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/models) provides
     classes for database schemas. Since Play requires the backing classes for forms to have public fields,
     this separation means that model classes can have private fields, avoiding [well documented problems](http://www.manning-sandbox.com/thread.jspa?messageID=143570&#143570). 

  2. **Explicit field constructors for Twitter Bootstrap 2.x.**  The canonical recommendation for users of 
     Twitter Bootstrap 2.x is to create a single "implicit" field constructor.  The problem with this recommendation
     is that a single implicit field constructor cannot satisfy all of Twitter Bootstrap's layout
     requirements for form controls (for example, multiple checkboxes). This example illustrates
     a more general solution in which normal (i.e. "explicit") scala templates (i.e. field constructors) are defined in the 
     [views.bootstrap package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/bootstrap) for each of the Twitter Bootstrap controls. IMHO, the 
     code is significantly easier to understand and debug for Java-based Play framework users.  

Steps to understanding the system
---------------------------------

**Play with the application.**

Begin by downloading the code, invoking "play run" in your shell, then retrieving http://localhost:9000 
to retrieve the single form as illustrated at the top of this page. The form displays the fields
associated with a "Student":  Name, Password, Hobbies, Level, GPA, and Majors.  Some of these
are required, some are optional. 

When you submit a valid version of the form, the system will redisplay the form with exactly the 
same data that you entered. 

**Review the controller.**

Now review the controller class. [Application](https://github.com/ics-software-engineering/play-example-form/blob/master/app/controllers/Application.java)
has just two methods: getIndex() which displays the form in the index page and postIndex() that processes a form submission
from the index page. See the [routes](https://github.com/ics-software-engineering/play-example-form/blob/master/conf/routes) file for how this is wired up.

The getIndex method takes a Student ID as a parameter. If the value is 0, then an empty form is
displayed, otherwise the form is displayed pre-filled with the data associated with the Student ID.
For example, you can retrieve the data for the student with ID 1 using: http://localhost:9000/?id=1.
The system [instantiates a couple of students on startup](https://github.com/ics-software-engineering/play-example-form/blob/master/app/models/Student.java#L168-180). 

By looking at the controller, you can see the basic approach for either form display (HTTP GET) or 
form submission (HTTP POST):
  
  * An instance of StudentFormData is passed to the templates for rendering. This class has public
    fields as required by Play, and they are all String or List[String] because binding only works on strings.
    
  * Other component entities (Hobby, GradeLevel, GradePointAverage, Major) provide helper methods
    to support display of their values as strings along with the student's current value(s) for
    those components.
    
  * The [Student.makeInstance](https://github.com/ics-software-engineering/play-example-form/blob/master/app/models/Student.java#L165-185) and [Student.makeStudentFormData](https://github.com/ics-software-engineering/play-example-form/blob/master/app/models/Student.java#L150-162)
    methods provide conversion between the form data and model representations of a Student.

**Review the models.**

Skim through the [models package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/models). 
There should be no surprises; it parallels the form pretty closely.  Some things to note:

  * A Student entity contains non-primitive, complex components such as a list of Hobby entities and a list of Major entities.
  
  * The models have private fields and getters/setters. (Sorry, I'm old school that way.)     

**Review the views.**

The [views package](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views) 
is where things get most interesting.   The [main](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/main.scala.html)
and [index](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/index.scala.html)
templates are pretty much what you'd expect. 

Note that the main template imports JQuery which is needed for Bootstrap Javascript components and not normally shown
in the built-in Play examples. What is really not shown in the built-in Play examples is the 
fact that in order to test your code with HTMLUnit, you cannot use a version of JQuery more recent than 1.8.3.
Look at [Build.scala](https://github.com/ics-software-engineering/play-example-form/blob/master/project/Build.scala#L17-19)
to see how to load a newer version of Bootstrap with an older, HTMLUnit-compliant version of JQuery.

The second thing to review is the [views.bootstrap](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/bootstrap)
subpackage, containing Bootstrap 2.x Scala templates for various form controls. Kudos to [Jason
Pearson](https://github.com/kaeawc) to writing these templates and making other helpful changes; your t-shirt awaits.

Finally, the [views.formdata](https://github.com/ics-software-engineering/play-example-form/tree/master/app/views/formdata)
subpackage contains the single backing class ([StudentFormData](https://github.com/ics-software-engineering/play-example-form/blob/master/app/views/formdata/StudentFormData.java)) required for this application.
Note that the backing class consists of public fields containing the String data to be displayed/bound in the form,
as well as a validate() method that determines if the submitted form data is valid or not.
        
Issues
------

While this code works and is pretty easy to understand, there are at least two design problems with it
that I can see:

  * Verbosity.  It's kind of a drag to have two representations for a Student, one as a model and
    one as a backing class for forms.   I know that I presented this as a feature, but at the end
    of the day it's born of necessity.  Maybe Play will evolve one day to support composite entities
    (i.e. a Student that contains a List of Hobbies) in which display, binding, and validation
    can be done easily and understandably with a single class, but that day does not appear to 
    be here yet.
    
  * Integrity.  The current code encapsulates validation in the StudentFormData class, and certain
    methods (such as Student.makeInstance) must assume that they are being passed a valid
    StudentFormData instance.  That kind of assumption is worrisome, and annotation-based 
    constraints could avoid it.  Annotation-based constraints also offer the potential
    to simultaneously apply to both the database model and the form validation, which would be really
    nice.  It's too bad that no one has yet gotten annotation-based validation to work for
    this kind of simple situation. Maybe that will change in future.
    
Note: I played around for a while with [Custom Data Binding](http://www.playframework.com/documentation/2.0/JavaForms).
I could not get it to work correctly (i.e. binding and validation) for lists of entities (such as Hobbies). 
    
Acknowledgements
----------------

This example is a descendent of the original [play-form-kludge](https://github.com/philipmjohnson/play-form-kludge/tree/original)
and [Jason Pearson](https://github.com/kaeawc)'s [very helpful improvements](https://github.com/philipmjohnson/play-form-kludge).
    
