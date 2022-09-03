package model;

public class FreeRoom extends Room{
    public FreeRoom() {
        setPrice(0.00);
    }

    @Override
    public String toString() {
        return "FreeRoom{}";
    }
}
