package model;

import org.joda.time.DateTime;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
//class to convert jodatime into more readable format
public final class JodaTimeConverter implements Converter
{
    @Override
    public boolean canConvert( final Class type )
    {
        return DateTime.class.isAssignableFrom( type );
    }

    @Override
    public void marshal( Object source, HierarchicalStreamWriter writer, MarshallingContext context )
    {
        writer.setValue( InputUtility.dateTimeToString((DateTime) source) );
       // writer.setValue(source.toString());
    }

    @Override
    public Object unmarshal( HierarchicalStreamReader reader,
                             UnmarshallingContext context )
    {
        //return new DateTime( reader.getValue() );
        return InputUtility.stringToDateTime(reader.getValue());
    }
}