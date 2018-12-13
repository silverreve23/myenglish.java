import java.util.Timer;

public class MyEnglish{
	public static void main(String args[]){
		Timer timer = new Timer();
		PeriodRun task = new PeriodRun();
		WordsModel model = new WordsModel();
		ModalApp modal = new ModalApp(model);
		modal.init();
		modal.run();
		task.init(modal);
		timer.schedule(task, 0, 5000);
	}
}

