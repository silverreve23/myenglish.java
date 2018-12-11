import javax.swing.*;
import java.awt.event.*;

class TranslateHandler extends AbstractAction{
	private Integer nowAttempt = 0;
	private Integer maxAttempt = 2;
	private WordsModel wordModel;
	private JLabel labelText;
	private JTextField textField;
	
	public TranslateHandler(WordsModel wModel, JLabel lText, JTextField tField){
		wordModel = wModel;
		labelText = lText;
		textField = tField;
	}
	public void actionPerformed(ActionEvent e){
		nowAttempt = wordModel.checkTranslate(textField);
	    if(nowAttempt > maxAttempt) labelText.setText(wordModel.trans);
	}
}
