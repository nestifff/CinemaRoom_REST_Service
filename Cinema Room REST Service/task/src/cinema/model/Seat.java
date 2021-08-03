package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Seat {

    private int row;
    private int column;
    private int price;
    private boolean isBought;

    public Seat(int row, int column, int price) {
        this.row = row;
        this.column = column;
        this.price = price;
        this.isBought = false;
    }

    @JsonProperty("row")
    public int getRow() {
        return row;
    }

    @JsonProperty("row")
    public void setRow(int row) {
        this.row = row;
    }

    @JsonProperty("column")
    public int getColumn() {
        return column;
    }

    @JsonProperty("column")
    public void setColumn(int column) {
        this.column = column;
    }

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    @JsonProperty("price")
    public void setPrice(int price) {
        this.price = price;
    }

    @JsonIgnore
    public boolean isBought() {
        return isBought;
    }

    @JsonIgnore
    public void setBought(boolean bought) {
        isBought = bought;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seat seat = (Seat) o;
        return row == seat.row &&
                column == seat.column &&
                price == seat.price &&
                isBought == seat.isBought;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, price, isBought);
    }
}
