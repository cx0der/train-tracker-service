package blog.devrandom.model;

import java.util.List;
import java.util.Optional;

/**
 * CallingPoint
 */
public record CallingPoint(
        String locationName,
        String crs,
        String st,
        String et,
        String at,
        boolean isCancelled,
        int length,
        boolean detachFront,
        Optional<FormationData> formation,
        List<String> adhocAlerts
) {
    @SuppressWarnings("OptionalAssignedToNull")
    public CallingPoint {
        if (formation == null) {
            formation = Optional.empty();
        }
    }
}
