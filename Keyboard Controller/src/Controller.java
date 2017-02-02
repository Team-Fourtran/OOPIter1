
public class Controller {
	MessageGenerator msgGen;
	
	public Controller(){
		//Create a new MessageGenerator with self as the receiver
		this.msgGen = new MessageGenerator(this);
	}
	
	/* TODO: For now, just prints messages that it receives */
	protected void handleMsg(String msg){
		System.out.println("Controller " + this + " received message:\n" + msg);
	}
}
