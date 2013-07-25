package ironhack;

import java.util.ArrayList;

/**
 * This class is used to represent the lists of movies that we have on our database.
 * It extends the ArrayList object and serves as a shell class.
 * @author kabamaru
 *
 */
public class MovieList extends ArrayList<Movie>
{
    /**
     * Needed by java
     */
    private static final long serialVersionUID = 1L;

    /**
     * Prints the movie list.
     */
    public void print() {
        for(Movie mv : this) {
            mv.print();
        }
    }

}