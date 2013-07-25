package ironhack;

public class ClassifyMovies {

    private static void sortMovies(String method) {
        MovieList movieList = new MovieList("movies.xml");
        movieList.print();

        if (method.equalsIgnoreCase("popularity"))
        	classifyByPopularity(movieList);
        else if (method.equalsIgnoreCase("mixed"))
            classifyMixed(movieList);
        else if (method.equalsIgnoreCase("director"))
            classifyByDirector(movieList);
        else 
            System.out.println("Set sorting the criteria: [popularity],[mixed], [director]");
        
        System.out.println();
        movieList.print();
    }

    private static void classifyByPopularity(MovieList movieList) {
        
    }
    
    private static void classifyMixed(MovieList movieList) {
        
    }
    
    private static void classifyByDirector(MovieList movieList) {
        
    }
    
    public static void main(String[] args) { 
        sortMovies("popularity");
    }

}
