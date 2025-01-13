package org.umeboshi.thud.game.board;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BoardConstantsTests {

    @Test
    public void testTilesAndCoordinatesMapCreation() {
        // given

        //when
        Pair<Integer, Integer> tileCoordinates = BoardConstants.TILES_TO_COORDINATES.get("G13");

        // then
        Assertions.assertEquals(2, (int) tileCoordinates.getLeft());
        Assertions.assertEquals(6, (int) tileCoordinates.getRight());
    }
}