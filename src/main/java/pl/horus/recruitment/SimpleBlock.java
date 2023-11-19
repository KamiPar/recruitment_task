package pl.horus.recruitment;

public class SimpleBlock implements Block {

    public SimpleBlock(String color, String material) {
        this.color = color;
        this.material = material;
    }

    private final String color;
    private final String material;

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public String getMaterial() {
        return material;
    }
}
