# HaliteBot
![Screenshot](Assets/Screenshot.jpg)
This is the unofficial Discord bot for the official Halite server for Halite III! 

## Features 
It has several useful commands for feeding your Halite addiction: 
* !who <user_id> This command returns information about the Halite account associated with <user_id>. For example, rank, level, language, games played, country, etc. 
  * Example: !who 216 

*!rank <[OFR]user_id> This command returns rank information for the associated <[OFR]user_id> account, like rank, mu, sigma, tier, etc. 
  * Example: !rank 206 
* !register <user_id> Tired of typing your user ID all the time? You can register to use OFR functions. OFR means Optional For Registered people. Halite Bot will store your user id in a JSON database so you can check your stats without typing your user id all the time. 
  * Example: !register 326 
* !me This command gives you your stats, if you're registered. Less typing! But only if you're registered. 
* !top <list_size> This handy command tells you who is in the top right now. <list_size> is how many players that you want to list. 
  * Example: !top 5 
## Contributing 
If you, for some reason want to contribute to the mess of a codebase that this is, you'll need to clone this repository, and create a Key.java file in the constants package, located in src/main/constants/ directory. When you are in it, paste this in:
```java
package constants; 
public class Key { 
  public static final String TOKEN = "TOKEN"; 
}
```
and replace TOKEN with whatever your token is. To get your token, go to [Discord's Developer Documentation ](https://discordapp.com/developers/applications/) and create a application, then a bot, then get your token. 
To run the bot, either open this project in your IDE of choice, let it download the dependencies and then hit the run button. <br> You can also use the command line: <br>

* Navigate to the directory that contains the pom.xml file. 
* Install Maven: `sudo apt-get install maven`
* To find the directory Maven is installed in, run `mvn --version` 
* This should be outputted: 
```
Apache Maven 3.5.0 
Maven home: /usr/share/maven 
Java version: 1.8.0_141, 
vendor: Oracle Corporation 
Java home: /usr/local/jdk1.8.0_141/jre Default locale: en_CA, platform encoding: UTF-8 OS name: "linux", version: "4.13.0-45-generic", arch: "amd64", family: "unix" 
```
* You are looking for `Maven home:` What comes after it should replace PATH/TO/MAVEN for the next step 
* Run `mvn exec:java -Dexec.mainClass="core.App" -s "PATH/TO/MAVEN/conf/settings.xml"` 
* You are now running the bot! After you make some cool changes, commit it to your repository and hit me with a pull request!
