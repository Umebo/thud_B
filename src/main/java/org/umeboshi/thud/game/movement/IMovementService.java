package org.umeboshi.thud.game.movement;

import org.umeboshi.thud.game.movement.dto.AvailableMovesResponse;
import org.umeboshi.thud.game.movement.dto.AvailableMovesRequest;

interface IMovementService {
AvailableMovesResponse getAvailableMoves(AvailableMovesRequest availableMovesRequest);
}
