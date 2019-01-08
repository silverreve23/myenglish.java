import javax.swing.*;
import java.awt.event.*;

class TranslateHandler extends AbstractAction {
	private Integer nowAttempt = 0;
	private Integer maxAttempt = 2;
	private WordsModel wordModel;
	private JDialog windowApp;
	private JLabel labelText;
	private JTextField textField;

	public TranslateHandler(JDialog jDialog, WordsModel wModel, JLabel lText, JTextField tField) {
		windowApp = jDialog;
		wordModel = wModel;
		labelText = lText;
		textField = tField;
	}

	public void actionPerformed(ActionEvent e) {
		try {
			nowAttempt = wordModel.checkTranslate(textField, windowApp);
			if (nowAttempt > maxAttempt)
				labelText.setText(wordModel.trans);
		} catch (Exception exc) {
		}
	}
}
