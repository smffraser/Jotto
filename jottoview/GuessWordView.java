package jottoview;

//import IView;

import jottomodel.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class GuessWordView extends JPanel {
	private JottoModel model;
	private JButton letterone = new JButton("-");
	private JButton lettertwo = new JButton("-");
	private JButton letterthree = new JButton("-");
	private JButton letterfour = new JButton("-");
	private JButton letterfive = new JButton("-");
	private JButton guess = new JButton("Guess!");

	private NumberFormat formatter = NumberFormat.getNumberInstance();
	private static final int MAX_FRACTION_DIGITS = 5;

	public GuessWordView(JottoModel aModel) {
		super();
		this.model = aModel;

		this.layoutView();
		this.registerControllers();

		this.model.addView(new IView() {
			public void updateView() {
				letterone.setText(model.getLetter(1));
				lettertwo.setText(model.getLetter(2));
				letterthree.setText(model.getLetter(3));
				letterfour.setText(model.getLetter(4));
				letterfive.setText(model.getLetter(5));

				Border border = new LineBorder(Color.RED, 3);

				if(model.getVowels()){
					if(model.isVowel(0)){
						letterone.setBorder(border);
					}
					if(model.isVowel(1)){
						lettertwo.setBorder(border);
					}
					if(model.isVowel(2)){
						letterthree.setBorder(border);
					}
					if(model.isVowel(3)){
						letterfour.setBorder(border);
					}
					if(model.isVowel(4)){
						letterfive.setBorder(border);
					}
				}
				else if(!model.getVowels()){
					border = UIManager.getBorder("Button.border");
					if(model.isVowel(0)){
						letterone.setBorder(border);
					}
					if(model.isVowel(1)){
						lettertwo.setBorder(border);
					}
					if(model.isVowel(2)){
						letterthree.setBorder(border);
					}
					if(model.isVowel(3)){
						letterfour.setBorder(border);
					}
					if(model.isVowel(4)){
						letterfive.setBorder(border);
					}
				}


			}

		});
	}

	private void layoutView() {
		this.setLayout(new GridLayout());
		this.add(new JLabel("Your Guess:"));
		//this.add(this.groupComponents(this.baseUp, this.baseDn, this.base));

		//this.add(new JLabel("Height:"));
		//this.add(this
				//.groupComponents(this.heightUp, this.heightDn, this.height));

		//this.add(new JLabel("Hypotenuse:"));
		//this.add(this.hypo);

		this.add(this.letterone);
		this.add(this.lettertwo);
		this.add(this.letterthree);
		this.add(this.letterfour);
		this.add(this.letterfive);
		this.add(this.guess);

		//Dimension d = this.hypo.getPreferredSize();
		//d.width = 80;
		//this.base.setPreferredSize(d);
		//this.height.setPreferredSize(d);
		//this.hypo.setPreferredSize(d);

		this.formatter.setMaximumFractionDigits(MAX_FRACTION_DIGITS);
	}

	private Box groupComponents(JButton up, JButton dn, JLabel label) {
		Box group = Box.createHorizontalBox();
		group.add(up);
		group.add(dn);
		group.add(label);

		Dimension d = up.getPreferredSize();
		d.width = Math.max(up.getPreferredSize().width,
				dn.getPreferredSize().width);
		up.setPreferredSize(d);
		dn.setPreferredSize(d);

		return group;
	}

	private void registerControllers() {
		this.letterone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setSelected(1);
			}
		});

		this.lettertwo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setSelected(2);
			}
		});

		this.letterthree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setSelected(3);
			}
		});

		this.letterfour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setSelected(4);
			}
		});

		this.letterfive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				model.setSelected(5);
			}
		});
		this.guess.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt) {
				String newguess = model.getLetter(1) + model.getLetter(2) + model.getLetter(3) +
				         model.getLetter(4) + model.getLetter(5) ;
				model.newGuess(newguess);
				model.clearLetters();
			}
		});
	}

}