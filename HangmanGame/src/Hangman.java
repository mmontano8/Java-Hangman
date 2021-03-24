import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
//HANG MAN GAME 
//  0
// -|-
// / \
//
public class Hangman {

	public static void main(String[] args) throws FileNotFoundException {
	//throws FileNotFoundException is incase you can't find the file listed program wont work
		
	
		Scanner scanner = new Scanner(new File("C:\\Users\\Mikey\\Downloads\\words_alpha.txt"));
		Scanner keyboard = new Scanner(System.in);
		// initialize a scanner but instead of input, direct it to a file path
		List<String> words = new ArrayList<>();
		// declare an empty array list of strings called *words* to work with in the file
		while (scanner.hasNext()) {
			words.add(scanner.nextLine());
			//while loop is looping through every line in the list and adding it to the array list
		}
		//objective of the following is to pick a random word from out list
		Random rand = new Random();
		String word = words.get(rand.nextInt(words.size()));
		//nested method calls: we want to get a random word so we .get the .nextInt but we 
		//dont need to know actual list size we just used words.size method instead of an actual Int parameter
		System.out.println(word);		
		
		//keep a list of letters player has guessed, iterate through word to see if player guessed correctly
		
		List<Character> playerGuesses = new ArrayList<>();
		
		int wrongCount = 0;
		while(true) {
			
			printHangedMan(wrongCount);
			
			if (wrongCount >= 6) {
				System.out.println("you lost dweeb LOL");
				break;
			}
			
			printWordState(word, playerGuesses);
			if (!getPlayerGuess(keyboard, word, playerGuesses)) {
				wrongCount++;
			}
			
			if (printWordState(word, playerGuesses)) {
				System.out.println("YOU WON WOW GOOD JOB WOW!");
				break;
			}
			System.out.println("Pleae enter your guess for the word:");
			if (keyboard.nextLine().equalsIgnoreCase(word)) {
				System.out.println("yOU WoN WOw GoOD jOB WOW!");
				break;
			}
			else {
				System.out.println("thats not quite right loser LMAO");
				
			}
		}	
	}

	private static void printHangedMan(int wrongCount) {
		System.out.println("---------");
		System.out.println("|       |");
		if (wrongCount >= 1) {
			System.out.println(" 0 ");
		}
		if (wrongCount >= 2) {
			System.out.print("\\ ");
			if(wrongCount >= 3) {
				System.out.println("/");
			}
			else {
				System.out.println("");
			}
		}
		if (wrongCount >= 4) {
			System.out.println(" |");
		}
		if (wrongCount >= 5) {
			System.out.print("/ ");
			if(wrongCount >= 6) {
				System.out.println("\\");
			}
			else {
				System.out.println("");
			}
		}
		System.out.println("");
		System.out.println("");
	}

	private static boolean getPlayerGuess(Scanner keyboard, String word, List<Character> playerGuesses) {
		System.out.print("please enter one letter my dude: ");
		String letterGuess = keyboard.nextLine();
		playerGuesses.add(letterGuess.charAt(0));
		
		return word.contains(letterGuess);
		
		
	}

	private static boolean printWordState(String word, List<Character> playerGuesses) {
		int correctCount = 0;
		for (int i = 0; i < word.length(); i++) {
			if (playerGuesses.contains(word.charAt(i))) {
				System.out.print(word.charAt(i));
				correctCount++;
			}
			else {
				System.out.print("-");
			}	
		}
		System.out.println();
		
		return (word.length() == correctCount);
	}
	

}
