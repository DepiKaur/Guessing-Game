# MooRefactored
This application is the result of refactoring another application (called MooOriginal) and cleaning that code according 
to the Java's Code-Conventions so that it becomes testable, and then writing a few JUnit and Mockito tests.

## Instructions to use this application

In this application, there is a possibility to choose between 2 guessing games-
 *  Bulls & Cows
 *  Guess The Word

One can even choose between 2 available User Interfaces-
 *  Graphic window
 *  Console

All these must be chosen in the `main` method present in the `Main` class, before starting the application.

When the application starts, the player is asked to enter username.
```bat
Enter your user name:
```

If the user does not exist in database, the following message appears on the screen
```bat
User not in database, please register with admin
```
After that the application ends automatically after 5 seconds.
However, if the user already exists in database, the guessing game starts.

### Instructions to play "*Bulls & Cows*"

If one chooses to play *Bulls & Cows*, a 4-digit random number gets generated.
```bat
New game:
```
NOTE: The randomly generated number has 4 distinct digits.

As of now, there is no restriction on the number of guesses it takes to guess the secret (or randomly generated) number.
So the game continues till the player guesses correctly. Once the goal is reached, a list of top-ten players and their 
average appears on the screen, along with the number of guesses it took to correctly guess the secret number. 

The application then asks if the player wants to continue playing the guessing game. 
* `y` means a new game starts. 
* `n` means the applications ends.

### Instructions to play "*Guess The Word*"

If one chooses to play *Guess The Word*, a 5-letter word gets selected from a list of words already present in the 
application.
```bat
New game:
```
NOTE: The secret (or selected) word has 5 distinct letters.

As of now, there is no restriction on the number of guesses it takes to guess the secret word.
So the game continues till the player guesses correctly. Once the goal is reached, a list of top-ten players and their
average appears on the screen, along with the number of guesses it took to correctly guess the secret word.

The application then asks if the player wants to continue playing the guessing game.
* `y` means a new game starts.
* `n` means the applications ends.