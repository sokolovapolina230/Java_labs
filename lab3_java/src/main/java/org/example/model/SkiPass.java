package org.example.model;

public abstract class SkiPass {
    private static int counter = 1;
    private final int id;
    protected SkiPassType type;
    protected boolean isBlocked;

    public SkiPass(SkiPassType type) {
        this.id = counter++;
        this.type = type;
        this.isBlocked = false;
    }

    public int getId() { return id; }
    public SkiPassType getType() { return type; }
    public boolean isBlocked() { return isBlocked; }

    public void block() { this.isBlocked = true; }

    public abstract boolean use();

    @Override
    public String toString() {
        return "SkiPass{" +
                "id=" + id +
                ", type=" + type +
                ", blocked=" + isBlocked +
                '}';
    }
}
