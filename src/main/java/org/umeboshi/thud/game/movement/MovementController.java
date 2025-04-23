package org.umeboshi.thud.game.movement;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.umeboshi.thud.game.movement.dto.AvailableMovesResponse;
import org.umeboshi.thud.game.movement.dto.AvailableMovesRequest;

@RestController
public class MovementController {

    private final ClassicThudMovementService classicThudMovementService;

    public MovementController(ClassicThudMovementService classicThudMovementService) {
        this.classicThudMovementService = classicThudMovementService;
    }

    @PostMapping
    AvailableMovesResponse getAvailableMoves(@RequestBody AvailableMovesRequest availableMovesRequest) {
        return classicThudMovementService.getAvailableMoves(availableMovesRequest);
    }

}
