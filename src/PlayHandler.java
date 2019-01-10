import java.awt.event.*;

class PlayHandler extends MouseAdapter {
	private Speatch speatch;

	public PlayHandler() {
		speatch = new Speatch();
	}

	public void mouseClicked(MouseEvent e) {
		speatch.play();
	}
}
