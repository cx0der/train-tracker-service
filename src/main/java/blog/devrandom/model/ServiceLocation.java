package blog.devrandom.model;

import java.util.Optional;

public record ServiceLocation(
        String locationName,
        String crs,
        Optional<String> via,
        String futureChangeTo,
        boolean assocIsCancelled) {

    @SuppressWarnings("OptionalAssignedToNull")
    public ServiceLocation {
        if (via == null) {
            via = Optional.empty();
        }
    }
}
