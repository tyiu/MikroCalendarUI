MikroCalendarUI
===============

Partial UI code for MikroCalendar, which was permittedly open-sourced to other students for use in their assignment.

This code has been open-sourced to be used as an example for developers.
Intended to show a possible approach for building, in Java, non-blocking UI for network related interactions.
This approach can also be applied with heavy processing tasks and file I/O.

Author
------
Terry Yiu

Originally Written For
----------------------
University of Waterloo
Spring 2011

CS 349
Assignment 4
http://hci.uwaterloo.ca/courses/cs349/s11/assignments/assignment_4.html

Login UI Notes
--------------

1. The event dispatching thread will not block if it takes a long time to login to a Twitter service because the login operation is executed on a separate thread. While the login is processing, the dialog components is disabled and the mouse cursor is turned into a throbber.

2. By default, LoginDialogFactory.createLoginDialog() will create a login dialog that will connect to a Twitter service using the provided TwitterEventManager class. If you want to use the LocalEventManager class instead, you need to call LoginDialogFactory.createLoginDialog(true) instead. This change will cause the application to create a login window that will use a JSON file instead of Twitter services. Through this dialog, you can choose which JSON file on your file system you want to connect to through the JFileChooser.

3. You have to open your main JFrame after a successful login. The place to plug in your code is in the LoginAction class on line 111. There is a TODO marker there. You will need to pass the MikroEventManager object into whatever class that handles the main frame.

4. Ignore the serialization warnings. We do not need to serialize Swing components for our assignment.

Date Time Picker UI Notes
-------------------------
To use it, create an instance of DateTimePickerImpl and call showDateTimePicker().
DateTimePickerDialog (a modal dialog) should pop up which allows the user to select the start/end month/day/year/hour/minute.
The OK button will be enabled only when all of the start date time fields have been entered.
If the end date time fields have not all been entered, then it is assumed to be null.
