package org.umeboshi.thud.game.gameplay;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/new")
    GameSession getNewGameSession(@RequestBody Player player) {
        return gameplayService.createNewGame(player);
    }
}
