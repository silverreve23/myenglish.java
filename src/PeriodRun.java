import java.util.TimerTask;
import java.util.Date;

class PeriodRun extends TimerTask {
	private ModalApp modalApp;

	public void run() {
		try {
			modalApp.render();
			modalApp.show();
		} catch (Exception e) {
		}
	}

	public void init(ModalApp modal) {
		modalApp = modal;
	}
}
