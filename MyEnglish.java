public class MyEnglish{
	public static void main(String args[]){
		WordsModel model = new WordsModel();
		ModalApp modal = new ModalApp(model);
		modal.init();
		modal.run();
		modal.render();
		modal.show();
	}
}

