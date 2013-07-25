package ironhack;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class make the classification object
 * It is currently used to execute the code also that's why we use static function definitions.
 * 
 * @author kabamaru
 *
 */
public class ClassifyMovies {

    /**
     * Classifying the movies according to the criterion selected by the user.
     * @param method
     */
    private static void classifyMovies(String method) {

        XMLParser parser = new XMLParser();

        MovieList parsedMovieList = parser.getMovieListFromFile("movies.xml");

        System.out.println("Original movie list:");
        parsedMovieList.print();

        ArrayList<MovieList> playLists = null;

        if (method.equalsIgnoreCase("popularity"))
            playLists = classifyByPopularity(parsedMovieList);
        else if (method.equalsIgnoreCase("mixed"))
            classifyMixed(parsedMovieList);
        else if (method.equalsIgnoreCase("director"))
            classifyByDirector(parsedMovieList);
        else 
            System.out.println("Set sorting the criteria: [popularity],[mixed], [director]");

        System.out.println("\nGreat Movies: ");
        playLists.get(0).print();

        System.out.println("\nMediocre Movies: ");
        playLists.get(1).print();

        System.out.println("\nBad Movies: ");
        playLists.get(2).print();
    }


    /** 
     * Creates three playlists according to the popularity
     * Great (> 8.0)
     * Mediocre (5.0 ~ 8.0)
     * Bad movies (< 5.0)
     * @param mv The initial movie list
     * @return
     */
    private static ArrayList<MovieList> classifyByPopularity(MovieList mvList) {

        ArrayList<MovieList> playLists = new ArrayList<MovieList>();

        MovieList greatMovies = new MovieList();
        MovieList mediocreMovies = new MovieList();
        MovieList badMovies = new MovieList();

        // We sort because want to show the movies in that order
        // This makes sense since the user asked for popularity distinguished lists. 
        // We suppose must be interested in this feature.
        // Also in the future we might want arbitrary categories
        Collections.sort(mvList, new Movie.OrderByPopularity());

        for(Movie mv : mvList) {
            if (mv.getPopularity() < 5.0) 
                badMovies.add(mv);
            else if (mv.getPopularity() >= 5 && mv.getPopularity() <= 8.0) 
                mediocreMovies.add(mv);
            else if (mv.getPopularity() > 8.0 && mv.getPopularity() <= 10.0) 
                greatMovies.add(mv);
            else
                System.out.println("Warning wrong popularity for movie: " + mv.getId() + " " + mv.getTitle() );
        }

        playLists.add(greatMovies);
        playLists.add(mediocreMovies);
        playLists.add(badMovies);

        return playLists;
    }

    /**
     * We use 
     * @param movieList
     */
    private static void classifyMixed(MovieList movieList) {

    }

    private static void classifyByDirector(MovieList movieList) {

    }

    public static void main(String[] args) { 
        classifyMovies("popularity");
    }

}
