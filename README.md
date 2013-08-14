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
  * Separation of the form's [backing class](https://github.com/ics-software-engineering/play-example-form/blob/master/app/assemblies/Student.java) from the [model classes](https://github.com/ics-software-engineering/play-example-form/tree/master/app/models).
  * [Validation](https://github.com/ics-software-engineering/play-example-form/blob/master/app/assemblies/Student.java#L46-97).

    
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
    








