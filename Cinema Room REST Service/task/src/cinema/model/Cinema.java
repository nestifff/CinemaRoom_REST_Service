package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.CheckedInputStream;

public class Cinema {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    public Cinema() {}

    public Cinema(int totalRows, int totalColumns) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        availableSeats = new ArrayList<>();
        for (int i = 1; i <= 9; ++i)
            for (int j = 1; j <= 9; ++j) {

                if (i <= 4) {
                    availableSeats.add(new Seat(i, j, 10));
                } else {
                    availableSeats.add(new Seat(i, j, 8));
                }
            }
    }

    @JsonIgnore
    public Cinema getOnlyFreeSeats() {

        Cinema newCinema = new Cinema();
        newCinema.setTotalColumns(9);
        newCinema.setTotalRows(9);

        List<Seat> seats = new ArrayList<>();
        for (Seat oldSeat: availableSeats) {
            if (!oldSeat.getBought()) {
                seats.add(oldSeat);
            }
        }

        newCinema.setAvailableSeats(seats);
        return newCinema;
    }

    @JsonProperty("total_rows")
    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    @JsonProperty("total_columns")
    public int getTotalColumns() {
        return totalColumns;
    }

    public void setTotalColumns(int totalColumns) {
        this.totalColumns = totalColumns;
    }

    @JsonProperty("available_seats")
    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(List<Seat> availableSeats) {
        this.availableSeats = availableSeats;
    }
}
