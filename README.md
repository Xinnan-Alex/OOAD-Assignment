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
