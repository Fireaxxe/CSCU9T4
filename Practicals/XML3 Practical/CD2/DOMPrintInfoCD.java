import java.io.*;               // import input-output

import javax.xml.parsers.*;         // import parsers
import javax.xml.xpath.*;           // import XPath

import org.w3c.dom.*;               // import DOM

/**
DOM handler to read XML information, to create this, and to print it.

@author   CSCU9T4, University of Stirling
@version  25/03/15
 */
public class DOMPrintInfoCD {

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
        Node CATALOG = document.getFirstChild();
        Node CD = CATALOG.getFirstChild().getNextSibling();
        System.out.println("Document root node is: " + CATALOG.getNodeName());
        System.out.println("  First " + CD.getNodeName() + " Data:");
        Node TITLE = CD.getFirstChild().getNextSibling();
        System.out.println("        " + TITLE.getFirstChild().getTextContent());
        Node ARTIST = TITLE.getNextSibling().getNextSibling();
        System.out.println("        " + ARTIST.getTextContent());
        Node COUNTRY = ARTIST.getNextSibling().getNextSibling();
        System.out.println("        " + COUNTRY.getTextContent());
        Node COMPANY = COUNTRY.getNextSibling().getNextSibling();
        System.out.println("        " + COMPANY.getTextContent());
        Node PRICE = COMPANY.getNextSibling().getNextSibling();
        System.out.println("        " + PRICE.getTextContent());
        Node YEAR = PRICE.getNextSibling().getNextSibling();
        System.out.println("        " + YEAR.getTextContent());

        
        
       
        //System.out.println("  Next CD data: " + query("/CATALOG/CD[2] "));  
        System.out.println("  Second CD data: ");
        System.out.println("        " + query("/CATALOG/CD[2]/TITLE")); 
        System.out.println("        " + query("/CATALOG/CD[2]/ARTIST")); 
        System.out.println("        " + query("/CATALOG/CD[2]/COUNTRY")); 
        System.out.println("        " + query("/CATALOG/CD[2]/COMPANY")); 
        System.out.println("        " + query("/CATALOG/CD[2]/PRICE")); 
        System.out.println("        " + query("/CATALOG/CD[2]/YEAR")); 
        
        System.out.println("  Last CD data: ");
        System.out.println("        " + query("/CATALOG/CD[26]/TITLE")); 
        System.out.println("        " + query("/CATALOG/CD[26]/ARTIST")); 
        System.out.println("        " + query("/CATALOG/CD[26]/COUNTRY")); 
        System.out.println("        " + query("/CATALOG/CD[26]/COMPANY")); 
        System.out.println("        " + query("/CATALOG/CD[26]/PRICE")); 
        System.out.println("        " + query("/CATALOG/CD[26]/YEAR"));         
        
        System.out.println("Total Number of CDs is: " + query("count(/CATALOG/CD)"));
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