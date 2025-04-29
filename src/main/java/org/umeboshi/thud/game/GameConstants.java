package org.umeboshi.thud.game;

import org.apache.commons.lang3.tuple.Pair;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class GameConstants {

    public static final int BOARD_SIZE = 15;

    public static final List<Integer> OUT_OF_BOARD = Arrays.asList(-1, 15);

    public static final List<Integer> ON_THE_EDGE = Arrays.asList(0, 14);

    static final List<Integer> VERTICAL_AXIS = Arrays.asList(
            1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15
    ).reversed();

    static final List<String> HORIZONTAL_AXIS = Arrays.asList(
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O"
    );

    public static final Map<String, Pair<Integer, Integer>> TILES_TO_COORDINATES = createTilesToCoordinatesMap();

    public static final Map<Pair<Integer, Integer>, String> COORDINATES_TO_TILES = invertMap(TILES_TO_COORDINATES);

    public static final List<Pair<Integer, Integer>> DIRECTIONS = Arrays.asList(
            Pair.of(0, 1), Pair.of(1, 1),
            Pair.of(0, -1), Pair.of(1, -1),
            Pair.of(1, 0), Pair.of(-1, 1),
            Pair.of(-1, 0), Pair.of(-1, -1)
    );

    public static final List<String> CLASSIC_DWARF_STARTING_TILES = Arrays.asList(
            "F15", "G15", "I15", "J15",
            "E14", "K14", "D13", "L13", "C12", "M12", "B11", "N11",
            "A10", "O10", "A9", "O9", "A7", "O7", "A6", "O6",
            "B5", "N5", "C4", "M4", "D3", "L3", "E2", "K2",
            "F1", "G1", "I1", "J1"
    );

    public static final List<String> CLASSIC_TROLL_STARTING_TILES = Arrays.asList(
            "G9", "H9", "I9", "G8", "I8", "G7", "H7", "I7"
    );

    public static final List<String> CLASSIC_OUTSIDE_TILES = Arrays.asList(
            "A15", "B15", "C15", "D15", "E15", "K15", "L15", "M15", "N15", "O15",
            "A14", "B14", "C14", "D14", "L14", "M14", "N14", "O14",
            "A13", "B13", "C13", "M13", "N13", "O13",
            "A12", "B12", "N12", "O12",
            "A11", "O11",
            "H8",
            "A5", "O5",
            "A4", "B4", "N4", "O4",
            "A3", "B3", "C3", "M3", "N3", "O3",
            "A2", "B2", "C2", "D2", "L2", "M2", "N2", "O2",
            "A1", "B1", "C1", "D1", "E1", "K1", "L1", "M1", "N1", "O1"
    );

    private static Map<String, Pair<Integer, Integer>> createTilesToCoordinatesMap() {
        Map<String, Pair<Integer, Integer>> map = new HashMap<>();
        VERTICAL_AXIS.forEach(number ->
            HORIZONTAL_AXIS.forEach(letter ->
                map.put(letter + number.toString(), Pair.of(BOARD_SIZE-number, letter.charAt(0)-65))
            )
        );
        return map;
    }

    private static <V, K> Map<V, K> invertMap(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    private GameConstants() {
    }
}