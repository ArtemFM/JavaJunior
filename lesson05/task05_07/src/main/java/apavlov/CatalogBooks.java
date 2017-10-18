package apavlov;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.Comparator;

/**
 * The class CatalogBooks - use for parse xml file to collection.
 *
 * @author Pavlov Artem
 * @since 18.10.2017
 */
public class CatalogBooks {
    /**
     * The constant local name in to xml.
     */
    private static final String ORDER = "Orders";

    /**
     * The constant local name in to xml.
     */
    private static final String ADD_BOOK = "AddOrder";

    /**
     * The constant local name in to xml.
     */
    private static final String DELETE_BOOK = "DeleteOrder";

    /**
     * The constant attribute to file xml.
     */
    private static final String NAME_BOOK = "book";

    /**
     * The constant attribute to file xml.
     */
    private static final String OPERATION = "operation";

    /**
     * The constant attribute to file xml.
     */
    private static final String PRICE = "price";

    /**
     * The constant attribute to file xml.
     */
    private static final String VOLUME = "volume";

    /**
     * The constant attribute to file xml.
     */
    private static final String ID = "orderId";

    /**
     * The constant left space text.
     */
    private static final int LEFT_SPACE = 8;

    /**
     * The constant left space text.
     */
    private static final int RIGHT_SPACE = 5;

    /**
     * The var for concat string.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * Path to file.
     */
    private String fileName;

    /**
     * Object XMLInputFactory.
     */
    private XMLInputFactory factoryXML;

    /**
     * Tree, where key is name book. Value is object Order.
     */
    private TreeMap<String, Order> treeName;


    /**
     * Constructor for class CatalogBooks.
     *
     * @param path - path to file;
     */
    public CatalogBooks(String path) {
        addNewFileXML(path);
        this.factoryXML = XMLInputFactory.newInstance();
        treeName = new TreeMap<>();
    }

    /**
     * Add new path to file, if previous path to file not equal this path to file.
     * if file is not found, then throw exception FileNotFoundException.
     *
     * @param path - path to file;
     */
    public void addNewFileXML(String path) {
        if (path == null || !new File(path).exists()) {
            try {
                throw new FileNotFoundException(String.format("File [path = %s] is not found...", path));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            if (!path.equals(this.fileName)) {
                this.fileName = path;
                treeName = new TreeMap<>();
            }
        }
    }

    /**
     * Start parse file xml.
     */
    public void startProcess() {
        try {
            parseData(openXML());
            clearExcessPrice();
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    /**
     * The method rewrite values collection HashMap to collection TreeMap.
     *
     * @param map - collection HashMap;
     */
    private void parseData(HashMap<String, HashMap<Integer, Book>> map) {
        if (map != null) {
            for (Map.Entry<String, HashMap<Integer, Book>> name : map.entrySet()) {
                Order order = this.treeName.get(name.getKey());
                if (order == null) {
                    order = new Order();
                    this.treeName.put(name.getKey(), order);
                }
                for (Book book : name.getValue().values()) {
                    order.addBook(book);
                }
            }
        }
    }

    /**
     * Open file xml for read.
     *
     * @throws IOException        - error open file xml;
     * @throws XMLStreamException - error parse xml file;
     * @return collection HashMap;
     */
    private HashMap<String, HashMap<Integer, Book>> openXML() throws IOException, XMLStreamException {
        HashMap<String, HashMap<Integer, Book>> map = null;
        if (this.fileName != null) {
            XMLStreamReader reader = null;
            try (FileInputStream input = new FileInputStream(this.fileName)) {
                reader = factoryXML.createXMLStreamReader(input);
                map = readXML(reader);
            } finally {
                if (reader != null) {
                    reader.close();
                }
            }
        }
        return map;
    }

    /**
     * Read local name to file xml.
     *
     * @param reader link xml reader;
     * @return collection HashMap;
     * @throws XMLStreamException error parse xml file;
     */
    private HashMap<String, HashMap<Integer, Book>> readXML(XMLStreamReader reader) throws XMLStreamException {
        HashMap<String, HashMap<Integer, Book>> map = null;
        while (reader.hasNext()) {
            int event = reader.next();
            if (XMLStreamReader.START_ELEMENT == event) {
                if (reader.getLocalName().equals(ORDER)) {
                    map = readBook(reader);
                }
            } else if (XMLStreamReader.END_ELEMENT == event) {
                break;
            }
        }
        return map;
    }

    /**
     * Read local name AddOrder or DeleteOrder to file xml.
     *
     * @param reader link xml reader;
     * @return collection HashMap;
     * @throws XMLStreamException error parse xml file;
     */
    private HashMap<String, HashMap<Integer, Book>> readBook(XMLStreamReader reader) throws XMLStreamException {
        HashMap<String, HashMap<Integer, Book>> map = new HashMap<>();
        while (reader.hasNext()) {
            int event = reader.next();
            if (XMLStreamReader.START_ELEMENT == event) {
                if (reader.getLocalName().equals(ADD_BOOK)) {
                    addBook(reader, map);
                } else if (reader.getLocalName().equals(DELETE_BOOK)) {
                    deleteBook(reader, map);
                }
            }
        }
        return map;
    }

    /**
     * Read line (attributes book) to file xml and add to collection.
     *
     * @param reader link xml reader;
     * @param map - collection HashMap;
     */
    private void addBook(XMLStreamReader reader, HashMap<String, HashMap<Integer, Book>> map) {
        Integer orderId = null;
        Book book = new Book();
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            if (reader.getAttributeLocalName(i).equals(NAME_BOOK)) {
                book.name = reader.getAttributeValue(i);
            }
            if (reader.getAttributeLocalName(i).equals(OPERATION)) {
                book.operation = reader.getAttributeValue(i);
            }
            if (reader.getAttributeLocalName(i).equals(ID)) {
                orderId = readInteger(reader.getAttributeValue(i));
            }
            if (reader.getAttributeLocalName(i).equals(VOLUME)) {
                book.volume = readInteger(reader.getAttributeValue(i));
            }
            if (reader.getAttributeLocalName(i).equals(PRICE)) {
                book.price = readFloat(reader.getAttributeValue(i));
            }
        }
        if (!book.typesIsNull() && orderId != null) {
            HashMap<Integer, Book> temp = map.get(book.name);
            if (temp == null) {
                temp = new HashMap<>();
                temp.put(orderId, book);
                map.put(book.name, temp);
            } else {
                temp.put(orderId, book);
            }
        }
    }

    /**
     * Read line (attributes book) to file xml and delete to collection.
     *
     * @param reader link xml reader;
     * @param map - collection HashMap;
     */
    private void deleteBook(XMLStreamReader reader, HashMap<String, HashMap<Integer, Book>> map) {
        String name = null;
        Integer idOrder = null;
        for (int i = 0; i < reader.getAttributeCount(); i++) {
            if (reader.getAttributeLocalName(i).equals(NAME_BOOK)) {
                name = reader.getAttributeValue(i);
            }
            if (reader.getAttributeLocalName(i).equals(ID)) {
                idOrder = readInteger(reader.getAttributeValue(i));
            }
        }
        if (name != null && idOrder != null) {
            HashMap<Integer, Book> temp = map.get(name);
            if (temp != null) {
                temp.remove(idOrder);
            }
        }
    }

    /**
     * Cast String to Integer.
     *
     * @param number String value;
     * @return Integer value
     */
    private Integer readInteger(String number) {
        return readFloat(number).intValue();
    }

    /**
     * Cast String to Float.
     *
     * @param number String value;
     * @return Float value
     */
    private Float readFloat(String number) {
        Float result;
        try {
            result = Float.parseFloat(number);
        } catch (NumberFormatException e) {
            result = null;
        }
        return result;
    }

    /**
     * The method delete excess price to collection.
     */
    private void clearExcessPrice() {
        for (Order order : this.treeName.values()) {
            Iterator<Float> itBuy = order.buyMap.keySet().iterator();
            Iterator<Float> itSell = order.sellMap.keySet().iterator();
            Float fBuy = 1f;
            Float fSell = 0f;
            while (itBuy.hasNext() && itSell.hasNext() && fBuy >= fSell) {
                fBuy = itBuy.next();
                fSell = itSell.next();
                itBuy.remove();
                itSell.remove();
            }
        }
    }

    @Override
    public String toString() {
        sb.delete(0, sb.length());
        String spaceBid = String.format("%s%s%s", "%", LEFT_SPACE + RIGHT_SPACE / 2, "s");
        String spaceSay = String.format("%s%s%s", "%", LEFT_SPACE + (RIGHT_SPACE * 2) - 1, "s");
        for (Map.Entry<String, Order> element : this.treeName.entrySet()) {
            sb.append(String.format("%14s", "Order book: ")).append(element.getKey());
            sb.append(System.lineSeparator()).append(System.lineSeparator());
            sb.append(String.format(spaceBid, "BID")).append(String.format(spaceSay, "ASK"));
            sb.append(System.lineSeparator()).append(System.lineSeparator());
            sb.append(element.getValue());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * The class Order - use for storage collections two operation (sell and buy).
     *
     * @author Pavlov Artem
     * @since 18.10.2017
     */
    private class Order {
        /**
         * Comparator for sort on decrease. Use lambda.
         */
        private Comparator<Float> comparator = (o1, o2) -> o1 != null && o2 != null ? -o1.compareTo(o2) : 0;

        /**
         * Object for concat string.
         */
        private StringBuilder sb = new StringBuilder();

        /**
         * Constant operation.
         */
        private static final String SELL = "SELL";

        /**
         * Constant operation.
         */
        private static final String BUY = "BUY";

        /**
         * Collection for operation sell.
         */
        private TreeMap<Float, Book> sellMap;

        /**
         * Collection for operation sell.
         */
        private TreeMap<Float, Book> buyMap;

        /**
         * The default constructor for class Order.
         */
        Order() {
            this.sellMap = new TreeMap<>();
            this.buyMap = new TreeMap<>(comparator);
        }

        /**
         * Add new book to one of collections.
         *
         * @param book - link object Book;
         */
        private void addBook(Book book) {
            if (book != null && !book.typesIsNull()) {
                if (book.operation.equals(BUY)) {
                    Book temp = buyMap.get(book.price);
                    if (temp == null) {
                        buyMap.put(book.price, book);
                    } else {
                        temp.volume += book.volume;
                    }
                }
                if (book.operation.equals(SELL)) {
                    Book temp = sellMap.get(book.price);
                    if (temp == null) {
                        sellMap.put(book.price, book);
                    } else {
                        temp.volume += book.volume;
                    }
                }
            }
        }

        @Override
        public String toString() {
            String space = String.format("%s", String.format("%s%s%s", "%", RIGHT_SPACE + 1 + LEFT_SPACE, "s"));
            sb.delete(0, sb.length());
            String emptyLine = "-------";
            String nameLine = new Book().toString();
            sb.append(nameLine).append(" - ").append(nameLine).append(System.lineSeparator()).append(System.lineSeparator());
            Iterator<Book> iteratorSell = sellMap.values().iterator();
            Iterator<Book> iteratorBuy = buyMap.values().iterator();
            while (iteratorBuy.hasNext() || iteratorSell.hasNext()) {
                sb.append(iteratorBuy.hasNext() ? iteratorBuy.next() : String.format(space, emptyLine)).append(" - ");
                sb.append(iteratorSell.hasNext() ? iteratorSell.next() : String.format(space, emptyLine));
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        }
    }

    /**
     * The class Book - use for storage attributes book.
     *
     * @author Pavlov Artem
     * @since 18.10.2017
     */
    private class Book {
        /**
         * Name book.
         */
        private String name;

        /**
         * Operation (Sell or Buy).
         */
        private String operation;

        /**
         * Price book.
         */
        private Float price;

        /**
         * Count books.
         */
        private Integer volume;

        /**
         * Check types class on the null.
         *
         * @return if all types is null return true or return false;
         */
        private boolean typesIsNull() {
            return name == null && operation == null && price == null && volume == null;
        }

        @Override
        public String toString() {
            String space = String.format("%s@%s", String.format("%s%s%s", "%", LEFT_SPACE, "s"), String.format("%s%s%s", "%-", RIGHT_SPACE, "s"));
            return typesIsNull() ? String.format(space, "Volume", "Price") : String.format(space, volume, price);
        }
    }
}

