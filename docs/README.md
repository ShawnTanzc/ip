# DUKE - User Guide
Duke is a command-line application that helps keep track of your everyday task with much convenience
While it is carefully build to cater the needs of student, but sure to have a smooth journey organizing
your task with Duke.

## Table of Contents
* [Quick Start](##Quick Start)
* [Features](##Features)
    * [Starting the program](###Starting the program)
    * [Adding deadline task](###Adding deadline task): `deadline`
    * [Adding event task](###Adding event task): `event`
    * [Adding todo task](###Adding todo task): `todo`
    * [Displaying task list](###Displaying task list): `list`
    * [Checking task as complete](###Checking task as complete): `done`
    * [Deleting task from list](###Deleting task from list): `delete`
    * [Finding keyword in list](###Finding keyword in list): `find`
    * [Exiting the program](###Exiting the program): `bye`
* [Command Summary](##Command Summary)

## Quick Start

1. Ensure that you have Java 11 or above installed in your computer.
1. Download the latest Duke.jar online.
1. Copy the file to the folder you want to use as the home folder for your Duke task manager
1. Double-click the file to start the app. A command line window will show up. 
1. Type your username as instructed by the programme. Enter any key to continue.
1. Type the command in the command box and press Enter to execute it.

    Here are some commands you can try:
    * `todo textbook`: Add "todo textbook" into task list.
    * `list`: List all current tasks.
    * `done 2`: Check the task in the 2nd index as completed.
    * `delete 3`: Delete the task in the 3rd index.
    * `find books`: Search through the task list for the keyword books.
    * `exit`: Save and exit Duke application.
1. Refer to the Features below for details of each commands
## Features 
Duke provides 3 categories of tasks to select from. They are `Deadline`, `Event` and `ToDo`. 
Other functions include `list`, `done`, `delete`, `find` and `exit`.

>Things to note:
>1. Words in **UPPERCASE** are the parameters to be supplied by user. |
>1. The user inputs are all case-sensitive.
>1. Format should be followed strictly as per user guide.

### Starting the program
Displays an introductory message and request for user's name

```
Hello from
 ____        _        
|  _ \ _   _| | _____ 
| | | | | | | |/ / _ \
| |_| | |_| |   <  __/
|____/ \__,_|_|\_\___|

_________________________________________
Hello I'm Duke
What is your name, sir?
_________________________________________

```
Upon receiving the username, Duke will ask user to enter any key to continue
```
Hello <username>! Press any key to continue.
_________________________________________

```
Meanwhile, Duke attempts to load the file `duke.txt` which was previously saved.

If successful:
```
Loading... Please Wait.
duke.txt has been loaded.
```
If unsuccessful: 
```
Loading... Please Wait.
Error! duke.txt cannot be found. 
```
###Adding deadline task
Adds a deadline type task to the task list

Format: `deadline TASK_NAME /by YYYY-MM-DD,HH:MM`

Parameters: 
* `TASK_NAME`: Label the deadline task with its name.
* `YYYY-MM-DD`: Include date of the deadline in the respective format.
* `HH-MM`: Include time of deadline in the respective format.

Examples:
* `deadline CS2113T Project Submission /by 2020-10-02,23:59`
```
_________________________________________
Got it. I've added this task: [D][✘] CS2113T Project Submission (by: Oct 2 2020, 11:59 PM)
Now you have 1 task(s) in the list.
_________________________________________
```
###Adding event task
Adds an event type task to the task list

Format: `event TASK_NAME /at TASK_DATE_TIME`

Parameters: 
* `TASK_NAME`: Label the event task with its name.
* `TASK_DATE_TIME`: Include relevant details of the event.

Examples:
* `event Engin Day /at this Monday 12pm - 4pm`
```
_________________________________________
Got it. I've added this task: [E][✘] Engin Day (at: this Monday 12pm - 4pm)
Now you have 2 task(s) in the list.
_________________________________________
```
###Adding todo task
Adds a todo type task to the task list

Format: `todo TASK_NAME`

Parameters:
* `TASK_NAME`: Label the todo task with its name. 

Examples:
* `todo CS2101 assignment`
```
_________________________________________
Got it. I've added this task: [T][✘] CS2101 assignment
Now you have 3 task(s) in the list.
_________________________________________
```
>Things to note:
>1. If Duke is unable to detect the keyword, 
>it will prompt user to use the correct keyword.
>1. If Duke detects the keyword, but incorrect 
>format provided, it will prompt user to follow the correct format.

* `toodo`
```
_________________________________________
 Task not detected. Use "todo", "deadline" or "event".
 _________________________________________
```
* `event Sports Day at night`
```
_________________________________________
 Missing details. Please use the correct format.
 _________________________________________
```
###Displaying task list
Displays the current tasks in the task list

Format: `list`

Examples: 
* `list`
```
_________________________________________
Here are the tasks in your list:
1. [D][✘] CS2113T Project Submission (by: Oct 2 2020, 11:59 PM)
2. [E][✘] Engin Day (at: this Monday 12pm - 4pm)
3. [T][✘] CS2101 assignment
_________________________________________
```
if the list doesn't have any task:
* `list`
```
List is currently empty. Please insert task.
``` 
###Checking task as complete
Sets the task on the specific index as done

Format: `done TASK_LIST_INDEX`

Parameters: 
* `TASK_LIST_INDEX`: Input the index of the task you have completed

Examples:
* `done 2` followed by `list`
```
_________________________________________
Nice! I've marked this task as done:
[E][✓] Engin Day (at: this Monday 12pm - 4pm)
_________________________________________
```
```
_________________________________________
Here are the tasks in your list:
1. [D][✘] CS2113T Project Submission (by: Oct 2 2020, 11:59 PM)
2. [E][✓] Engin Day (at: this Monday 12pm - 4pm)
3. [T][✘] CS2101 assignment
_________________________________________
```
If task do not exist: 
* `done 4`
```
_________________________________________
Task not created yet. Please create the task first.
_________________________________________
```
###Deleting task from list
Deletes the task on the specific index in the list

Format: `delete TASK_LIST_INDEX`

Parameters: 
* `TASK_LIST_INDEX`: Input the index of the task you want to delete

Examples:
* `delete 2` followed by `list`
```
_________________________________________
Noted. I've removed this task:
[E][✓] Engin Day (at: this Monday 12pm - 4pm)
Now you have 2 task(s) in the list.
_________________________________________
```
```
_________________________________________
Here are the tasks in your list:
1. [D][✘] CS2113T Project Submission (by: Oct 2 2020, 11:59 PM)
2. [T][✘] CS2101 assignment
_________________________________________
```
If task do not exist: 
* `delete 3`
```
_________________________________________
Task not created yet. Please create the task first.
_________________________________________
```
###Finding keyword in list
Finds any keyword the user inputs and extract the matching tasks.

Format: `find KEYWORD`

Parameters:
* `KEYWORD`: input the keyword of the task you wish to find in the list

Examples:
* `find CS2101`
```
_________________________________________
Here are the matching tasks in your list:
1. [T][✘] CS2101 assignment
_________________________________________
```
if `KEYWORD` is not found, the list will be empty: 
* `find cheesecake`
```
_________________________________________
Here are the matching tasks in your list:
_________________________________________
```
> Things to note:
> * `KEYWORD` input by user is case-sensitive.
###Exiting the program
Displays an exit message.
Saves a copy of the task list in `duke.txt` in your computer
before exiting the program.
 
Format: `bye`

Example:
* `bye`
```
_________________________________________
Task have been saved. File name: duke.txt
_________________________________________
Bye. Hope to see you again soon!
_________________________________________
```
if program is unable to be saved:
* `bye`
```
_________________________________________
Unable to save file.
_________________________________________
Bye. Hope to see you again soon!
_________________________________________
```
##Command Summary
Action | Format, Example
----------|----------
Deadline | `deadline TASK_NAME /by YYYY-MM-DD,HH:MM`, `deadline CS2113T Project Submission /by 2020-10-02,23:59`
Event | `event TASK_NAME /at TASK_DATE_TIME`, `event Engin Day /at this Monday 12pm - 4pm`
ToDo | `todo TASK_NAME`, `todo CS2101 assignment`
List | `list`, `list`
Done | `done TASK_LIST_INDEX`, `done 2`
Delete | `delete TASK_LIST_INDEX`, `delete 2`
Find | `find KEYWORD`, `find CS2101`

### ---- END OF USER GUIDE ----