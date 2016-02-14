package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;

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

public class AboutWindow extends JDialog {
	private JPanel contentPane, main, footer;
	private JButton btnOk;
	private JLabel lbTitle,lbAuthor;
	private JTextArea txtDescription;
	private JScrollPane scrollPane;

	
	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		try {
			AboutWindow dialog = new AboutWindow();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Create the dialog.
	 */
	public AboutWindow() {
		initGUI();
		//setVisible(true);
		
	}
	/**
	 * initialise UI elements
	 */
	private void initGUI(){
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 550, 550);
		setUIFont(new FontUIResource(new Font("Cambria",Font.PLAIN, 18)));
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout());
		
		String title = "Project Sales Registry";
		String author = "Copyright "
				+ "\u00a9"
				+ " 2016 by Evgeniya O'Regan Pevchikh";
		String text = "This project demonstrates a practical application of knowledge gained in Java programming.\n\n"
				+ "The aim of this project was to convert a similar project developed in C# into Java using MVC design.\n\n"
				+ "The application is designed to help a small business to manage their customer records.\n\n"
				+ "The following features are present:\nadding, editing and deleting records;\nsorting records by different parameters;\n"
				+ "displaying records for certain time periods;\nprinting sales reports for chosen time period;\n"
				+ "generating and printing invoices to customers";
		
		setTitle("About "+title);
		
		lbTitle = new JLabel(title);
		lbAuthor = new JLabel(author);
		
		txtDescription = new JTextArea(text);
		txtDescription.setLineWrap(true);
		txtDescription.setWrapStyleWord(true);
		txtDescription.setEditable(false);
		//txtDescription.setEnabled(false);
		
		scrollPane = new JScrollPane(txtDescription); 
		scrollPane.setPreferredSize(new Dimension(520, 380));
		
		btnOk = new JButton("OK");
		//handle ok button click
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				//hide about dialog
				setVisible(false);
			}
			
		});
		
		main = new JPanel();
		main.setLayout(new MigLayout());
		main.add(lbTitle, "cell 0 0");
		main.add(lbAuthor, "cell 0 1");
		main.add(scrollPane," cell 0 2 , align center");
		
		footer = new JPanel();
		footer.setLayout(new MigLayout());
		footer.add(btnOk," cell 0 0 , align center");
		
		contentPane.add(main, "cell 0 0, align center");
		contentPane.add(footer, "cell 0 1, align center");
		
		
		
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
		
}
