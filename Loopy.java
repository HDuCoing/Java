import java.util.*;
// Tutorial 1, 159272. Task 2.
public class Loopy {

	public static void main(String[] args) {
// TODO: Use a loop to print every upper case letter
//Prints out the alphabet in all caps
		for (char c = 'a'; c <= 'z'; c++) {
			System.out.println(c);
		}
// TODO: Get input from user. Print the same input back but with cases swapped. Use the helper functions below.
//Asks for input from the user then defines it
		Scanner input = new Scanner(System.in);
		System.out.println("Enter a string:");
		String userInput = input.nextLine();
//Calls the method that converts the string
		toggleCase(userInput);


	}


// TODO: Implement this function to return the opposite case of the letter given. DO NOT USE any built in functions.
// THINK! How will you handle the case where the char given is not a letter? (any sensible treatment of this edge case is okay - but you have to figure out which way you will choose)
//Method to convert strings to upper/lower cases
	public static String toggleCase(String text) {
		int length = text.length();
		for (int b = 0; b < length; b++) {
			char let = text.charAt(b);
//Takes a lowercase and turns it into an uppercase
			if (Character.isLowerCase(let)) {
				char upperInput = Character.toUpperCase(let);
				System.out.print(upperInput);
//Takes an uppercase and turns it into a lowercase
			} else if (Character.isUpperCase(let)) {
				char lowerInput = Character.toLowerCase(let);
				System.out.print(lowerInput);
			}

		}
	return text;
	}
// TODO: Implement this function to toggle the case each char in a string. Use toggleCase() to help you.
		private static String toggleStringCase(char charr) {
			char ch = charr;
//char to string conversion
			String str = String.valueOf(ch);
//converting the string
			return toggleCase(str);
		}


}
