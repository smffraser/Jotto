package jottoview;

//import IView;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Color;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

import javax.swing.BorderFactory;

import jottomodel.*;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.text.NumberFormat;


public class LastGuessStats extends JPanel {
	private JottoModel model ;

	private JLabel exact = new JLabel("0");
	private JLabel partial = new JLabel("0");
	private JLabel left = new JLabel("10");

	public LastGuessStats(JottoModel amodel){
		super();
		this.model = amodel ;

		this.layoutView();

		this.model.addView(new IView() {
			public void updateView() {
				exact.setText(Integer.toString(model.getExact()));
				partial.setText(Integer.toString(model.getPartial()));
				left.setText(Integer.toString(model.getLeft()));
			}

		});
	}

	public void layoutView(){


		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		JPanel previousguess = new JPanel();
		previousguess.setLayout(new GridLayout());
		previousguess.setBorder(BorderFactory.createTitledBorder("Your Previous Guess:"));


		JPanel exactpanel = new JPanel();

		exactpanel.setLayout(new GridBagLayout());
		GridBagConstraints gc = new GridBagConstraints();
		gc.anchor = GridBagConstraints.CENTER;
		gc.gridheight = 3;
		exactpanel.add(this.exact);
		exactpanel.setBorder(BorderFactory.createTitledBorder("Exact:"));


		previousguess.add(exactpanel);

		JPanel partialpanel = new JPanel();

		partialpanel.setLayout(new GridBagLayout());
		GridBagConstraints gc2 = new GridBagConstraints();
		gc2.gridheight = 3;
		gc2.anchor = GridBagConstraints.CENTER;
		partialpanel.add(this.partial);
		partialpanel.setBorder(BorderFactory.createTitledBorder("Partial:"));

		previousguess.add(partialpanel);
		this.add(previousguess);


		JPanel leftpanel = new JPanel();
		leftpanel.setLayout(new GridBagLayout());
		GridBagConstraints gc3 = new GridBagConstraints();
		gc3.anchor = GridBagConstraints.CENTER;
		gc3.gridheight = 3;
		leftpanel.add(this.left);
		leftpanel.setBorder(BorderFactory.createTitledBorder("Guesses Remaining:"));

		this.add(leftpanel);
	}

		private Box groupComponents(JLabel up, JLabel dn) {
		Box group = Box.createHorizontalBox();
		group.add(up);
		group.add(dn);

		Dimension d = up.getPreferredSize();
		d.width = Math.max(up.getPreferredSize().width,
				dn.getPreferredSize().width);
		up.setPreferredSize(d);
		dn.setPreferredSize(d);

		return group;
	}

}
