package com.martenscedric;

import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import static com.martenscedric.GameManager.HEIGHT;
import static com.martenscedric.GameManager.WIDTH;

public class Phone extends ScrollPane
{
    private Table messageTable;

    public Phone(Skin skin) {
        super(new Table(skin), skin);
        this.messageTable = (Table) getActor();
        this.setScrollingDisabled(true, false);
        this.debug();
    }

    public void addTweet(Tweet tweet)
    {
        this.messageTable.add(tweet.getContents()).width(getWidth());
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
        return HEIGHT * 0.8f;
    }

    private class TweetWidget extends WidgetGroup
    {

    }
}
