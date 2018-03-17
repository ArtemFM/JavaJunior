package apavlov.xml;

import org.apache.logging.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import javax.xml.stream.XMLStreamException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

/**
 * The class XMLParse - methods write, read and parse XML.
 *
 * @author Pavlov Artem
 * @since 17.03.2018
 */
public class XMLParse {
    /**
     * The constant - name file style transform XML.
     */
    private static final String FILE_SETTINGS_TRANSFORM = "transform.xslt";

    /**
     * The constant - name root field to XML file.
     */
    private static final String ROOT_XML = "entries";

    /**
     * The constant - name child field to XML file.
     */
    private static final String CHILD_XML = "entry";

    /**
     * The constant - name content field to XML file.
     */
    private static final String CONTENT_XML = "field";

    /**
     * The list element for write to XML file.
     */
    private final LinkedList<Integer> listValues;

    /**
     * The link object Logger (logging system).
     */
    private final Logger log;

    /**
     * The constructor for class XMLParse.
     *
     * @param listValues - list values for writing to file XML.
     * @param log        - logging system;
     */
    public XMLParse(LinkedList<Integer> listValues, Logger log) {
        this.listValues = listValues;
        this.log = log;
    }

    /**
     * The main method start all process work to XML files.
     *
     * @param firstNameXML  - name first file XML;
     * @param secondNameXML - name second file XML;
     */
    public void start(String firstNameXML, String secondNameXML) {
        if (this.writeValuesToXML(firstNameXML)) {
            if (this.parseXMLToOtherFormatXML(firstNameXML, secondNameXML)) {
                this.parseXMLAndOutSumToConsole(secondNameXML);
            }
        }
    }

    /**
     * The method write list values to file XML.
     *
     * @param nameFile - name file for save;
     * @return true, if save is ready or false;
     */
    private boolean writeValuesToXML(String nameFile) {
        boolean result;
        try (FileOutputStream out = new FileOutputStream(nameFile)) {
            this.log.info(String.format("Start writing values to XML file [name = %s]...", nameFile));
            Document doc = new Document();
            doc.setRootElement(new Element(ROOT_XML));
            if (result = this.listValues != null) {
                Element child;
                while (!this.listValues.isEmpty()) {
                    int value = this.listValues.removeFirst();
                    child = new Element(CHILD_XML);
                    child.addContent(new Element(CONTENT_XML).setText(String.valueOf(value)));
                    doc.getRootElement().addContent(child);
                }
                XMLOutputter xmlWriter = new XMLOutputter(Format.getPrettyFormat());
                xmlWriter.output(doc, out);
                this.log.info(String.format("Finish writing values to XML file [name = %s]", nameFile));
            } else {
                this.log.fatal("The list values for write to XML is empty.");
            }
        } catch (IOException e) {
            this.log.fatal(String.format("Error of access to file [name = %s]", nameFile), e);
            result = false;
        }
        return result;
    }

    /**
     * The method transform first XML file to second XML file.
     *
     * @param inFile  - input file;
     * @param outFile - transform file;
     * @return true, if process is apply or false;
     */
    private boolean parseXMLToOtherFormatXML(String inFile, String outFile) {
        boolean result;
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(ClassLoader.getSystemResourceAsStream(FILE_SETTINGS_TRANSFORM));
        Transformer transformer = null;
        try {
            transformer = factory.newTransformer(xslt);
        } catch (TransformerConfigurationException e) {
            this.log.fatal(String.format("File settings transform [name = %s] is not found.", FILE_SETTINGS_TRANSFORM), e);
        }
        if (result = transformer != null) {
            this.log.info(String.format("Start transform XML:%s to XML:%s...", inFile, outFile));
            Source text = new StreamSource(new File(inFile));
            try {
                transformer.transform(text, new StreamResult(new File(outFile)));
                this.log.info(String.format("Finish transform XML:%s to XML:%s...", inFile, outFile));
            } catch (TransformerException e) {
                this.log.fatal(String.format("File XML [name = %s] is not found.", inFile), e);
                result = false;
            }
        }
        return result;
    }

    /**
     * The method counting sum all field to file XML.
     *
     * @param fileName - name file XML;
     */
    private void parseXMLAndOutSumToConsole(String fileName) {
        try (StaxStreamReader stax = new StaxStreamReader(new FileInputStream(fileName))) {
            this.log.info(String.format("Start solution sum values from XML [name = %s]...", fileName));
            int sumValues = 0;
            while (stax.startElement(CHILD_XML, ROOT_XML)) {
                sumValues += stringToInt(stax.getAttribute(CONTENT_XML));
            }
            this.log.info(String.format("Finish solution sum values from XML [name = %s]...", fileName));
            System.out.printf("Sum all values equals %d;", sumValues);
        } catch (FileNotFoundException | XMLStreamException e) {
            this.log.fatal(String.format("File XML [name = %s] is not found or busy.", fileName), e);
        }
    }

    /**
     * The method parse number type string to number type int.
     *
     * @param number - number type string;
     * @return number type int;
     */
    private int stringToInt(String number) {
        int result = 0;
        try {
            result = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            this.log.error(String.format("Error parse string to int [text = %s]", number), e);
        }
        return result;
    }
}

