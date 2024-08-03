package main.java.com.app.board;

import java.util.Objects;

public record Treasure(int amount, int i, int j) implements CellValue {
    private static CellType type = CellType.TREASURE;

    @Override
    public String toString() {
        return amount > 0 ? String.format("T - %d - %d - %d", j, i, amount): "";
    }

    @Override
    public int getOrder() {
        return type.getOrder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Treasure treasure = (Treasure) o;
        return amount == treasure.amount && i == treasure.i && j == treasure.j;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, i, j);
    }

    @Override
    public boolean isStepable() {
        return true;
    }

}
