# MooRefactored
This application is the result of refactoring another application (called MooOriginal) and cleaning that code according 
to the Java's Code-Conventions so that it becomes testable, and then writing a few JUnit and Mockito tests.

## Set-up required before running this application
One can connect to the database in the following manner: 

Go to `Project` --> `src` --> `database` --> `DatabasePlayerDao` 

In the DatabasePlayerDao class's constructor, you can write the username and password to complete the connection with 
your database. Make sure to add a user directly in the database before starting the application.

## Instructions to use this application

In this application, there is a possibility to choose between 2 guessing games-
 *  Bulls & Cows
 *  Guess The Word

One can even choose between 2 available User Interfaces-
 *  Graphic window or `WindowIO in this application`
 *  Console or `SystemIO in this application`

All these must be chosen in the `main` method present in the `Main` class, before starting the application.

When the application starts, the player is asked to enter username.
If the user does not exist in database, the following message appears on the screen
```bat
User not in database, please register with admin
```
After that the application ends automatically after 5 seconds.
However, if the user already exists in database, the guessing game starts.

### Instructions to play "*Bulls & Cows*"

If one chooses to play *Bulls & Cows*, a 4-digit number gets generated randomly.
In the following example, let's assume that the secret number is `1234`.
```bat
New game:

4567:       //guess1
,           //no digit matches with digits in secret number

2146:       //guess2
,CCC        //digits (1-2-4) match, but incorrect position

2164:       //guess3
B,CC        //digit (4) at correct position, 
              digits (1-2) at incorrect position

1264:       //guess4
BBB,        //digits (1-2-4) at correct position

1234:       //guess5
BBBB,       //guess matches secret number
```
where
* Bull `B` means that a digit has been placed correctly
* Cow `C` means that a digit is present in the secret number, but at another position.

NOTE: The randomly generated number has 4 _distinct_ digits.

As of now, there is no restriction on the number of guesses it takes to guess the secret (or randomly generated) number.
So the game continues till the player guesses correctly. Once the goal is reached, a list of top-ten players and their 
average appears on the screen, along with the number of guesses it took to correctly guess the secret number. 

The application then asks if the player wants to continue playing the guessing game. 
* `y` means a new game starts. 
* `n` means the applications ends.

### Instructions to play "*Guess The Word*"

If one chooses to play *Guess The Word*, a 5-letter word gets selected from a list of around 120 words already present 
in the application. In the following example, let's assume that the secret (or selected) word is `space`.
```bat
New game:

fruit:                         //guess1 
Correct Position: 0            //no match found
Incorrect Position: 0

crazy:                         //guess2 
Correct Position: 1            //letter a
Incorrect Position: 1          //letter c

slice:                         //guess3 
Correct Position: 3            //letters s,c,e 
Incorrect Position: 0 

space:                         //guess4 
Correct Position: 5            //guess matches secret word 
Incorrect Position: 0         
```
NOTE: The secret word has 5 distinct letters.

As of now, there is no restriction on the number of guesses it takes to guess the secret word.
So the game continues till the player guesses correctly. Once the goal is reached, a list of top-ten players and their
average appears on the screen, along with the number of guesses it took to correctly guess the secret word.

The application then asks if the player wants to continue playing the guessing game.
* `y` means a new game starts.
* `n` means the applications ends.