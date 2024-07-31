package main.java.com.app;

public class Board {

    private int width;
    private int height;

    public static final int MAX_AREA = 85192;

    public Board(int width, int height) {
        if(width * height > Board.MAX_AREA){
            throw new IllegalArgumentException(String.format("Area is too big max is {0}", Board.MAX_AREA));
        }
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
