package YU.PinforYouAPIServer.Controller;

import java.util.List;

public class AcceptFellowshipRequest {
    private Long user_id;
    private List<Long> acceptedRequests;

    // Getters and setters
    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public List<Long> getAcceptedRequests() {
        return acceptedRequests;
    }

    public void setAcceptedRequests(List<Long> acceptedRequests) {
        this.acceptedRequests = acceptedRequests;
    }
}
