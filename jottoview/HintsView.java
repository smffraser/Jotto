package jottoview;

//import IView;

import jottomodel.*;
import javax.swing.*;
import javax.swing.JCheckBox;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;

public class HintsView extends JPanel {
	private JottoModel model ;

	private JCheckBox freq = new JCheckBox("Frequency");
	private JCheckBox vowels = new JCheckBox("Show Vowels");
	private JCheckBox annot = new JCheckBox("Annotate Target Letters");
	private JCheckBox giver = new JCheckBox("List Words");

	public HintsView(JottoModel amodel){
		super();
		this.model = amodel ;

   		freq.setMnemonic(KeyEvent.VK_C); 
    	freq.setSelected(false);
    	annot.setMnemonic(KeyEvent.VK_C); 
    	annot.setSelected(false);
    	vowels.setMnemonic(KeyEvent.VK_C); 
    	vowels.setSelected(false);
    	giver.setMnemonic(KeyEvent.VK_C); 
    	giver.setSelected(false);

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				freq.setSelected(model.getFreq());
				annot.setSelected(model.colourLetters());
				vowels.setSelected(model.getVowels());
				giver.setSelected(model.getGiver());
			}

		});
	}

	public void layoutView(){
		this.setLayout(new GridLayout());

		this.add(this.vowels);
		this.add(this.annot);
		this.add(this.freq);
		this.add(this.giver);

		//Dimension d = this.hypo.getPreferredSize();
		//d.width = 80;
		//this.base.setPreferredSize(d);
		//this.height.setPreferredSize(d);
		//this.hypo.setPreferredSize(d);
	}

	private void registerControllers() {
		this.vowels.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JCheckBox j = (JCheckBox) evt.getSource();
				model.setVowels(j.isSelected());
			}
		});

		this.annot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JCheckBox j = (JCheckBox) evt.getSource();
				model.setcolourLetters(j.isSelected());
			}
		});

		this.giver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JCheckBox j = (JCheckBox) evt.getSource();
				model.setGiver(j.isSelected());
			}
		});

		this.freq.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				JCheckBox j = (JCheckBox) evt.getSource();
				model.setFreq(j.isSelected());
			}
		});
	}

}