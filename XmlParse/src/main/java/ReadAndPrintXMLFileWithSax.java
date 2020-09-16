import java.io.File;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ReadAndPrintXMLFileWithSax {

    public static void main(String[] args) throws Exception {
        SAXParserFactory fabrique = SAXParserFactory.newInstance();
        SAXParser parser = fabrique.newSAXParser();
        ClassLoader classLoader = ReadAndPrintXMLFileWithSax.class.getClassLoader();
        File file = new File(classLoader.getResource("book.xml").getFile());

        BookHandler handler = new BookHandler();
        parser.parse(file, handler);
    }

    public static class BookHandler extends DefaultHandler {
        private int count = 0;
        private StringBuilder buffer;

        @Override
        public void startElement(String uri, String localName, String qName,
                                 Attributes attributes) throws SAXException {
            switch (qName) {
                case "person":
                    count++;
                    break;
                case "PARAM_SEQ":
                    buffer = new StringBuilder("PARAM_SEQ : ");
                    break;
                case "PARAM_VALUE":
                    buffer = new StringBuilder("PARAM_VALUE : ");
                    break;
                case "age":
                    buffer = new StringBuilder("Age : ");
                    break;
            }
        }

        @Override
        public void characters(char[] ch, int start, int length)
                throws SAXException {
            String content = new String(ch, start, length);
            System.out.println("i am called: " + content);
            if (buffer != null){
                buffer.append(content);
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName)
                throws SAXException {
            switch (qName) {
                case "PARAM_VALUE":
                case "PARAM_SEQ":
                case "age":
                    System.out.println(buffer.toString());
                    break;
            }
        }

        @Override
        public void endDocument() throws SAXException {
            System.out.println(count + " persons");
        }
    }
}