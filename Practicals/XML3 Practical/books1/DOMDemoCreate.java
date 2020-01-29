import java.io.*;               // import input-output

import javax.xml.parsers.*;         // import parsers
import javax.xml.xpath.*;           // import XPath
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;               // import DOM

/**
DOM handler to read XML information, to create this, and to print it.

@author   CSCU9T4, University of Stirling
@version  11/03/2017
 */
public class DOMDemoCreate{

    /** Document builder */
    private static DocumentBuilder builder = null;

    /** XML document */
    private static Document document = null;

    /*----------------------------- General Methods ----------------------------*/

    /**
    Main program to call DOM creator.

    @param args         command-line name of flile
     */
    public static void main(String[] args)  {

        // The argument is used as the filename
        createDocument(args[0]);

    }

    /**
    Set global document to create with data
    @param filename     name of the file to write
     */
    private static void createDocument(String filename) {
        try {
            // create a document builder
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builder = builderFactory.newDocumentBuilder();

            // new document
            document = builder.newDocument();

            // create library as the root element and add it to the document
            Element library = document.createElement("library");
            document.appendChild(library);    

            // add  a book

            Element book = document.createElement("book");
            Element title = document.createElement("title");
            Element category = document.createElement("category");
            Element year = document.createElement("year");
            title.appendChild(document.createTextNode("Introduction to XML"));
            category.appendChild(document.createTextNode("Computing"));
            year.appendChild(document.createTextNode("2001"));
            book.appendChild(title);
            book.appendChild(category);
            book.appendChild(year);
            library.appendChild(book);

            Element book1 = document.createElement("book");
            Element title1 = document.createElement("title");
            Element category1 = document.createElement("category");
            Element year1 = document.createElement("year");
            title1.appendChild(document.createTextNode("The Blue Planet"));
            category1.appendChild(document.createTextNode("Bulls**t"));
            year1.appendChild(document.createTextNode("2014"));
            book1.appendChild(title1);
            book1.appendChild(category1);
            book1.appendChild(year1);
            library.appendChild(book1); 

            Element book2 = document.createElement("book");
            Element title2 = document.createElement("title");
            Element category2 = document.createElement("category");
            Element year2 = document.createElement("year");
            title2.appendChild(document.createTextNode("Climbing to the Stars"));
            category2.appendChild(document.createTextNode("Novel"));
            year2.appendChild(document.createTextNode("2014"));
            book2.appendChild(title2);
            book2.appendChild(category2);
            book2.appendChild(year2);
            library.appendChild(book2);     

            // This allows saving the DOM as a file with indendation
            File file = new File(filename);
            Source source = new DOMSource(document);
            Result result = new StreamResult(file);
            Transformer transf = TransformerFactory.newInstance().newTransformer();
           
            transf.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transf.setOutputProperty(OutputKeys.INDENT, "yes");            

            transf.transform(source, result);
        }
        catch (Exception exception) {
            System.err.println("could not create document " + exception);
        }
    }
}