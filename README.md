play-example-form
=================

![screenshot](https://raw.github.com/philipmjohnson/play-example-form/master/doc/play-form-kludge-homepage.png)


Overview
--------

This application provides an example of form processing slightly different 
from the play example programs (forms and computer database) that demonstrates the following 
requirements:

  * Use of Twitter Bootstrap.
  * Use of multi-valued form controls (checkboxes and multi-select)
  * Use of a backing entity with nested entity and List of entity fields.
  * Validation.

    
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
    








