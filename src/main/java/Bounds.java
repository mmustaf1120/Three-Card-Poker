/**
 * Bounds represents the boundaries of the game board
 */
public class Bounds {
    public float x;
    public float y;
    public float height;
    public float width;
    public float xOffset;
    public float yOffset;

    Bounds(float x, float y, float width, float height, float xOffset, float yOffset) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }
}
