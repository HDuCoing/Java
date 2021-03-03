// Tutorial 1, 159272. Task 3.
public class Village {
	
	public static void main(String[] args) {
// Create several Person objects to create yourself a little Village. Store these so you can print them later.
// You can either hardcode these in, or better yet, ask the user for information about them! If you do this, you might want to define a helper function.
//surname,age,height,weight,shoesize
		Person person1 = new Person("Smith", 50, 134, 200, 9); //person 1
		Person person2 = new Person("McClaud", 25, 132, 180, 11); //person 2
		Person person3 = new Person("Armstrong", 42, 137, 205, 10); //person 3
		Person person4 = new Person("Andrews", 70, 130, 160, 8); //person 4
		System.out.println(person1);
// One person just got married. They changed their surname!
		person1.changeSurname("Jolie");
// Print out each Person in the Village (make sure you have implemented the Person.toString() method first)
		System.out.println(person1);
		System.out.println(person2);
		System.out.println(person3);
		System.out.println(person4);



	}

}
class Person {
// Include at least four other 'traits' that a Person might have that usually differ from other People.
	private String surname;
	int age;
	int height;
	int weight;
	int shoeSize;
// Update this constructor to include the additional traits you made.
	public Person (String surname, int age, int height, int weight, int shoeSize) {
//Gives the person a name
		this.surname = surname;
//Gives them their age
		this.age = age;
//Gives them a height
		this.height = height;
//Gives them a weight
		this.weight = weight;
//Gives them a shoe size
		this.shoeSize = shoeSize;

	}
// Implement
	public void changeSurname(String newSurname) {
		System.out.println("A marriage occured!");
		this.surname = newSurname;
}


	
// Create a sensible toString implementation so each Person object can be printed out.
	public String toString() {
		return("This is " + this.surname + " and they're " + this.age + " years old." + " Their height is " + this.height + "cm, and their weight is " + this.weight + " pounds. Their shoe size is " + this.shoeSize + ".");
	}

}
