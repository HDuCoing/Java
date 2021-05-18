package tutorial5.src.nz.ac.massey.cs.pp.tutorial5;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * This is the class template you must implement. This means to replace all TODOs 
 * by meaningful code until all tests in SimpleListTests succeed. 
 * 
 * This is an example of test driven development (test first, then write your code to pass the test ). 
 * The specifications are defined by the test cases.
 * 
 * RULES:
 * 1. You are NOT allowed to change the definition of the single instance variable
 * 2. You are NOT allowed to add instance variables
 */


public class SimpleList<E> {

	// Generic types cannot be arrays as they are only resolved at runtime
	// While this is an Object array, we maintain type safety by only accessing it with objects of type <E>
	private Object[] array;
	private int size = 0; // Tracks how many elements are stored

	public SimpleList() {
		this( 10 );
	}

	public SimpleList(int num) {
		array = new Object[num];
	}

	public void add(E element) {
		//TODO
		this.add( element );
	}

	public void remove(int index) {
		//TODO
		this.remove( index );
	}

	public E get(int index) {
		//TODO
		return this.get( index );
	}

	public int size() {
		//TODO
		return this.size();
	}
}

