package notes;

public class Control {
	
	private Gui gui;
	private Data data;
	
	public Control() {
		this.data = new Data();
		this.gui = new Gui(this);
	}

	public static void main(String[] args) {
		Control c = new Control();
	}

}
