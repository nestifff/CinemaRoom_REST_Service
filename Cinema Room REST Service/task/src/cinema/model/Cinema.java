package cinema.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class Cinema {

    private int totalRows;
    private int totalColumns;
    private List<Seat> availableSeats;

    public Cinema() {}

    public Cinema(int totalRows, int totalColumns, List<Seat> list) {
        this.totalRows = totalRows;
        this.totalColumns = totalColumns;
        if (list.size() != totalColumns * totalRows) {
            throw new RuntimeException("Illegal number of seats while creating cinema!");
        }
        this.availableSeats = list;

    }

    @JsonIgnore
    public Cinema getOnlyFreeSeats() {

        Cinema newCinema = new Cinema();
        newCinema.setTotalColumns(9);
        newCinema.setTotalRows(9);

        List<Seat> seats = new ArrayList<>();
        for (Seat oldSeat: availableSeats) {
            if (!oldSeat.isBought()) {
                seats.add(oldSeat);
            }
        }

        newCinema.setAvailableSeats(seats);
        return newCinema;
    }

    @JsonIgnore
    public int getNumOfAvailable() {
        int num = 0;
        for (Seat seat: availableSeats) {
            if (!seat.isBought()) {
                ++num;
            }
        }
        return num;
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
