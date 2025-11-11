package org.example.model;


public class TimeSkiPass extends SkiPass {
    private final int validHours;
    private final long activationTime;

    public TimeSkiPass(SkiPassType type, int validHours) {
        super(type);
        this.validHours = validHours;
        this.activationTime = System.currentTimeMillis();
    }

    @Override
    public boolean use() {
        if (isBlocked) return false;
        long elapsedHours = (System.currentTimeMillis() - activationTime) / (1000 * 60 * 60);
        return elapsedHours < validHours;
    }

    public int getValidHours() {
        return validHours;
    }
}
