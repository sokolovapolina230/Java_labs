package org.example.model;

public class LiftSkiPass extends SkiPass {
    private int remainingLifts;

    public LiftSkiPass(SkiPassType type, int lifts) {
        super(type);
        this.remainingLifts = lifts;
    }

    @Override
    public boolean use() {
        if (isBlocked || remainingLifts <= 0) return false;
        remainingLifts--;
        return true;
    }
    @Override
    public String toString() {
        return super.toString() +
                ", remainingLifts=" + remainingLifts;
    }

    public int getRemainingLifts() { return remainingLifts; }
}
