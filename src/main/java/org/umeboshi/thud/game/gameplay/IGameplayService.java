package org.umeboshi.thud.game.gameplay;

import org.umeboshi.thud.game.entities.GameSession;
import org.umeboshi.thud.game.entities.Player;

public interface IGameplayService {
    GameSession createNewGame(Player firstPlayer);
}
