import java.util.LinkedList;

public class MessageGenerator {
	private LinkedList<Mode> modes = new LinkedList<Mode>();
	private Mode currentMode;
	
	public MessageGenerator(){
		initializeModes();
	}
	
	private void initializeModes(){
		modes.add((Mode) new UnitMode());
		modes.add((Mode) new StructureMode());
		modes.add((Mode) new RallyPointMode());
		modes.add((Mode) new ArmyMode());
	}
}

interface Mode{
	
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