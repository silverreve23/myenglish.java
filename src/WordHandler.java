import java.awt.event.*;

class WordHandler extends MouseAdapter{
	private Speatch speatch;
	
	public WordHandler(){
		speatch = new Speatch();
	}
	public void mouseClicked(MouseEvent e){
		speatch.play();
	}
}
