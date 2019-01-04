import java.util.Timer;

public class MyEnglish{
	public static void main(String args[]){
		try{
			Timer timer = new Timer();
			PeriodRun task = new PeriodRun();
			WordsModel model = new WordsModel();
			ModalApp modal = new ModalApp(model); 
			modal.init();
			modal.run();
			task.init(modal);
			modal.render();
			modal.show();
			timer.schedule(task, 0, model.period);
			System.out.println("Runed!");
		}catch(Exception e){
			Logger.log(e.getMessage());
			System.out.println(e.getClass());
			System.out.println(e.getMessage());
			System.out.println(e.fillInStackTrace());
    	}
	}
}

	
