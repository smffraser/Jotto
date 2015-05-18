package jottoview;

//import IView;

import jottomodel.*;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;

public class FindWordView extends JPanel {
	private JottoModel model ;

	private JButton findwords = new JButton("Find Words");

	public FindWordView(JottoModel amodel){
		super();
		this.model = amodel ;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				findwords.setEnabled(model.getGiver());
			}

		});
	}

	public void layoutView(){
		this.setLayout(new GridLayout());

		this.add(this.findwords);
	}

	private void registerControllers() {
		this.findwords.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JButton j = (JButton) evt.getSource();
				JFrame words = new JFrame("Find Words");
				String list = model.findWords() ;
            	JOptionPane.showMessageDialog(words, list);
			}
		});
	}

}