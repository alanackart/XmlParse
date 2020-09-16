import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;

public class ReadAndPrintXMLFileWithStAX {

    private static String getXmlString(){
        return "<Page>\n" +
                "    <PARAM_LIST>\n" +
                "        <PARAM>\n" +
                "            <PARAM_SEQ>1</PARAM_SEQ>\n" +
                "            <PARAM_VALUE>100</PARAM_VALUE>\n" +
                "        </PARAM>\n" +
                "        <PARAM>\n" +
                "            <PARAM_SEQ>2</PARAM_SEQ>\n" +
                "            <PARAM_VALUE>200</PARAM_VALUE>\n" +
                "        </PARAM>\n" +
                "    </PARAM_LIST>\n" +
                "    <CUS_NUMBER>2</CUS_NUMBER>\n" +
                "    <STRATEGY_ID>L01_01_01</STRATEGY_ID>\n" +
                "    <SEQ>1</SEQ>\n" +
                "</Page>";
    }
    public static void main(String argv[]) throws Exception {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
//        InputStream in = ReadAndPrintXMLFileWithStAX.class.getResourceAsStream("/book.xml");
        Reader reader = new StringReader(getXmlString());
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(reader);
//        streamReader.nextTag(); // Advance to "Page" element
        String paramSeq = null;
        String paramValue = null;
        while (streamReader.hasNext()) {
            if (streamReader.isStartElement()) {
                String name = streamReader.getLocalName();
//                System.out.println(name);
                switch (name) {
                    case "CUS_NUMBER": {
                        System.out.print("CUS_NUMBER : ");
                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "STRATEGY_ID": {
                        System.out.print("STRATEGY_ID : ");
                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "SEQ": {
                        System.out.print("SEQ : ");
                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "PARAM_LIST": {
//                        System.out.print("PARAM_LIST : ");
//                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "PARAM": {
//                        System.out.print("PARAM : ");
                        paramSeq = null;
                        paramValue = null;
//                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "PARAM_SEQ": {
//                        System.out.print("PARAM_SEQ : ");
                        paramSeq = streamReader.getElementText();
//                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    case "PARAM_VALUE": {
//                        System.out.print("PARAM_VALUE : ");
                        paramValue = streamReader.getElementText();
//                        System.out.println(streamReader.getElementText());
                        break;
                    }
                    default:
                        System.out.println(name + " unhandled");

                }
                if(paramSeq != null && paramValue != null){
                    System.out.println(paramSeq + ", " + paramValue);
                     paramSeq = null;
                     paramValue = null;
                }
            }
            streamReader.next();
        }
    }

}