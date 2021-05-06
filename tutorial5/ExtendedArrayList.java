package tutorial5.src.nz.ac.massey.cs.pp.tutorial5;

import java.util.ArrayList;
import java.util.Collection;

public class ExtendedArrayList<E> extends ArrayList<E> {

	private static final long serialVersionUID = 1491116894329011199L;
	
	public ExtendedArrayList() {
		super();
	}
	
	public ExtendedArrayList(int num) {
		super(num);
	}
	
	public ExtendedArrayList(Collection<? extends E> collection) {
		super(collection);
	}
	
	
	/**
	 * Implementations of the following methods to extend ArrayList
	 * back() returns the final element of the list
	 * popBack() returns and removes the final element of the list
	 * remove() removes the final element of the list (note there is no Object or index as a parameter - ArrayLists don't have this ability!)
	 */
	public E back() {
		if (super.size() == 0) return null;
		
		return super.get(super.size()-1);
	}
	
	public E popBack() {
		if (super.size() == 0) return null;
		
		return super.remove(super.size()-1);
	}
	
	public boolean remove() {
		if (super.size() == 0) return false;
		
		super.remove(super.size()-1);
		return true;
	}
	
}
