package org.umeboshi.thud.game.movement.dto;

import org.umeboshi.thud.game.entities.PlayerType;

import java.util.UUID;

public record AvailableMovesRequest(
        UUID gameId,
        String tileSignature,
        PlayerType playerType
) {}
