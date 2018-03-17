package apavlov.xml;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;

/**
 * The class StaxStreamReader - methods job class STAX.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
public class StaxStreamReader implements AutoCloseable {
    /**
     * The constant - factory XML.
     */
    private static final XMLInputFactory FACTORY = XMLInputFactory.newInstance();

    /**
     * The link to object XMLStreamReader.
     */
    private final XMLStreamReader reader;

    /**
     * The constructor for class StaxStreamReader.
     *
     * @param is - link object InputStream;
     * @throws XMLStreamException - exception XML;
     */
    StaxStreamReader(InputStream is) throws XMLStreamException {
        reader = FACTORY.createXMLStreamReader(is);
    }

    /**
     * The method start element.
     *
     * @param element - child;
     * @param parent - root;
     * @return true, if element is found or false;
     * @throws XMLStreamException - exception XML;
     */
    boolean startElement(String element, String parent) throws XMLStreamException {
        boolean result = false;
        while (reader.hasNext()) {
            int event = reader.next();
            if (parent != null && event == XMLEvent.END_ELEMENT && parent.equals(reader.getLocalName())) {
                result = false;
                break;
            }
            if (result = event == XMLEvent.START_ELEMENT && element.equals(reader.getLocalName())) {
                break;
            }
        }
        return result;
    }

    /**
     * The method for get attribute to XML file.
     *
     * @param name - name attribute;
     * @return attribute type string;
     * @throws XMLStreamException - exception XML;
     */
    String getAttribute(String name) throws XMLStreamException {
        return reader.getAttributeValue(null, name);
    }

    @Override
    public void close() {
        if (this.reader != null) {
            try {
                this.reader.close();
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        }
    }
}
