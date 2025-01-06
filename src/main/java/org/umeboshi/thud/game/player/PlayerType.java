package org.umeboshi.thud.game.player;

public enum PlayerType {
    DWARF(1),
    TROLL(2);

    public final int value;

    PlayerType(int value) {
        this.value = value;
    }
}
