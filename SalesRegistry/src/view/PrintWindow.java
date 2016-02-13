package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Enumeration;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;

import net.miginfocom.swing.MigLayout;

public class PrintWindow extends JDialog {
	private JPanel contentPane, header, main, footer;
	private JLabel lbInfo;
	private JButton btnEnable;
	private JButton btnPrint;
	private JTextArea txtReport;
	private JScrollPane scrollPane;
	private boolean isEnabled = false;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PrintWindow dialog = new PrintWindow();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the dialog.
	 */
	public PrintWindow() {
		initialiseGUI();
		//setVisible(true);
	}
	
	private void initialiseGUI(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 580, 500);
		setUIFont(new FontUIResource(new Font("Cambria",Font.PLAIN, 18)));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout());
		
		lbInfo = new JLabel("You can adjust text before printing");
		btnEnable = new JButton("Enable editing");
		btnPrint = new JButton("Print");
		addImage(btnPrint,"c_print.png");
		
		txtReport = new JTextArea("");
		txtReport.setLineWrap(true);
		txtReport.setWrapStyleWord(true);
		txtReport.setEditable(false);
		//txtReport.setText("test");
		
		scrollPane = new JScrollPane(txtReport); 
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setPreferredSize(new Dimension(500, 450));
		
		//handle enable editing of text area button click
		btnEnable.addActionListener(new ActionListener() {

            
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//if text area is disabled
				if(!isEnabled){
					//enable editing
					txtReport.setEditable(true);
					//txtReport.append(" add");
					//change button text
					btnEnable.setText("Disable editing");
					//change flag accordingly
					isEnabled = true;
				}//if text area editing is enabled - reverse above actions
				else{
					txtReport.setEditable(false);
					btnEnable.setText("Enable editing");
					isEnabled = false;
				}
			}
        });
		
		header = new JPanel();
		header.setLayout(new MigLayout());
		header.add(lbInfo, "cell 0 0 2 1, gapright 35");//width 2, height 1
		header.add(btnEnable, "cell 3 0, gapleft 35");
		
		main = new JPanel();
		main.setLayout(new MigLayout());
		main.add(scrollPane," cell 0 0 , align center");
		
		footer = new JPanel();
		footer.setLayout(new MigLayout());
		footer.add(btnPrint," cell 0 0 , align center");
		
		contentPane.add(header, "cell 0 0, align center");
		contentPane.add(main, "cell 0 1, align center");
		contentPane.add(footer, "cell 0 2, align center");
		
	}
	//set font to all elements
		public void setUIFont(FontUIResource f) {
	        Enumeration<Object> keys = UIManager.getDefaults().keys();
	        while (keys.hasMoreElements()) {
	            Object key = keys.nextElement();
	            Object value = UIManager.get(key);
	            if (value instanceof FontUIResource) {
	                FontUIResource orig = (FontUIResource) value;
	                Font font = new Font(f.getFontName(), orig.getStyle(), f.getSize());
	                UIManager.put(key, new FontUIResource(font));
	                
	            }
	        }
		}
		/**
		 * add image to a button component
		 * @param btn button
		 * @param path path to image
		 */
		private void addImage(JButton btn, String path)
		{
			try {
			    Image img = ImageIO.read(getClass().getResource("/"+path));
			    btn.setIcon(new ImageIcon(img));
			  } catch (IOException ex) {
			  }
		}

		public JPanel getHeader() {
			return header;
		}

		public JPanel getFooter() {
			return footer;
		}

		public JButton getBtnPrint() {
			return btnPrint;
		}

		public JTextArea getTxtReport() {
			return txtReport;
		}

		//adding controller
				public void addController(ActionListener controller){
					System.out.println("print      : adding controller");
					btnPrint.addActionListener(controller);
					
				}
		
}
