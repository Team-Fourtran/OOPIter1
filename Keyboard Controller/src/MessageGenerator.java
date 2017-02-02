import java.util.ArrayList;
import java.util.ListIterator;

public class MessageGenerator {
	private ArrayList<Mode> modes = new ArrayList<Mode>();
	private ListIterator<Mode> modeIterator;
	
	private Mode currentMode;
	
	private Controller receiver; 
	
	public MessageGenerator(Controller receiver){
		initializeModes();
		this.receiver = receiver;
	}
	
	private void initializeModes(){
		//Fill this.modes with the 4 modes
		this.modes.add((Mode) new UnitMode());
		this.modes.add((Mode) new StructureMode());
		this.modes.add((Mode) new RallyPointMode());
		this.modes.add((Mode) new ArmyMode());
		
		//Create an iterator for the list, initialized with the first element
		this.modeIterator = this.modes.listIterator(0);
	}
	
	//Switches the mode to the next mode on the list, looping back around when the end is reached.
	private void nextMode(){
		if(this.modeIterator.hasNext()){
			this.currentMode = this.modeIterator.next(); //Move to next mode if it exists
		} else{
			while(this.modeIterator.hasPrevious()){	//else go back to the first mode
				this.modeIterator.previous();
			}
		}
	}
	
	//Switches the mode to the previous mode on the list, looping back around when the beginning is reached.
	private void previousMode(){
		if(this.modeIterator.hasPrevious()){
			this.currentMode = this.modeIterator.previous();	//move to prev. mode if it exists
		} else{
			while(this.modeIterator.hasNext()){		//else go back to the last one
				this.modeIterator.next();
			}
		}
	}
}

interface Mode{
	void generateMessage();
	
	void leftKey();
	void rightKey();
	void upKey();
	void downKey();
	
	void controlLeft();
	void controlRight();
}

class UnitMode implements Mode{
	
}

class StructureMode implements Mode{
	
}

class RallyPointMode implements Mode{
	
}

class ArmyMode implements Mode{
	private LinkedList<Mode> subModes = new LinkedList<Mode>();
	private Mode currentSubMode;
	
}

class EntireArmyMode implements Mode{

}

class BattleGroupMode implements Mode{
	
}

class ReinforcementMode implements Mode{
	
}