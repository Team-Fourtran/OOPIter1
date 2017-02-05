package application.models.tileState;

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

    private int getValue(){
        return this.value;
    }
}