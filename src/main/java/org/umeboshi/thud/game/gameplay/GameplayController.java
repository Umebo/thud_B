package org.umeboshi.thud.game.gameplay;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.umeboshi.thud.game.entities.GameSession;
import org.umeboshi.thud.game.entities.Player;

@RestController
@RequestMapping("/gameplay")
public class GameplayController {

    private final ClassicThudGameplayService gameplayService;

    public GameplayController(ClassicThudGameplayService gameplayService) {
        this.gameplayService = gameplayService;
    }

    @PostMapping("/new")
    @ResponseStatus(HttpStatus.CREATED)
    GameSession getNewGameSession(@RequestBody Player player) {
        return gameplayService.createNewGame(player);
    }
}
