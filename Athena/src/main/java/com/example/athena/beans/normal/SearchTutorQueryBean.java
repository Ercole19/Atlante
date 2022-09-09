package com.example.athena.beans.normal;

public class SearchTutorQueryBean {
    private String query ;
    private String byCourseOrName ;
    private boolean byBestReviews ;

    public String getQuery() {
        return this.query ;
    }

    public String getSearchType() {
        return this.byCourseOrName ;
    }
    
    public boolean isBybestreviews() {
        return this.byBestReviews ;
    }
    
    public void setQuery(String query) {
        this.query = query ;
    }
    
    public void setByCourseOrName(String type) {
        this.byCourseOrName = type ;
    }
    
    public void setByBestReviews(boolean type) {
        this.byBestReviews = type ;
    }
}
