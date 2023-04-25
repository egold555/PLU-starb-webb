package webb.shared.dtos.old;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@Deprecated
public record Player_OLD(String username) {
    @JsonCreator
    public Player_OLD(@JsonProperty("username") String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player_OLD player = (Player_OLD) o;
        return Objects.equals(username, player.username);
    }
}
