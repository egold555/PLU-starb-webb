package webb.shared.dtos.user;

import java.util.Comparator;

public class UserDTOComparator implements Comparator<UserDTO> {
    @Override
    public int compare(UserDTO o1, UserDTO o2) {
        // Compare the number of completed puzzles
        long result = o1.getStats().getPuzzlesComplete() - o2.getStats().getPuzzlesComplete();

        // If there's a tie, compare the average solve time
        if(result == 0)
            result = o1.getStats().getAvgSolveTime() - o2.getStats().getAvgSolveTime();

        // If there's still a tie, compare the usernames
        if(result == 0)
            result = o1.getUsername().compareTo(o2.getUsername());

        return -(int) result;
    }
}
