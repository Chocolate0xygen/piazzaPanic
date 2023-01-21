package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

import java.util.Stack;

public class entity {
    public Texture image;
    public Rectangle body;
    public Stack<String> inventory;
    public float prevx = 0;
    public float prevy = 0;
    public boolean collide = false;

    public boolean isIngredientStation = false;

    public String ingredient;
    entity(Texture image, Rectangle body){
        this.image = image;
        this.body = body;
    }

    entity(Texture image, Rectangle body, Stack<String> inventory){
        this.image = image;
        this.body = body;
        this.inventory = inventory;
    }

    entity(Texture image, Rectangle body, boolean isIngredientStation, String ingredient){
        this.image = image;
        this.body = body;
        this.isIngredientStation = isIngredientStation;
        this.ingredient = ingredient;
    }


}
