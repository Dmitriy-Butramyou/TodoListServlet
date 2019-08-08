package model;

public enum State {

    ACTUAL, COMPLETE, DELETE;

    public static State getState(int number) {
        switch (number) {
            case 1:
                return ACTUAL;
            case 2:
                return COMPLETE;
            case 3:
                return DELETE;

        }
        return null;
    }
    public static int getNumber(State state) {
        switch (state) {
            case ACTUAL:
                return 1;
            case COMPLETE:
                return 2;
            case DELETE:
                return 3;
        }
        return 0;
    }

}
