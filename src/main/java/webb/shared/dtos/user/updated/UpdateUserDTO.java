package webb.shared.dtos.user.updated;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class UpdateUserDTO {

    @NotNull
    private final UpdatedUserStatsDTO stats;

    /**
     * Creates a new UpdatedStatsDTO
     * @param stats the updated stats of the user
     */
    public UpdateUserDTO(@JsonProperty("stats") UpdatedUserStatsDTO stats) {
        this.stats = stats;
    }

    /**
     * @return the updated stats of the user
     */
    public UpdatedUserStatsDTO getStats() {return stats;}

}
