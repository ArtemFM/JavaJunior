package apavlov.api;

import apavlov.board.Position;

/**
 * The enum Move - for get next position for unit.
 *
 * @author Pavlov Artem
 * @since 19.01.2018
 */
public enum Move {
    /**
     * The constant movement to up.
     */
    UP {
        @Override
        public Position nextPosition(Position position) {
            return new Position(0, -1).getSumPosition(position);
        }
    },
    /**
     * The constant movement to down.
     */
    DOWN {
        @Override
        public Position nextPosition(Position position) {
            return new Position(0, 1).getSumPosition(position);
        }
    },
    /**
     * The constant movement to left.
     */
    LEFT {
        @Override
        public Position nextPosition(Position position) {
            return new Position(-1, 0).getSumPosition(position);
        }
    },
    /**
     * The constant movement to right.
     */
    RIGHT {
        @Override
        public Position nextPosition(Position position) {
            return new Position(1, 0).getSumPosition(position);
        }
    };

    /**
     * The method for get next position for unit.
     *
     * @param position = now position unit;
     * @return next position for unit;
     */
    public abstract Position nextPosition(Position position);
}
