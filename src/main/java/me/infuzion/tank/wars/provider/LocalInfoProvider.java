package me.infuzion.tank.wars.provider;

import me.infuzion.tank.wars.object.*;
import me.infuzion.tank.wars.util.Position;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class LocalInfoProvider implements InfoProvider {
    private static List<Tank> tanks = new CopyOnWriteArrayList<>();
    private List<GameObject> objects = new CopyOnWriteArrayList<>();
    private List<Drawable> drawables = new CopyOnWriteArrayList<>();
    private List<Tickable> tickables = new CopyOnWriteArrayList<>();
    private int fps;

    public LocalInfoProvider() {
        new Tank("Player 1", 640, 0, 0, this);
        new Tank("Player 2", 0, 240, 18, this);
        new Wall(50, 50, this);
        new Wall(150, 50, this);
    }

    @Override
    public List<GameObject> getGameObjects() {
        return objects;
    }

    @Override
    public List<Drawable> getDrawableObjects() {
        return drawables;
    }

    @Override
    public List<Tickable> getTickableObjects() {
        return tickables;
    }

    @Override
    public void addGameObject(GameObject toAdd) {
        objects.add(toAdd);
        if (toAdd instanceof Drawable) {
            drawables.add((Drawable) toAdd);
        }
        if (toAdd instanceof Tickable) {
            tickables.add((Tickable) toAdd);
        }
        if (toAdd instanceof Tank) {
            tanks.add((Tank) toAdd);
        }
    }

    @Override
    public List<Tank> getTanks() {
        return LocalInfoProvider.tanks;
    }

    @Override
    public void updateTanks(List<Tank> tanks) {
        LocalInfoProvider.tanks = tanks;
    }

    @Override
    public void setFPS(int fps) {
        this.fps = fps;
    }

    @Override
    public int getFPS() {
        return fps;
    }

    @Override
    public synchronized void removeGameObject(GameObject object) {
        objects.removeIf(e -> e.getUuid() == object.getUuid());
        tickables.removeIf(e -> e.getUuid() == object.getUuid());
        drawables.removeIf(e -> e.getUuid() == object.getUuid());
        tanks.removeIf(e -> e.getUuid() == object.getUuid());
    }


}