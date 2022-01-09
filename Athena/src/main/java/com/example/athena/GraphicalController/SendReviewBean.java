package com.example.athena.GraphicalController;

public class SendReviewBean
{
    private int reviewStars ;

    public SendReviewBean(int reviewStars)
    {
        setReviewStars(reviewStars) ;
    }

    public void setReviewStars(int reviewStars)
    {
        this.reviewStars = reviewStars ;
    }

    public int getReviewStars()
    {
        return this.reviewStars ;
    }
}
