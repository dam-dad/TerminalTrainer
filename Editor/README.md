# Terminal Trainer Editor

**Members**

* Steven Francisco Arteaga Mesa

* Alberto Barroso Carrillo

* Aitor Romojaro Martínez

**Instalation**

The app can be downloaded from  [here.][id]

[id]: https://github.com/dam-dad/TerminalTrainer/releases/tag/1.0

**Screenshots**

![editor-dark](C:\Users\Aitor\Pictures\CapturasTT\editor-dark.PNG)

![editor-ejemplo](C:\Users\Aitor\Pictures\CapturasTT\editor-ejemplo.PNG)![editor-light](C:\Users\Aitor\Pictures\CapturasTT\editor-light.PNG)



**Documentation**

The project has the following packages:

* `dad.javafx.terminaltrainer.editor` Contains applicaction's main class.
* `dad.javafx.terminaltrainer.editor.model` Contains application's data.
* `dad.javafx.terminaltrainer.editor.ui.app` The app's window.
* `dad.javafx.terminaltrainer.editor.ui.controller` Functionality of the app.
* `dad.javafx.terminaltrainer.editor.utils` Tool to read/write to/from JSON  format.



**Help**

Terminal Trainer's Editor main purpose is to create .challenge files with the **name**, **description**, **operative system** and **goals** of a challenge.



On the **left** hand side, we introduce the **black quoted** fields mentioned above.

On the **Goals** table we can press the **+** button to add a default **Goal** that we can edit by clicking on it and writing on the **right** hand side of the application, or press the **-** button to remove a **Goal**.



The **Description** column describes what you have to accomplished with the executed command.

The **Shell** column will be the terminal you will be creating the challenge for. For example: `cmd`, `PowerShell`.

The **PWD** column will determine the path(location) where you execut the commands.

The **User** tells you which user you have to be logged as to do the challenge. For example, if you  have a user with more permissions.



In the <span style='color:turquoise'>blue menu</span>, in the file submenu on top of the app, we can choose to **open** an already existing .challenge file, **save** a .challenge file or create a **blank** new .challenge file to edit.

In the <span style='color:turquoise'>blue menu</span>, in the **edit** submenu on top of the app, we can change the theme of our app.

