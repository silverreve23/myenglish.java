import java.util.TimerTask;
import java.util.Date;

class PeriodRun extends TimerTask {
	private ModalApp modalApp;
	
	public void run() {
		modalApp.render();
		modalApp.show();
	}
	public void init(ModalApp modal){
		modalApp = modal;
	}
}
