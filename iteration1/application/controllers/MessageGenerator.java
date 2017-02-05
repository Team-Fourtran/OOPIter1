package application.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;


class MessageGenerator {
    private ArrayList<Mode> modes = new ArrayList<Mode>();
    private Mode currentMode;
    private int modeIndex;	//Originally used ListIterator, but the Java List interface iterators are garbage

    private HashMap<String, Iterator> assetIterators;

    private Controller receiver;

    MessageGenerator(Controller receiver, ArrayList<String> keysPressedList, HashMap<String, Iterator> assetIterators){
        initializeModes();
        this.assetIterators = assetIterators;
        this.receiver = receiver;
        //System.out.println(this.modes);
        listenToKeyboard(keysPressedList);
    }

    protected void updateIterators(HashMap<String, Iterator> assetIterators){
        this.assetIterators = assetIterators;
    }

    private void listenToKeyboard(ArrayList<String> ks){
        //Runs perpetually
        System.out.println("Syntax:\n\t'UP','DOWN','LEFT','RIGHT','CONTROL','ENTER'\n\t(Space-separated)");
        Scanner s = new Scanner(System.in);

        while(true){
            //System.out.println(keystrokes);

            System.out.println("\n\nEnter simulated keystrokes: ");
            ArrayList<String> keystrokes = new ArrayList<String>();
            String[] tempS = s.nextLine().split(" ");
            for(int i = 0; i < tempS.length; i++)
                keystrokes.add(tempS[i]);

            // Enter and nothing else == "Submit"
            if(keystrokes.contains("ENTER") && keystrokes.size() == 1){
                generateMessage();
            }

            if(keystrokes.contains("CONTROL")){
                //Cycle MODE
                if(keystrokes.contains("UP")){
                    nextMode();
                } else if(keystrokes.contains("DOWN")){
                    previousMode();
                }

                //Cycle TYPE
                else if(keystrokes.contains("LEFT")){
                    this.currentMode.controlLeft();			//Forward to Mode
                } else if(keystrokes.contains("RIGHT")){
                    this.currentMode.controlRight();		//Forward to Mode
                }

            } else {
                if(keystrokes.contains("UP")){
                    this.currentMode.upKey();
                } else if(keystrokes.contains("DOWN")){
                    this.currentMode.downKey();
                }

                //Cycle TYPE
                else if(keystrokes.contains("LEFT")){
                    this.currentMode.leftKey();
                } else if(keystrokes.contains("RIGHT")){
                    this.currentMode.rightKey();
                }
            }


        }
    }

    private void generateMessage(){
        //Sends receiver a message as generated by the current Mode
        receiver.handleMsg(this.currentMode.generateMessage());
    }

    private void initializeModes(){
        //Fill this.modes with the 4 modes
        this.modes.add((Mode) new RallyPointMode());
        this.modes.add((Mode) new StructureMode());
        this.modes.add((Mode) new UnitMode());
        this.modes.add((Mode) new ArmyMode());

        //Initialize currentMode
        this.modeIndex = 0;
        this.currentMode = (Mode) this.modes.get(modeIndex);
    }

    //Switches the mode to the next mode on the list, looping back around when the end is reached.
    private void nextMode(){
        this.modeIndex = Utils.mod(this.modeIndex + 1,this.modes.size());
        this.currentMode = (Mode) modes.get(modeIndex);
    }

    //Switches the mode to the previous mode on the list, looping back around when the beginning is reached.
    private void previousMode(){
        this.modeIndex = Utils.mod(this.modeIndex - 1,this.modes.size());
        this.currentMode = (Mode) modes.get(modeIndex);
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

    UnitMode(){
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
    }

    @Override //Cycle to next type
    public void controlRight() {
        this.currentTypeIndex = Utils.mod(currentTypeIndex + 1, this.unitTypes.size());
        this.currentType = this.unitTypes.get(this.currentTypeIndex);
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

class StructureMode implements Mode{
    private ArrayList<String> baseTypes = new ArrayList<String>();
    private String currentType;
    private int currentTypeIndex;

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

    ArmyMode() {
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
    }

    @Override //Cycle to next submode
    public void controlRight() {
        this.subModeIndex = Utils.mod(subModeIndex + 1, this.subModes.size());
        this.currentSubMode = this.subModes.get(this.subModeIndex);
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