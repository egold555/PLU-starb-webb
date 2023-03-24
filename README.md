# Star Battle

Project includes both client and server code.

## Client

### Before Running

You'll need to sign in or authenticate yourself as a user. As of March 24, 2022, we have a mocked authentication system
that uses program arguments to achieve login. 

Steps to log in:

> Make sure IntelliJ is running and on starb-webb repo before preceding on the next steps

1) Click over to the run configuration for StarbClient (next to the green run button)
<img src="doc/tutorial/authentication/1_authstep.png" width="400px">
2) Click "edit configurations"
<br><img src="doc/tutorial/authentication/2_authstep.png" width="400px">
3) In the StarbClient run configuration settings, click modify options and select "Add VM options"
<img src="doc/tutorial/authentication/3_authstep.png" width="500px">
<img src="doc/tutorial/authentication/4_authstep.png" width="500px">
4) In the VM options text field, add `-Dusername=test.` The argument username is required field followed by any username that is not currently in `src/main/resources/webb/data/userdata.json`
<img src="doc/tutorial/authentication/5_authstep.png" width="500px">

### Java Client

To start the Java client: `./gradlew run`

Code located in: `src/main/java/starb/client`

### HTML/CSS/Javascript client.  

Start server (see below) then open browser and go to:

http://localhost:1390/client/index.html

HTML/JS/CSS files located under: `static/client`

## Server

To start server:  `./gradlew bootRun`

## Tests

Tests are located in `src/test/java`.  To execute tests using IntelliJ,
use the green arrow.  To execute using Gradle run:

`./gradlew test`