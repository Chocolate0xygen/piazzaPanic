package com.mygdx.game.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.game.Entity;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Stack;

public class Chef extends Entity {
    private float speed;

    public Chef(Texture image, Rectangle body, Stack<String> inventory,
                float speed) {
        super(image, body, inventory);
        this.prevx = body.x;
        this.prevy = body.y;
        this.speed = speed;
    }

    public void movement() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            this.prevx = this.body.x;
            this.body.x -= this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            this.prevx = this.body.x;
            this.body.x += this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            this.prevy = this.body.y;
            this.body.y += this.speed * Gdx.graphics.getDeltaTime();
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            this.prevy = this.body.y;
            this.body.y -= this.speed * Gdx.graphics.getDeltaTime();
        }
        if (this.body.x < 36) this.body.x = 36;
        if (this.body.x > 804 - 48) this.body.x = 804 - 48;
        if (this.body.y < 36) this.body.y = 36;
        if (this.body.y > 652 - 48) this.body.y = 652 - 48;
    }


    public void interact(Entity e, int stationType, String ingredient) {
        if (stationType == 0 && Gdx.input.isKeyJustPressed(Input.Keys.E)
                && this.inventory.size() < 4 && distance(this, e) < 100
                && ingredient != null) {

            this.inventory.push(ingredient);

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 1
                && distance(this, e) < 60 && !(this.inventory.isEmpty())) {

            this.inventory.pop();
            e.score++;

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 2
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Raw Patty")) {

            this.speed = 0;
            this.inventory.pop();
            this.inventory.push("Half-Cooked Patty");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 2
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Half-Cooked Patty")) {

            this.speed = 0;
            this.inventory.pop();
            this.inventory.push("Cooked Patty");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 3
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Lettuce")) {

            this.speed = 0;
            this.inventory.pop();
            this.inventory.push("Chopped Lettuce");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 3
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Tomato")) {

            this.speed = 0;
            this.inventory.pop();
            this.inventory.push("Chopped Tomato");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 3
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals("Onion")) {

            this.speed = 0;
            this.inventory.pop();
            this.inventory.push("Chopped Onion");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 4
                && distance(this, e) < 100
                && new HashSet<>(this.inventory).containsAll(Arrays.asList("Burger Bun",
                "Chopped Lettuce", "Cooked Patty"))) {

            this.speed = 0;
            this.inventory.remove("Cooked Patty");
            this.inventory.remove("Chopped Lettuce");
            this.inventory.remove("Burger Bun");
            this.inventory.push("Burger");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 4 &&
                distance(this, e) < 100 &&
                new HashSet<>(this.inventory).containsAll(Arrays.asList("Chopped Lettuce",
                        "Chopped Tomato", "Chopped Onion"))) {

            this.inventory.remove("Chopped Lettuce");
            this.inventory.remove("Chopped Tomato");
            this.inventory.remove("Chopped Onion");
            this.speed = 0;
            this.inventory.push("Salad");

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 5
                && distance(this, e) < 90
                && !(this.inventory.isEmpty())
                && e.stationInv.size() < 4) {

            e.stationInv.push(this.inventory.pop());

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.F) && e.stationType == 5
                && distance(this, e) < 90
                && this.inventory.size() < 4
                && !(e.stationInv.isEmpty())) {
            this.inventory.push(e.stationInv.pop());

        } else if (Gdx.input.isKeyJustPressed(Input.Keys.E) && e.stationType == 6
                && distance(this, e) < 100
                && !(this.inventory.isEmpty())
                && this.inventory.peek().equals(Customer.order)) {
            this.inventory.pop();
            e.score++;
            Customer.state = 3;
            Customer.order = "";
        }


    }

    public void collide(Entity e) {
        if ((e != this) & e.body.overlaps(this.body)) {
            float test = this.body.x;
            this.body.x = this.prevx;
            if (e.body.overlaps(this.body)) {
                this.body.x = test;
                this.body.y = this.prevy;
            }
        }

    }

    private double distance(Chef e1, Entity e2) {
        // Manhattan distance
        return (Math.sqrt(Math.pow((e1.body.x - e2.body.x), 2)
                + Math.pow((e1.body.y - e2.body.y), 2)));

    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
