package com.example.athena.beans;

public class ReviewTutorBean
{
    private int reviewStars ;
    private String code ;

    public ReviewTutorBean(int reviewStars, String code)
    {
        setReviewStars(reviewStars) ;
        setReviewCode(code) ;
    }

    public void setReviewCode(String code)
    {
        this.code = code ;
    }

    public void setReviewStars(int reviewStars)
    {
        this.reviewStars = reviewStars ;
    }

    public int getReviewStars()
    {
        return this.reviewStars ;
    }

    public String getCode()
    {
        return this.code ;
    }
}
