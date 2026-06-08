package blog.devrandom.model;

import java.util.List;
import java.util.Optional;

public record ServiceItemWithCallingPoints(
        List<ServiceLocation> origin,
        List<ServiceLocation> destination,
        Optional<List<ServiceLocation>> currentOrigins,
        Optional<List<ServiceLocation>> currentDestinations,
        Optional<String> sta,
        Optional<String> eta,
        Optional<String> std,
        Optional<String> etd,
        Optional<String> platform,
        String operator,
        String operatorCode,
        Optional<Boolean> isCircularRoute,
        boolean isCancelled,
        boolean filterLocationCancelled,
        ServiceType serviceType,
        int length,
        boolean detachFront,
        boolean isReverseFormation,
        String cancelReason,
        String delayReason,
        String serviceID,
        List<String> adhocAlerts,
        Optional<FormationData> formation,
        Optional<List<CallingPointWithDetails>> subsequentCallingPoints,
        Optional<List<CallingPointWithDetails>> previousCallingPoints,
        UncertaintyType uncertaintyType,
        String affectedBy
) {
    @SuppressWarnings("OptionalAssignedToNull")
    public ServiceItemWithCallingPoints {
        // GSON sets optional fields to null
        if (formation == null) {
            formation = Optional.empty();
        }
        if (currentOrigins == null) {
            currentOrigins = Optional.empty();
        }
        if (currentDestinations == null) {
            currentDestinations = Optional.empty();
        }
        if (sta == null) {
            sta = Optional.empty();
        }
        if (eta == null) {
            eta = Optional.empty();
        }
        if (std == null) {
            std = Optional.empty();
        }
        if (etd == null) {
            etd = Optional.empty();
        }
        if (platform == null) {
            platform = Optional.empty();
        }
        if (isCircularRoute == null) {
            isCircularRoute = Optional.empty();
        }
        if (subsequentCallingPoints == null) {
            subsequentCallingPoints = Optional.empty();
        }
        if (previousCallingPoints == null) {
            previousCallingPoints = Optional.empty();
        }
    }
}
