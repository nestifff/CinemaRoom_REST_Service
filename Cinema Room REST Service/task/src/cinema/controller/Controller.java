package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.SeatRowColumn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    Cinema cinema = new Cinema(9, 9);

    @GetMapping("/seats")
    public Cinema returnCinema() {
        return cinema.getOnlyFreeSeats();
    }

    @PostMapping("/purchase")
    public ResponseEntity<?> buySeat(@RequestBody SeatRowColumn seatRowColumn) {

        List<Seat> temp = cinema.getAvailableSeats();
        for (Seat seat : temp) {
            if (
                    seat.getColumn() == seatRowColumn.getColumn() &&
                            seat.getRow() == seatRowColumn.getRow()
            ) {
                if (seat.getBought()) {

                    Map<String, String> map = new HashMap<>();
                    map.put("error", "The ticket has been already purchased!");
                    return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

                } else {
                    seat.setBought(true);
                    return new ResponseEntity<>(seat, HttpStatus.OK);
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("error", "The number of a row or a column is out of bounds!");
        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

    }
}
