# Blog

For now, this is simple blog. Features:
* sign up new user,
* sign in as user, 
* user can change own first name, last name and password,
* admin can ban user, so he/she won't be able to log in,
* in main page posts are sorted from newest to oldest,
* under every post comments from oldest to newest,
* add new post,
* comment existing posts,
* users can vote on every post/comment  (but not his own content),
* posts supports tags,
* You can search for post by tags,
* You can see user content (user profile page with posts created by him, oraz posts commented by him)

I create this project for tests spring boot framework. 

## Getting Started

You can build project with gradle, but the easiest way is create new project from version controll (IntelliJ support this). After this you can run project.

### Prerequisites

I used MySQL database installed on my computer for this project. If you want use another database you need to change properties in application.properties file.

## Starting the project

Property spring.jpa.hibernate.ddl-auto is set to create-drop, so project after start will create tables for You. 

After configuring database connection, you can look at InitDbService in service package. There are init() method which creating some basic data. Method creates:
* Role for admin and user,
* Admin and normal user,
* 12 posts, 11 with tags and 1 without tag,
* 4 comments, 2 for first post and 2 for last one.

You can change it if you want.

## Some screens

![Main page](https://preview.ibb.co/mHNB5d/1.png)
![Sign up](https://preview.ibb.co/fCKPQd/2.png)
![Sign in](https://preview.ibb.co/crw4Qd/3.png)
![Main page for logged user](https://preview.ibb.co/g41FWJ/4.png)
![Bottom of page](https://preview.ibb.co/b9G4Qd/5.png)
![My profile page](https://preview.ibb.co/eLndkd/6.png)
![User profile viewed by Admin](https://preview.ibb.co/gH8Jkd/7.png)


## Project will be develope in future. 

TODO list:
* Tag page.
* Image support.
* Support formating in post/comment.
* Add support for Polish.
* Private messages between users.
* Create automation tests.
* Use angular framework in front end.
* And some other stuff... :)


