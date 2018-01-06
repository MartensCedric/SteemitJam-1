package com.martenscedric;

public class Tweet
{
    private enum Author
    {
        TRUMP,
        KIM
    }

    private Author author;

    //Retweets will start with (something)
    //Linked media is done like this ~/media
    private String contents;
    private float generatedHeat;


    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public float getGeneratedHeat() {
        return generatedHeat;
    }

    public void setGeneratedHeat(float generatedHeat) {
        this.generatedHeat = generatedHeat;
    }
}
