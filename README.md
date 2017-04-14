# stringFun
String manipulation


When I first looked at this problem i was thinking i would sort the tokens without the prefix 
of - or -- but then when I looked at the sample output of the challenge I realized that to do this
correctly would require a parent child relationship which ParsedElement provides.

To run both solutions use PrintDriver.  StringFun is included just to show thought progressions and my 
line of reasoning about the problem.  I do have a simple unit test for 
ParsedElement that confirms it was parsed correctly.


I have included the gradle wrapper so to build it

./gradlew clean build
or 
gradle clean build (Windows)


To run this after you have built it locally

java -cp ./stringFun.jar PrintDriver

