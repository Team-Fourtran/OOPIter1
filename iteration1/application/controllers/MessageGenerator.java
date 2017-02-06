package application.controllers;



import java.util.*;

import application.models.playerAsset.Player;
import application.models.playerAsset.PlayerAsset;


class MessageGenerator implements KeyPressListener{
    private ArrayList<Mode> modes = new ArrayList<Mode>();
    private Mode currentMode;
    private int modeIndex;	//Originally used ListIterator, but the Java List interface iterators are garbage

    protected HashMap<String, ListIterator> assetIterators;

    private Controller receiver;

    MessageGenerator(Controller receiver, KeyPressInformer keyInformer, HashMap<String, ListIterator> assetIterators){
        initializeModes();
        this.assetIterators = assetIterators;
        this.receiver = receiver;
        //System.out.println(this.modes);
        keyInformer.registerClient(this);
    }
    //Gets called when player turn switches. Changes the iterators on hand.
    protected void updateIterators(HashMap<String, ListIterator> assetIterators){
        this.assetIterators = assetIterators;
    }

    @Override //Listen to notifications from a KeyPressInformer
    public void updateKeysPressed(HashMap<String, Boolean> kp) {
        interpretKeystrokes(kp);
    }

    private void interpretKeystrokes(HashMap<String, Boolean> keystrokes){
        //System.out.println(keystrokes);
        if(keystrokes.get("ENTER")){
            generateMessage();
        }

        if(keystrokes.get("CONTROL")){
            //Cycle MODE
            if(keystrokes.get("UP")){
                nextMode();
            } else if(keystrokes.get("DOWN")){
                previousMode();
            }

            //Cycle TYPE
            else if(keystrokes.get("LEFT")){
                this.currentMode.controlLeft();			//Forward to Mode
            } else if(keystrokes.get("RIGHT")){
                this.currentMode.controlRight();		//Forward to Mode
            }

        } else {
            if(keystrokes.get("UP")){
                this.currentMode.upKey();          		//Forward to Mode
            } else if(keystrokes.get("DOWN")){
                this.currentMode.downKey();		    	//Forward to Mode
            }

            //Cycle TYPE
            else if(keystrokes.get("LEFT")){
                this.currentMode.leftKey(); 			//Forward to Mode
            } else if(keystrokes.get("RIGHT")){
                this.currentMode.rightKey();    		//Forward to Mode
            }
        }
    }

    private void generateMessage(){
        //Sends receiver a message as generated by the current Mode
        receiver.handleMsg(this.currentMode.generateMessage());
    }

    private void initializeModes(){
        //Fill this.modes with the 4 modes
        this.modes.add((Mode) new RallyPointMode(this));
        this.modes.add((Mode) new StructureMode(this));
        this.modes.add((Mode) new UnitMode(this));
        this.modes.add((Mode) new ArmyMode(this));

        //Initialize currentMode
        this.modeIndex = 0;
        this.currentMode = (Mode) this.modes.get(modeIndex);
    }

    //Switches the mode to the next mode on the list, looping back around when the end is reached.
    private void nextMode(){
        this.modeIndex = Utils.mod(this.modeIndex + 1,this.modes.size());
        this.currentMode = (Mode) modes.get(modeIndex);
        System.out.println(currentMode);
    }

    //Switches the mode to the previous mode on the list, looping back around when the beginning is reached.
    private void previousMode(){
        this.modeIndex = Utils.mod(this.modeIndex - 1,this.modes.size());
        this.currentMode = (Mode) modes.get(modeIndex);
        System.out.println(currentMode);

    }
}

interface Mode{
    String generateMessage();


    void controlLeft();
    void controlRight();


    void upKey();
    void downKey();

    void leftKey();
    void rightKey();

    String toString();
}

class UnitMode implements Mode{
    private ArrayList<String> unitTypes = new ArrayList<String>();
    private String currentType;
    private int currentTypeIndex;
    MessageGenerator owner;

    boolean iteratingUnitsForward = true;  //To deal with ListIterator's funkiness

    String message = "";    //the ID of the unit in focus

    UnitMode(MessageGenerator owner){
        this.owner = owner;
        this.unitTypes.add("Explorer");
        this.unitTypes.add("Colonist");
        this.unitTypes.add("Melee Unit");
        this.unitTypes.add("Ranged Unit");

        this.currentTypeIndex = 0;
        this.currentType = unitTypes.get(currentTypeIndex);
    }

    public String toString(){
        return "Mode: Unit\tType:" + this.currentType;
    }

    public String generateMessage(){
        return "Generated message: " + this.toString();
    }

    @Override //Cycle to previous type
    public void controlLeft() {
        this.currentTypeIndex = Utils.mod(currentTypeIndex - 1, this.unitTypes.size());
        this.currentType = this.unitTypes.get(this.currentTypeIndex);
        System.out.println("Mode: Unit\tSubmode:" + currentType);
    }

    @Override //Cycle to next type
    public void controlRight() {
        this.currentTypeIndex = Utils.mod(currentTypeIndex + 1, this.unitTypes.size());
        this.currentType = this.unitTypes.get(this.currentTypeIndex);
        System.out.println("Mode: Unit\tSubmode:" + currentType);
    }

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {
        if(this.owner.assetIterators.get("unit").hasPrevious()){
            //Deal with ListIterator's iteration mechanics
            if(iteratingUnitsForward)
                this.owner.assetIterators.get("unit").previous();
            iteratingUnitsForward = false;

            PlayerAsset p = (PlayerAsset) this.owner.assetIterators.get("unit").previous();
            this.message = p.getID();
            System.out.println("Message state: " + message);
        }
    }

    @Override
    public void rightKey() {
        if(this.owner.assetIterators.get("unit").hasNext()){
            //Deal with ListIterator's iteration mechanics
            if(!iteratingUnitsForward)
                this.owner.assetIterators.get("unit").next();
            iteratingUnitsForward = true;

            PlayerAsset p = (PlayerAsset) this.owner.assetIterators.get("unit").next();
            this.message = p.getID();
            System.out.println("Message state: " + message);
        }
    }
}

class StructureMode implements Mode{
    private ArrayList<String> baseTypes = new ArrayList<String>();
    private String currentType;
    private int currentTypeIndex;
    MessageGenerator owner;

    StructureMode(MessageGenerator owner){
        this.owner = owner;
    }

    public String toString(){
        return "I am in Structure Mode";
    }

    public String generateMessage(){
        return "Generated message: " + this.toString();
    }

    @Override
    public void controlLeft() {
        // TODO Implement different structure types

    }

    @Override
    public void controlRight() {
        // TODO Implement different strucure types

    }

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }
}

class RallyPointMode implements Mode{
    MessageGenerator owner;

    RallyPointMode(MessageGenerator owner){
        this.owner = owner;
    }

    public String toString(){
        return "I am in RallyPoint Mode";
    }

    public String generateMessage(){
        return "Generated message: " + this.toString();
    }

    @Override // Nothing happens
    public void controlLeft() { }

    @Override // Nothing happens
    public void controlRight() { }

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }
}

class ArmyMode implements Mode{
    private ArrayList<Mode> subModes = new ArrayList<Mode>();
    private Mode currentSubMode = new EntireArmyMode();
    private int subModeIndex;
    MessageGenerator owner;

    ArmyMode(MessageGenerator owner) {
        this.owner = owner;
        subModes.add(new EntireArmyMode());
        subModes.add(new BattleGroupMode());
        subModes.add(new ReinforcementMode());

        subModeIndex = 0;
        currentSubMode = subModes.get(subModeIndex);
    }

    public String toString(){
        return "Mode: Army\tSubmode:" + this.currentSubMode;
    }

    public String generateMessage(){
        return "Generated message: " + this.toString();
    }

    @Override //Cycle to previous submode
    public void controlLeft() {
        this.subModeIndex = Utils.mod(subModeIndex - 1, this.subModes.size());
        this.currentSubMode = this.subModes.get(this.subModeIndex);
        System.out.println("Mode: Army\tSubmode:" + currentSubMode);
    }

    @Override //Cycle to next submode
    public void controlRight() {
        this.subModeIndex = Utils.mod(subModeIndex + 1, this.subModes.size());
        this.currentSubMode = this.subModes.get(this.subModeIndex);
        System.out.println("Mode: Army\tSubmode:" + currentSubMode);

    }

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }
}

class EntireArmyMode implements Mode{
    public String toString(){
        return "EntireArmy Mode";
    }

    public String generateMessage(){
        return this.toString();
    }

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlLeft() {}

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlRight() {}

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }

    EntireArmyMode(){

    }
}

class BattleGroupMode implements Mode{
    public String toString(){
        return "BattleGroup Mode";
    }

    public String generateMessage(){
        return this.toString();
    }

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlLeft() {}

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlRight() {}

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }

    BattleGroupMode(){

    }
}

class ReinforcementMode implements Mode{
    public String toString(){
        return "Reinforcement Mode";
    }

    public String generateMessage(){
        return this.toString();
    }

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlLeft() {}

    @Override //Unused - Taken care of by parent ArmyMode
    public void controlRight() {}

    @Override
    public void upKey() {

    }

    @Override
    public void downKey() {

    }

    @Override
    public void leftKey() {

    }

    @Override
    public void rightKey() {

    }

    ReinforcementMode(){

    }
}

//Custom operations to deal with some quirks
class Utils{
    //Different interpretation of mod for negative numbers.
    //Necessary for circular iteration through modes/types etc.
    public static int mod(int i, int n){
        return (i >= 0) ? (i%n) : (n+i);
    }
}