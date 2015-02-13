package Project.Gui;

import java.io.OutputStream;

import javax.swing.JTextArea;

public class MyOutputstream extends OutputStream {
	
	private JTextArea textArea;
	
	public MyOutputstream(JTextArea textarea) {
		
		this.textArea = textarea;
	
	}
	public void write(int x){
	
		textArea.append(String.valueOf((char)x));
		textArea.setCaretPosition(textArea.getDocument().getLength());
	}

}
