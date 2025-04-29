package org.umeboshi.thud.game.entities;

public enum PlayerType {
    DWARF(1),
    TROLL(2);

    public final int value;

    PlayerType(int value) {
        this.value = value;
    }
}
