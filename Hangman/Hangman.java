import java.util.Random;
import java.util.Scanner;

public class Hangman {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random rand = new Random();

        String[] words = {"apple", "banana", "cherry", "date", "elderberry"};
        String wordToGuess = words[rand.nextInt(words.length)];
        StringBuilder blankWord = new StringBuilder("_ ".repeat(wordToGuess.length()).trim());
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
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guessedChar) {
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
            System.out.println("Congratulations! You've guessed the word: " + wordToGuess);
        } else {
            System.out.println("Game over! The word was: " + wordToGuess);
        }



        scanner.close();
    }
}
