package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.thoughtworks.xstream.annotations.XStreamAlias;
/**
 * class implementing methods to manage a list of objects
 * @author Win8
 *
 * @param <T>
 */
public class ListManager<T> extends Observable implements IListManager<T> {
	//list of objects
	private List<T> list;
	
	public ListManager() {
		//initialise the list
		list = new ArrayList<T>();
	}
	/**
	 * 
	 * @return list of objects
	 */
	public List<T> getList() {
		return list;
	}

	
	/**
	 * set list of objects
	 * @param list
	 */
	public void setList(List<T> list) {
		this.list.clear();
		this.list = list;
	}
	/**
	 * 
	 * @return size of list
	 */
	public int getCount() {
		return list.size();
	}
	/**
	 * add object to the list
	 */
	@Override
	public void add(T type) {
		if(type!= null)
			list.add(type);
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

		
	}
	/**
	 * delete object at selected position
	 */
	@Override
	public void deleteAt(int index) {
		//provided position is within bounds
		if(checkIndex(index))
			list.remove(index);
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}
	/**
	 * clear the list
	 */
	@Override
	public void deleteAll() {
		if(list.size() > 0)
			list.clear();
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}
	/**
	 * ensure index position is within bounds of the list
	 */
	@Override
	public boolean checkIndex(int index) {
		return ((index >= 0) && (index < list.size()));
	}
	/**
	 * get object at specified position
	 */
	@Override
	public T getAt(int index) {
		if(checkIndex(index))
			return list.get(index);
		return null;
	}
	/**
	 * modify object at specified position
	 */
	@Override
	public void changeAt(T type, int index) {
		if(checkIndex(index))
			list.set(index, type);
		// call setChanged before notifyObservers to indicate model has changed
	      setChanged();     
	   // notify Observers that model has changed 
	      notifyObservers();

	}
	/**
	 * convert list of objects to string array
	 */
	@Override
	public String[] toStringArray() {
		
		String[] strArray = new String[list.size()];
		int index = 0;
		for(T value:list)
		{
		strArray[index] = value.toString();
		index++;
		}
		return strArray;
	}
	/**
	 * convert list of objects to list of strings
	 */
	@Override
	public List<String> toStringList() {
		List<String> strings = new ArrayList<String>();

		for (T obj : list) {
		    strings.add(obj.toString());
		}
		
		return strings;
	}
	/**
	 * save list to xml file
	 */
	@Override
	public void serialiseXML(String fileName) {
		InputUtility.saveXml(fileName, list);
		
	}
	/**
	 * read list from xml file
	 */
	@Override
	public void deserialiseXML(String fileName) {
		list = InputUtility.readFromXML(fileName);
	}

}
