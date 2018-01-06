package com.martenscedric;

public class Tweet
{
    private Author author;

    //Retweets will start with (something)
    //Linked media is done like this ~/media
    private String contents;
    private float generatedHeat;
    private float minHeat;
    private int tweetId;

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
        String[] tweetContents = contents.split("\\[");
        String[] values = tweetContents[1].split(",");
        minHeat = Float.parseFloat(values[0]);
        generatedHeat = Float.parseFloat(values[1].substring(0, values[1].length() -1));
        this.contents = tweetContents[0];
    }

    public float getGeneratedHeat() {
        return generatedHeat;
    }

    public void setGeneratedHeat(float generatedHeat) {
        this.generatedHeat = generatedHeat;
    }

    public float getMinHeat() {
        return minHeat;
    }

    public void setMinHeat(float minHeat) {
        this.minHeat = minHeat;
    }

    public int getTweetId() {
        return tweetId;
    }

    public void setTweetId(int tweetId) {
        this.tweetId = tweetId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        return tweetId == tweet.tweetId;
    }

    @Override
    public int hashCode() {
        return tweetId;
    }
}
