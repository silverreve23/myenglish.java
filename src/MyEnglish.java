import java.util.Timer;

public class MyEnglish {
	public static void main(String args[]) {
		try {
			Timer timer = new Timer();
			PeriodRun task = new PeriodRun();
			WordsModel model = new WordsModel();
			ModalApp modal = new ModalApp(model);
			modal.init();
			modal.run();
			task.init(modal);
			timer.schedule(task, 0, model.period);
			System.out.println("Runed! With period: " + model.period);
		} catch (Exception e) {
			System.out.println("Exception!");
			Logger.log(e.getMessage());
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			System.out.println(e.fillInStackTrace());
		}
	}
}
