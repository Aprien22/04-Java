import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();
        String path = "words.txt";
        ArrayList<String> wordsToGuess = new ArrayList<>();

        try(BufferedReader buffer = new BufferedReader(new FileReader(path))){
            String line; 
            while((line = buffer.readLine()) != null)
            {
                wordsToGuess.add(line);   
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found!");
        }
        catch(IOException e)
        {
            System.out.println("Something went wrong!");
        }

        String word = wordsToGuess.get(rand.nextInt(wordsToGuess.size()));

        StringBuilder blankWord = new StringBuilder("_ ".repeat(word.length()).trim());
        boolean isGuessed = false;

        int attemptsLeft = 6;

        System.out.println("Welcome to Hangman!");

        while (attemptsLeft > 0 && !isGuessed) {
            System.out.println("Word to guess: " + blankWord);
            System.out.println("Attempts left: " + attemptsLeft);
            System.out.print("Enter your guess (single letter): ");
            String guess = scanner.nextLine().toLowerCase();

            if (guess.length() != 1 || !Character.isLetter(guess.charAt(0))) {
                System.out.println("Please enter a valid single letter.");
                continue;
            }

            char guessedChar = guess.charAt(0);
            boolean correctGuess = false;
            StringBuilder newBlankWord = new StringBuilder(blankWord);

            // Check if the guessed character is in the word
            for (int i = 0; i < word.length(); i++) {
                if (word.charAt(i) == guessedChar) {
                    newBlankWord.setCharAt(i * 2, guessedChar);
                    correctGuess = true;
                }
            }

            // Update the blank word and attempts
            if (correctGuess) {
                blankWord = newBlankWord;
                if (!blankWord.toString().contains("_")) {
                    isGuessed = true;
                }
            } else {
                attemptsLeft--;
                System.out.println("Wrong guess!");
            }
        }

        if (isGuessed) {
            System.out.println("Congratulations! You've guessed the word: " + word);
        } else {
            System.out.println("Game over! The word was: " + word);
        }



        scanner.close();
    }
}
