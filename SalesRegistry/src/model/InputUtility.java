package model;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import com.thoughtworks.xstream.XStream;

/**
 * custom class with helper methods
 * @author Win8
 *
 */
public class InputUtility {
	/**
	 * check if string is null or empty
	 * @param stringToCheck
	 * @return true if valid string, false otherwise
	 */
    public static boolean isInValidString(String stringToCheck)
    {
        String trimStr = stringToCheck.trim();
        return isNullOrEmpty(trimStr);
    }
    /**
     * 
     * @param value
     * @return
     */
    public static boolean isNullOrEmpty(String value)
    {
      if (value != null)
        return value.length() == 0;
      else
        return true;
    }
    /**
     * remove underscores
     * @param str string value
     * @return string without underscores
     */
    public static String removeUnderscores(String str)
    {
    	return str.replace("_", " ");
    }
    /**
     * remove underscore from string array
     * @param array
     * @return string array without underscores
     */
    public static String[] removeUnderscoresArray(String[] array)
    {
    	String[] fixed = array;
    	for(int i=0; i<fixed.length;i++)
    		fixed[i]=removeUnderscores(fixed[i]);
    	return fixed;
    }
    /**
     * regex pattern for valid email address
     */
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
    	    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    /**
     * check if provided string is a valid email address
     * @param emailStr
     * @return true if valid email
     */
    public static boolean isValidEmail(String emailStr) {
    	    Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
    	    return matcher.find();
    }
    
    //set expiry date one year from the date provided
    public static Date setExpiryDate(Date date)
	{
		//get selected payment date
    	Date selectedDate = date;
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(selectedDate);
    	//add one year to that date
    	cal.add(Calendar.YEAR, 1);
    	
    	return cal.getTime();
	}
    
    //convert given date object into string in format "10 May 2015"
    public static String dateTimeToString(DateTime date)
    {
    	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMMM yyyy");
    	return fmt.print(date);
    }
    //convert string to joda datetime
    public static DateTime stringToDateTime(String string)
    {
    	DateTimeFormatter fmt = DateTimeFormat.forPattern("dd MMMM yyyy");
    	return fmt.parseDateTime(string);
    }
    //convert given date object into string in format "10 May 2015"
    public static String dateToString(Date date)
    {
    	SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy");
    	return dateFormat.format(date);
    }
    /**
     * serialise object to xml file
     * @param file file name
     * @param obj object
     */
    public static <T> void serialiseToXML(String file, T obj)
    {
    	try{
    		XMLEncoder encoder =
    		           new XMLEncoder(
    		              new BufferedOutputStream(
    		                new FileOutputStream(file)));
    		        encoder.writeObject(obj);
    		        encoder.close();
    	}
    	catch (FileNotFoundException e){
    		
    	}
    }
    /**
     * save object to xml file using XStream
     * @param file file name
     * @param obj object
     */
    public static <T> void saveXml(String file, T obj)
    {
    	XStream xstream = new XStream();
    	xstream.registerConverter(new JodaTimeConverter());
		
        OutputStream outputStream = null;
        Writer writer = null;
        
        try {
            outputStream = new FileOutputStream(file);
            writer = new OutputStreamWriter(outputStream, Charset.forName("UTF-8"));
            xstream.toXML(obj, writer);
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("file not found "+e.toString());
		}
        finally
        {
        	if(writer!=null)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        	if(outputStream!=null)
				try {
					outputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        }
    }
    /**
     * save object to xml file using serializer
     * @param file file name
     * @param obj object
     */
    public static <T> void serialiseToXML2(String file, T obj)
    {
    	Serializer serializer = new Persister();
    	File result = new File(file);
    	try{
    		serializer.write(obj, result);  		        
    	}
    	catch (FileNotFoundException e){
    		
    	} catch (Exception e) {
			// TODO Auto-gener ated catch block
			e.printStackTrace();
		}
    	
    }
    /**
     * read object from xml file
     * @param file file name
     * @return object
     */
    public static <T> T deserialiseFromXML(String file)
    {
    	T obj = null;
    	
    	try {
    		XMLDecoder decoder =
    	            new XMLDecoder(new BufferedInputStream(
    	                new FileInputStream(file)));
    		
    	        obj = (T)decoder.readObject();
    	        decoder.close();
    	}
    	catch (Exception e) {
    		
    	}
		return (T)obj;
    	
    }
    /**
     * read object from xml file using XStream
     * @param file file name
     * @return object
     */
    public static <T> T readFromXML(String file)
    {
    	T obj = null;
    	XStream xstream = new XStream();
    	xstream.registerConverter(new JodaTimeConverter());
		
    	FileReader reader = null;
    	try {
    		reader = new FileReader(file);
    		
    		obj = (T) xstream.fromXML(reader);
    	}
    	catch (Exception e) {
    		
    	}
    	finally
    	{
    		if(reader!=null)
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	}
		return (T)obj;
    	
    }
    /**
     * read object from xml file using serializer
     * @param file name
     * @return object
     */
    public static <T> T deserialiseFromXML2(String file)
    {
    	T obj = null;
    	Serializer serializer = new Persister();
    	File source = new File(file);
    	try {
    		
    	        obj = (T)serializer.read(obj, source);
    	        
    	}
    	catch (Exception e) {
    		
    	}
		return (T)obj;
    	
    }
    /**
     * save object to xml file using outputStream, JAXNContext and marshaller
     * @param fileName
     * @param obj object
     */
    public static <T> void saveToFile(String fileName, T obj) 
    {
    	FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(fileName);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try
        {
           // JaxbList<T> jaxbList = new JaxbList<>(list);
            //File file = openFile(fileName);
            
            //String xsdFilename = fileName + ".xsd";
            JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
           // jaxbContext.generateSchema(new XSDSchemaGenerator(xsdFilename));
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, fileName);
            marshaller.marshal(obj, outputStream);
        }
        catch (JAXBException e)
        {
           System.out.println("Error saving file "+e.toString());        }
    }


}
