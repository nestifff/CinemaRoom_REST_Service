package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SeatRowColumn {

    private int row;
    private int column;

    public SeatRowColumn() {}

    public SeatRowColumn(int row, int column) {
        this.row = row;
        this.column = column;
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
}
