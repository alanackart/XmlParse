import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class ReadAndPrintXMLFileWithDom {

    private static String getXmlString() {
        return "<Page>\n" +
                "    <PARAM_LIST>\n" +
                "        <PARAM>\n" +
                "            <PARAM_SEQ>1</PARAM_SEQ>\n" +
                "            <PARAM_VALUE>豆腐干豆腐干地方</PARAM_VALUE>\n" +
                "        </PARAM>\n" +
                "        <PARAM>\n" +
//                "            <PARAM_SEQ>2</PARAM_SEQ>\n" +
//                "            <PARAM_VALUE>200</PARAM_VALUE>\n" +
                "        </PARAM>\n" +
                "    </PARAM_LIST>\n" +
                "    <CUS_NUMBER>2</CUS_NUMBER>\n" +
                "    <STRATEGY_ID>L01_01_01</STRATEGY_ID>\n" +
                "    <SEQ>1</SEQ>\n" +
                "</Page>";
    }

    private static String getElementText(Document document, String tagName){
        NodeList list = document.getDocumentElement().getElementsByTagName(tagName);
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                System.out.println(element.getTextContent());
            }
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new ByteArrayInputStream(getXmlString().getBytes("UTF-8")));


        getElementText(document,"CUS_NUMBER");
        getElementText(document,"STRATEGY_ID");

        NodeList list = document.getDocumentElement().getElementsByTagName("PARAM");
        for (int temp = 0; temp < list.getLength(); temp++) {
            Node node = list.item(temp);
            System.out.println("\nCurrent element: " + node.getNodeName());
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                NodeList s = element.getElementsByTagName("PARAM_SEQ");
                NodeList v = element.getElementsByTagName("PARAM_VALUE");
                if(s.getLength() > 0 && v.getLength() > 0){
                    System.out.println("Name: " + s.item(0).getTextContent());
                    System.out.println("Key Value: " + v.item(0).getTextContent());
                }
            }
        }

    }
}
