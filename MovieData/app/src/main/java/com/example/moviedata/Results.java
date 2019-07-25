package com.example.moviedata;

public class Results {

    private String   video;

    private String[]   genre_ids;

    private String   backdrop_path;

    private String   original_language;

    private String   release_date;

    private String   overview;

    private String   title;

    private String   vote_average;

    private String   adult;

    private String   id;

    private String   popularity;

    private String   poster_path;

    private String vote_count;

    private String   original_title;

    public String getvideo ()
    {
        return   video;
    }

    public void setvideo (String   video)
    {
        this.  video =   video;
    }

    public String[] getgenre_ids ()
    {
        return   genre_ids;
    }

    public void setgenre_ids (String[]   genre_ids)
    {
        this.  genre_ids =   genre_ids;
    }

    public String getbackdrop_path ()
    {
        return   backdrop_path;
    }

    public void setbackdrop_path (String   backdrop_path)
    {
        this.  backdrop_path =   backdrop_path;
    }

    public String getoriginal_language ()
    {
        return   original_language;
    }

    public void setoriginal_language (String   original_language)
    {
        this.  original_language =   original_language;
    }

    public String getrelease_date ()
    {
        return   release_date;
    }

    public void setrelease_date (String   release_date)
    {
        this.  release_date =   release_date;
    }

    public String getoverview ()
    {
        return   overview;
    }

    public void setoverview (String   overview)
    {
        this.  overview =   overview;
    }

    public String gettitle ()
    {
        return   title;
    }

    public void settitle (String   title)
    {
        this.  title =   title;
    }

    public String getvote_average ()
    {
        return   vote_average;
    }

    public void setvote_average (String   vote_average)
    {
        this.  vote_average =   vote_average;
    }

    public String getadult ()
    {
        return   adult;
    }

    public void setadult (String   adult)
    {
        this.  adult =   adult;
    }

    public String getid ()
    {
        return   id;
    }

    public void setid (String   id)
    {
        this.  id =   id;
    }

    public String getpopularity ()
    {
        return   popularity;
    }

    public void setpopularity (String   popularity)
    {
        this.  popularity =   popularity;
    }

    public String getposter_path ()
    {
        return   poster_path;
    }

    public void setposter_path (String   poster_path)
    {
        this.  poster_path =   poster_path;
    }

    public String getVote_count ()
    {
        return vote_count;
    }

    public void setVote_count (String vote_count)
    {
        this.vote_count = vote_count;
    }

    public String getoriginal_title ()
    {
        return   original_title;
    }

    public void setoriginal_title (String   original_title)
    {
        this.  original_title =   original_title;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [  video = "+  video+",   genre_ids = "+  genre_ids+",   backdrop_path = "+  backdrop_path+",   original_language = "+  original_language+",   release_date = "+  release_date+",   overview = "+  overview+",   title = "+  title+",   vote_average = "+  vote_average+",   adult = "+  adult+",   id = "+  id+",   popularity = "+  popularity+",   poster_path = "+  poster_path+", vote_count = "+vote_count+",   original_title = "+  original_title+"]";
    }

}
