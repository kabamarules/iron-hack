package ironhack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class MovieList extends ArrayList<Movie>
{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MovieList(String filename) {
        Document xmlDocument = parseFile(filename);
        parseXMLDocument(xmlDocument);
        System.out.println("Parsed " + this.size() + " movies from file " + filename);
    }

    public void print() {
        for(Movie mv : this) {
            mv.print();
        }
    }
    
    private Document parseFile(String filename) {
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

    private void parseXMLDocument(Document xmlDocument) {

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

                this.add(tempMovie);

                //tempMovie.print();
            }
        }
    }

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

    private String getStringFromElement(Element el, String tagName) {
        String textVal = null;
        NodeList nl = el.getElementsByTagName(tagName);
        if(nl != null && nl.getLength() > 0) {
            Element stringElement = (Element)nl.item(0);
            textVal = stringElement.getFirstChild().getNodeValue();
        }
        return textVal;
    }

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