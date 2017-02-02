
public class Controller {
	MessageGenerator msgGen;
	
	public Controller(){
		//Create a new MessageGenerator with self as the receiver
		this.msgGen = new MessageGenerator(this);
	}
}
