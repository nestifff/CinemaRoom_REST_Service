package cinema.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class TokenTicket {

    private UUID token;
    private Seat ticket;

    public TokenTicket(UUID token, Seat ticket) {
        this.token = token;
        this.ticket = ticket;
    }

    @JsonProperty("token")
    public UUID getToken() {
        return token;
    }

    @JsonProperty("token")
    public void setToken(UUID token) {
        this.token = token;
    }

    @JsonProperty("ticket")
    public Seat getTicket() {
        return ticket;
    }

    @JsonProperty("ticket")
    public void setTicket(Seat ticket) {
        this.ticket = ticket;
    }
}
