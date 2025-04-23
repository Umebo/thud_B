package org.umeboshi.thud.game.movement.dto;

import java.util.Map;
import java.util.Set;

public record AvailableMovesResponse(
        Map<String, Set<String>> availableMoves,
        Map<String, Set<String>> availableTakes
) {}
