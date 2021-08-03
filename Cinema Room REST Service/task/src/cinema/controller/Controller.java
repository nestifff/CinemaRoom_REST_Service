package cinema.controller;

import cinema.model.Cinema;
import cinema.model.Seat;
import cinema.model.SeatRowColumn;
import cinema.model.TokenTicket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.UUID.randomUUID;

@RestController
public class Controller {

    private static final Cinema cinema;
    private static final List<TokenTicket> tokenTickets = new ArrayList<>();

    static {

        List<Seat> listSeats = new ArrayList<>();
        for (int i = 1; i <= 9; ++i) {
            for (int j = 1; j <= 9; ++j) {

                if (i <= 4) {
                    listSeats.add(new Seat(i, j, 10));
                } else {
                    listSeats.add(new Seat(i, j, 8));
                }
            }
        }
        cinema = new Cinema(9, 9, listSeats);

        for (int i = 0; i < 81; ++i) {
            tokenTickets.add(new TokenTicket(randomUUID(), listSeats.get(i)));
        }
    }

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
                if (seat.isBought()) {

                    Map<String, String> mapErr = new HashMap<>();
                    mapErr.put("error", "The ticket has been already purchased!");

                    return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);

                } else {

                    seat.setBought(true);

                    UUID id = randomUUID();

                    for (TokenTicket tt : tokenTickets) {
                        if (tt.getTicket().equals(seat)) {
                            id = tt.getToken();
                        }
                    }

                    Map<String, Object> resp = new LinkedHashMap<>();
                    resp.put("token", id.toString());
                    resp.put("ticket", seat);

                    return new ResponseEntity<>(resp, HttpStatus.OK);
                }
            }
        }

        Map<String, String> map = new HashMap<>();
        map.put("error", "The number of a row or a column is out of bounds!");

        return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/return")
    public ResponseEntity<?> returnTicket(@RequestBody Map<String, String> data) {

        if (!data.containsKey("token") || data.size() != 1) {

            Map<String, String> mapErr = new HashMap<>();
            mapErr.put("error", "Illegal body of request");

            return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);
        }

        UUID id = UUID.fromString(data.get("token"));
        Seat seat = null;

        for (TokenTicket tt : tokenTickets) {
            if (tt.getToken().equals(id)) {
                seat = tt.getTicket();
            }
        }

        if (seat == null) {

            Map<String, String> mapErr = new HashMap<>();
            mapErr.put("error", "Wrong token!");
            return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);

        } else if (!seat.isBought()) {

            Map<String, String> mapErr = new HashMap<>();
            mapErr.put("error", "Ticket is already returned");
            return new ResponseEntity<>(mapErr, HttpStatus.BAD_REQUEST);
        }

        seat.setBought(false);

        Map<String, Object> resp = new LinkedHashMap<>();
        resp.put("returned_ticket", seat);

        return new ResponseEntity<>(resp, HttpStatus.OK);

    }

}
