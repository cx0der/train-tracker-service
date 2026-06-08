package blog.devrandom.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * CallingPointWithDetails
 */
public record CallingPointWithDetails(
        @SerializedName("callingPoint") List<CallingPoint> callingPoints,
        ServiceType serviceType,
        boolean serviceChangeRequired,
        boolean assocIsCancelled
) {
}
