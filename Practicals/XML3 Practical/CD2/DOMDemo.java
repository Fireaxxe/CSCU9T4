import java.io.*;               // import input-output

import javax.xml.parsers.*;         // import parsers
import javax.xml.xpath.*;           // import XPath

import org.w3c.dom.*;               // import DOM

/**
DOM handler to read XML information, to create this, and to print it.

@author   CSCU9T4, University of Stirling
@version  25/03/15
 */
public class DOMDemo {

    /** Document builder */
    private static DocumentBuilder builder = null;

    /** XML document */
    private static Document document = null;

    /** XPath expression */
    private static XPath path = null;

    /*----------------------------- General Methods ----------------------------*/

    /**
    Main program to call DOM parser.

    @param args         command-line arguments
     */
    public static void main(String[] args)  {
        // load XML file into "document"
        loadDocument(args[0]);

        // print staff.xml using DOM methods and XPath queries
        printNodes();

    }
    /**
    Set global document by reading the given file.

    @param filename     XML file to read
     */
    private static void loadDocument(String filename) {
        try {
            // create a document builder
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();

            // create an XPath expression
            XPathFactory xpathFactory = XPathFactory.newInstance();
            path = xpathFactory.newXPath();

            // parse the document for later searching
            document = builder.parse(new File(filename));
        }
        catch (Exception exception) {
            System.err.println("could not load document " + exception);
        }
    }

    /*-------------------------- DOM and XPath Methods -------------------------*/

    /**
    Print nodes using DOM methods and XPath queries.
     */
    private static void printNodes() {
        Node staff = document.getFirstChild();
        Node staffMember = staff.getFirstChild().getNextSibling();
        System.out.println("First child is: " + staff.getNodeName());
        System.out.println("  Child is: " + staffMember.getNodeName());
        Node name = staffMember.getFirstChild().getNextSibling();
        System.out.println("  Name is:  " + name.getFirstChild().getTextContent());

        System.out.println("Staff count is: " + query("count(/staff/staffMember)"));
        System.out.println("Next name is: " + query("/staff/staffMember[3]/name"));
    }

    /**
    Get result of XPath query.

    @param query        XPath query
    @return         result of query
     */
    private static String query(String query) {
        String result = "";
        try {
            result = path.evaluate(query, document);
        }
        catch (Exception exception) {
            System.err.println("could not perform query - " + exception);
        }
        return(result);
    }
}