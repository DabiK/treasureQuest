package main.java.com.app;

import java.util.Objects;

public record Mountain(int i, int j) implements CellValue {
    private static CellType type = CellType.MOUNTAIN;

    @Override
    public int getOrder() {
        return type.getOrder();
    }

    @Override
    public boolean isStepable() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mountain mountain = (Mountain) o;
        return i == mountain.i && j == mountain.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(i, j);
    }

    @Override
    public String toString() {
        return String.format("M - %d - %d\n", i, j);
    }

}
