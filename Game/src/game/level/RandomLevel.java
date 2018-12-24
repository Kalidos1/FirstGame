package game.level;

import java.util.Random;

public class RandomLevel extends Level {

    private static final Random random = new Random();


    public RandomLevel(int width, int height) {
        super(width, height);
        generateLevel();
    }

    protected void generateLevel() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                tilesInt[x + y * width] = random.nextInt(4); //0-3, 0 = Grass, 1 = Water etc.
            }
        }
    }

    @Override
    public String toString() {
        return null;
    }
}
