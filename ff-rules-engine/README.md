JBoss BPM Suite Fantasy Football Demo

The goal of this demo project is to highlight the latest features offered by JBoss BPM Suite 6 product using fantasy football demo where you will find rules, decision tables, events, and a process that is leveraged by a web application. The web application is a WAR built using the JBoss BPM Suite generated kjar as a dependency, providing an example project showing how developers can focus on the application code while the business analysts can focus on the rules, event, and processed in the JBoss BPM Suite product web user interface.

Note: In this demo, we will be using the JBoss BPM Suite product which is a downstream version of Drools project. You can download the software on Jboss.org open source website at https://www.jboss.org/products/bpmsuite.html   

Setup and Configuration

    1) Download the JBoss BPM Suite 6.0.1 from https://www.jboss.org/products/bpmsuite.html 

    2) Navigate to the folder where you downloaded the installer file in a command prompt. You can install the JBoss BPM Suite in one of the following two ways:

    	Option A)  You can use our built in installation script and launch the automated installation process by providing the passwords for the Administration and the Application user using the following command. The default username for both type of users is 'admin'.

    	Before running the following command, open the installationScript.xml file and change the line # 5 <installpath>.....</installpath>and provide the path where you want to install the BPM Suite server.
    		$ java -jar jboss-bpms-installer-6.0.1.GA-redhat-4.jar installationScript.xml

    		(Or)

    	Option B) Execute the following command (replace the VERSION number and x with the actual file name). 
    		$ java -jar jboss-bpms-installer-VERSION.GA-redhat-x.jar (For example: java -jar jboss-bpms-installer-6.0.1.GA-redhat-4.jar)
 
 	3) If you have chosen Option A and used our built-in installation script, navigate to the folder as you specified on installationScript.xml file and cd into jboss-eap-6.1/ folder. If you chosen Option B, then navigate to the folder you specified during the installation and then cd into jboss-eap-6.1/.


 	4) Change locations of git and maven repositories (otherwise, they will be relative to where jboss is started from) by adding the following parameters to standalone.conf:
			-Dorg.uberfire.nio.git.dir=$JBOSS_HOME 
			-Dorg.uberfire.metadata.index.dir=$JBOSS_HOME 
			-Dorg.guvnor.m2repo.dir=$JBOSS_HOME/m2repo


 	5) Start the BPM Suite server by issuing bin/standalone.sh command. If you are on windows, you would run bin/standalone.bat instead.

    4) Open a web browser and login to Business Central by navigating to http://localhost:8080/business-central/. Login using the username/password combination for accessing the BPM Suite application that you created at installation time. 

    You have successfully installed and run BPM Suiteinspect project in BPM Suite, login http://localhost:8080/business central

    Fantasy Football and demo away (http://localhost:8080/fantasy-football)

