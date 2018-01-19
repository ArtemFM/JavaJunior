package apavlov.model.element.unit;

import apavlov.board.Cell;
import apavlov.board.Position;
import apavlov.model.element.unit.monster.Drop;
import apavlov.model.element.unit.monster.Onion;
import apavlov.model.element.unit.monster.Sponge;
import apavlov.model.element.unit.player.Player;

/**
 * The enum MakeUnit - for creating new units.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public enum MakeUnit {
    /**
     * The field for creating unit (monster) Onion.
     */
    ONION {
        @Override
        public Unit createUnit(Position position, Cell[][] board) {
            return new Onion(position, board);
        }
    },
    /**
     * The field for creating unit (monster) Drop.
     */
    DROP {
        @Override
        public Unit createUnit(Position position, Cell[][] board) {
            return new Drop(position, board);
        }
    },
    /**
     * The field for creating unit Player.
     */
    PLAYER {
        @Override
        public Unit createUnit(Position position, Cell[][] board) {
            return new Player(position, board);
        }
    },
    /**
     * The field for creating unit (monster) Sponge.
     */
    SPONGE {
        @Override
        public Unit createUnit(Position position, Cell[][] board) {
            return new Sponge(position, board);
        }
    };

    /**
     * The method create new unit.
     *
     * @param position - position for unit;
     * @param board - game board;
     * @return new unit;
     */
    public abstract Unit createUnit(Position position, Cell[][] board);
}
