package com.martenscedric;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class Phone extends ScrollPane
{
    private GameManager gameManager;
    private Table messageTable;
    private List<Tweet> trumpTweets = new ArrayList<>();
    private List<Tweet> kimTweets = new ArrayList<>();

    private OnNukeButtonPress onNukeButtonPress;
    private float heat = 0;
    private float timeSinceLastTweet = 10;

    private boolean nuked = false;

    public Phone(GameManager gameManager, Skin skin) {
        super(new Table(skin), skin);
        this.gameManager = gameManager;
        this.messageTable = (Table) getActor();
        this.setScrollingDisabled(true, false);

        readTweetFile("messages/trump.txt", trumpTweets);
        readTweetFile("messages/kim.txt", kimTweets);
    }

    public void update(float delta)
    {
        timeSinceLastTweet+=delta;
        if(new Random().nextInt(60) == 0)
            System.out.println(heat);

        if(!nuked)
        {
            boolean buttonPress = new Random().nextFloat() * 5000 < heat;

            if(buttonPress)
                onNukeButtonPress.handle();
        }

        if(!nuked && timeSinceLastTweet > 10 - heat * 2)
        {
            Author author = Author.values()[new Random().nextInt(2)];

            if(author == Author.TRUMP)
            {
                List<Tweet> availableTweets = new ArrayList<>(trumpTweets);
                availableTweets.removeIf(t -> t.getMinHeat() > heat);
                System.out.println("Trump has " + availableTweets.size() + " available tweets!");

                Tweet tweet = availableTweets.get(new Random().nextInt(availableTweets.size()));
                trumpTweets.removeIf(t -> t.getTweetId() == tweet.getTweetId());

                addTweet(tweet);
                heat+=tweet.getGeneratedHeat();
            }else{
                List<Tweet> availableTweets = new ArrayList<>(kimTweets);
                availableTweets.removeIf(t -> t.getMinHeat() > heat);
                System.out.println("Kim has " + availableTweets.size() + " available tweets!");

                Tweet tweet = availableTweets.get(new Random().nextInt(availableTweets.size()));
                kimTweets.removeIf(t -> t.getTweetId() == tweet.getTweetId());

                addTweet(tweet);
                heat+=tweet.getGeneratedHeat();
            }

            heat *= 1.15f;
            timeSinceLastTweet = 0;
        }
    }

    public void addTweet(Tweet tweet)
    {
        this.messageTable.add(new TweetWidget(tweet, Utils.getDefaultSkin())).width(getWidth());
        this.messageTable.row();
        this.layout();
        this.scrollTo(0, 0, 0, 0);
    }

    public void setOnNukeButtonPress(OnNukeButtonPress onNukeButtonPress) {
        this.onNukeButtonPress = onNukeButtonPress;
    }

    public void stop()
    {
        nuked = true;
    }

    public boolean isNuked()
    {
        return nuked;
    }

    @Override
    public float getWidth() {
        return WIDTH * 0.4f;
    }

    @Override
    public float getHeight() {
        return HEIGHT * 0.8f - 20;
    }

    private class TweetWidget extends WidgetGroup
    {
        private Image profilePic;
        private boolean media;
        private Label lblText;

        public TweetWidget(Tweet tweet, Skin skin)
        {
            Texture textureLeader = gameManager.assetManager.get("art/" + tweet.getAuthor().name().toLowerCase() + ".png", Texture.class);
            profilePic = new Image(textureLeader);
            profilePic.setWidth(64);
            profilePic.setHeight(64);
            this.profilePic.setX(15);
            this.lblText = new Label(tweet.getContents(), skin);
            this.lblText.setWrap(true);
            this.lblText.setWidth(200);
            lblText.setAlignment(Align.bottomLeft);
            lblText.setX(100);
            lblText.setHeight(lblText.getMinHeight());
        }

        @Override
        protected void setStage(Stage stage) {
            super.setStage(stage);
            addActor(profilePic);
            addActor(lblText);
        }

        @Override
        public float getMinHeight() {
            return Math.max(lblText.getPrefHeight() + 50, profilePic.getImageHeight());
        }
    }


    private void readTweetFile(String filename, List<Tweet> list)
    {
        int id = 0;
        try {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while (line != null) {
                Tweet tweet = new Tweet();
                tweet.setAuthor(filename.contains("trump.txt") ? Author.TRUMP :Author.KIM);
                tweet.setContents(line);
                tweet.setTweetId(id++);
                list.add(tweet);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
