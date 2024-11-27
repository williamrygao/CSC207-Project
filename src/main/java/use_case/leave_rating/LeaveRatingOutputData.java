package use_case.leave_rating;

/**
 * Output Data for the LeaveRating Use Case.
 */
public class LeaveRatingOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public LeaveRatingOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}

