----------------------------------------------------------------------------------------
# **Title: Cyberjaya Online Rental Managment System**

- properties will be listed by property types
- each properties will have their own special ID
- MVC(Model-View-Controller) pattern GUI (its best to take advantage of this and make it user friendly)
- save and load properties can be saved as txt file

----------------------------------------------------------------------------------------
## Features that are available for diff type of users

### **Admin**
- create more admin accounts
- can configure on all properties projects(view/comments/remove/add)
- can view a report 
	1. list of properties in the system
	2. list of property according to their property type
	3. list of property accroding to owner
	4. list of inactive property and who are the tenant (rented out)
	5. list of active property (no tenant yet/prev tenent moved out)
	6. list of projects/posts with comments
	7. list of facilities

### **Agent/Property Owner**
- list or publish their properties projects
- can edit their published projects (set to hidden/edit texts/ add or remove pictures)

### **Tenant**
- can view listt of projects

----------------------------------------------------------------------------------------
## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).

## Instuction on how to setup the launch.json

After you clone the repository, right click the GUI.java and click on run, it will then add the GUI.java build setting into launch.json. After that find the one you have added
and change the name from "Launch GUI" to "Launch_GUI_URNAME" and copy the content in the parentheses, do not copy the parentheses 
( "vmArgs": "--module-path javafx-sdk-11.0.2\\lib --add-modules javafx.controls,javafx.fxml" )
and paste it below the ( "mainClass": "GUI", ) line.
