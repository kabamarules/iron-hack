package ironhack;

import java.util.Arrays;
import java.util.Comparator;

/**
 * This class represents the movie entities. 
 * The attributes are protected and an interface for each one is provided.
 * Also provided:
 * Comparison functions (implementing Comparator).
 * A print function.
 * @author kabamaru
 */
public  class Movie {

    private int id;
    private String[] genres;
    private String[] tags;
    private String title;
    private int year;
    private String director;
    private String[] actors;
    private double popularity;

    public int getId(){
        return this.id;
    }

    public String[] getGenre(){
        return this.genres; 
    }

    public String[] getTags(){
        return this.tags;
    }

    public String getTitle(){
        return this.title;
    }

    public int getYear(){
        return this.year;
    }

    public String getDirector(){
        return this.director;
    }

    public String[] getActors(){
        return this.actors;
    }

    public double getPopularity(){
        return this.popularity;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setGenre(String[] genres){
        this.genres = genres;
    }

    public void setTags(String[] tags){
        this.tags = tags;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setYear(int year){
        this.year = year;
    }

    public void setDirector(String director){
        this.director = director;
    }

    public void setActors(String[] actors){
        this.actors = actors;
    }

    public void setPopularity(double popularity){
        this.popularity = popularity;
    }

    public void print() {
        System.out.println(this.id + " " + this.title + " " + this.year  + " " + Arrays.toString(this.genres)  + " " + this.director 
                + " " + Arrays.toString(this.actors)  + " " + Arrays.toString(this.tags)  + " " + this.popularity);   
    }

    /**
     * A Movie Comparator based on popularity
     * It sorts the movies in descending order.
     * @author kabamaru
     *
     */
    public static class OrderByPopularity implements Comparator<Movie> {
        @Override
        /**
         * Returns 1,0,-1 depending which movie is more popular
         */
        public int compare(Movie mv1, Movie mv2) {
            return mv1.getPopularity() > mv2.getPopularity() ? -1 : (mv1.getPopularity() < mv2.getPopularity() ? 1 : 0);
        }

    };



}