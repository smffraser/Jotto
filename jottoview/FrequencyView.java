package jottoview;

//import IView;

import jottomodel.*;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;

public class FrequencyView extends JPanel {
	private JottoModel model ;

	private JButton freq = new JButton("Frequency");

	public FrequencyView(JottoModel amodel){
		super();
		this.model = amodel ;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				freq.setEnabled(model.getFreq());
			}

		});
	}

	public void layoutView(){
		this.setLayout(new GridLayout());

		this.add(this.freq);
	}

	private void registerControllers() {
		this.freq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JButton j = (JButton) evt.getSource();
				JFrame words = new JFrame("Frequency");
				String list = model.frequency() ;
            	JOptionPane.showMessageDialog(words, list);
			}
		});
	}

}