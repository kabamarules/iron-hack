package ironhack;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * This is a assistive class used to parse an XML file.
 * @author kabamaru
 *
 */
public class XMLParser {
    
    /**
     * This is the only public member function. It reads the file (filename) from the disk and puts it to a Document.
     * Then using the Element and Node libraries of Java it parses the DOM tree and convert the entries into Movie objects.
     * These objects are put into a list which is finally returned.
     * @param filename The name of the file to be processed
     * @return A MovieList object containing the parsed movies from the file.
     */
    public MovieList getMovieListFromFile(String filename) {
        Document xmlDocument = readFiletoXMLDocument(filename);
        MovieList parsedMovieList = parseXMLDocument(xmlDocument);
        System.out.println("Parsed " + parsedMovieList.size() + " movies from file " + filename);
        return parsedMovieList;
    }
    
    
    /**
     * It read the file and converts the contents into a Document object.
     * @param filename The file to be read.
     * @return A Document containing the elements of the file.
     */
    private Document readFiletoXMLDocument(String filename) {
        Document xmlDocument = null;
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder =  builderFactory.newDocumentBuilder();
            xmlDocument = builder.parse(filename);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        return xmlDocument;
    }
    
    /**
     * It parses the DOM Document object using Element and Node.
     * Each first level Node (movie) is further processed by getMovieFromElement() to get all the attributes;
     * @param xmlDocument
     * @return A movie list containing all the movies found in the xmlDocument
     */
    private MovieList parseXMLDocument(Document xmlDocument) {
        MovieList parsedMovieList = new MovieList();
        
        //Get the root elememt
        Element rootElement = xmlDocument.getDocumentElement();
        //Get the movie elements
        NodeList nl = rootElement.getElementsByTagName("movie"); 

        if(nl != null && nl.getLength() > 0) {
            for(int i = 0; i < nl.getLength(); i++) {

                //Get the current movie element
                Element movieElement = (Element)nl.item(i);

                //Set the temporary movie object
                Movie tempMovie = getMovieFromElement(movieElement);

                parsedMovieList.add(tempMovie);

                //tempMovie.print();
            }
        }
        return parsedMovieList;
    }
    
    /**
     * This function converts a movie Element to a Movie object.
     * @param[in] movieEl The movie in Element object format.
     * @return The output Movie object.
     */
    private Movie getMovieFromElement(Element movieEl) {
        Movie mv = new Movie();

        int id = getIntFromElement(movieEl, "id");
        String title = getStringFromElement(movieEl, "title");
        int year = getIntFromElement(movieEl, "year");
        String[] genres = getStringArrayFromElement(movieEl, "genre");
        String director = getStringFromElement(movieEl, "director");    
        String[] cast = getStringArrayFromElement(movieEl, "actor");
        String[] tags = getStringArrayFromElement(movieEl, "tag");
        double popularity = getDoubleFromElement(movieEl, "popularity");


        mv.setId(id);
        mv.setTitle(title);
        mv.setYear(year);
        mv.setGenre(genres);
        mv.setDirector(director);
        mv.setActors(cast);
        mv.setTags(tags);
        mv.setPopularity(popularity);

        return mv;
    }

    /**
     * Helping function. Returns a string from an element attribute.
     * @param el
     * @param tagName
     * @return
     */
    private String getStringFromElement(Element el, String tagName) {
        String textVal = null;
        NodeList nl = el.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element stringElement = (Element)nl.item(0);
            textVal = stringElement.getFirstChild().getNodeValue();
        }
        return textVal;
    }

    /**
     * Like getString but for multiple valued attributes such as tags, actors etc.
     * @param el
     * @param tagName
     * @return
     */
    private String[] getStringArrayFromElement(Element el, String tagName) {
        String[] textVal = null;
        NodeList nl = el.getElementsByTagName(tagName);

        if(nl != null && nl.getLength() > 0) {                
            textVal = new String[nl.getLength()];

            for (int i = 0; i < nl.getLength(); i++ ) {
                Node rootNode = nl.item(i);
                textVal[i] = rootNode.getTextContent();
            }
        }
        return textVal;
    }

    private int getIntFromElement(Element el, String tagName) {
        //in production application you would catch the exception
        return Integer.parseInt(getStringFromElement(el, tagName));
    }

    private double getDoubleFromElement(Element el, String tagName) {
        //in production application you would catch the exception
        return Double.parseDouble(getStringFromElement(el, tagName));
    }
    
}
