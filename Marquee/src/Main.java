import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Main extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JPanel panel = new JPanel();
	static JLabel label = new JLabel();
	public Main(){
		panel.setLayout(null);
		label.setText("Hello there!");
		//panel.add(label);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args){
		MarqueePanel mar = new MarqueePanel();
		mar.add(label);
		panel.add(mar);
		new Main().setVisible(true);
	}
}
