package model;

import java.util.List;
/**
 * interface to manage a list of objects
 * @author Win8
 *
 * @param <T>
 */
public interface IListManager<T> {
    // adds an element to a collection list
	public void add(T type);
	// remove element at given position from the collection
	public void deleteAt(int index);
	// remove all elements from a collection
	public void deleteAll();
	// A collection should not be able to be indexed out of bounds.
    /// This method can be used from different places to ensure 
    /// correct indexing.
	public boolean checkIndex(int index);
	// get one element object from the list and return that object
	public T getAt(int index);
	// to change an element in the list at the position specified
	public void changeAt(T type, int index);
	// prepare and return an array of strings
    /// containing information about objects in collection
	public String[] toStringArray();
	// prepare and return a collection of List<string> type
    /// containing information about objects in collection
	public List<String> toStringList();
	// xml serialisation of object to file
	public void serialiseXML(String fileName);
	// deserialis data from xml file
	public void deserialiseXML(String fileName);


}
