package com.martenscedric;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.Align;

import java.util.ArrayList;
import java.util.List;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class Phone extends ScrollPane
{
    private GameManager gameManager;
    private Table messageTable;
    private List<Tweet> tweetList;

    public Phone(GameManager gameManager, Skin skin) {
        super(new Table(skin), skin);
        this.gameManager = gameManager;
        this.messageTable = (Table) getActor();
        this.tweetList = new ArrayList<>();
        this.setScrollingDisabled(true, false);
    }

    public void addTweet(Tweet tweet)
    {
        this.messageTable.add(new TweetWidget(tweet, Utils.getDefaultSkin())).width(getWidth());
        this.messageTable.row();
        this.layout();
        this.scrollTo(0, 0, 0, 0);
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
}
