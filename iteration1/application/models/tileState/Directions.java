package application.models.tileState;
/*
 * Enum for representing degree directions to N,S,E,W etc
 */
public enum Directions {
    N(90),
    NE(45),
    E(0),
    SE(315),
    S(270),
    SW(225),
    W(180),
    NW(135);

    private int value;

    Directions(int _v){
        this.value = _v;
    }

    public int getValue(){
        return this.value;
    }
}