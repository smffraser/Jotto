package jottoview;

import jottomodel.*;

import javax.swing.*;
import java.awt.event.*;
import java.text.NumberFormat;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Color;

/*
 * View a triangle as text:  numbers for the base, height, and hypotenuse.
 * This view leaves a lot to be desired in terms of "polish".
 * -- inconsistent decimal precision displayed, esp. in hypotenuse
 * -- tabbing or clicking out of a text field doesn't update
 * -- can edit the hypotenuse field
 * 
 * @author Byron Weber Becker
 */
public class AlphabetBox extends JPanel implements IView {
	private JottoModel model;
	private JButton[] letterarray = new JButton[26] ;
	private boolean[] clicked = new boolean[26];

	MouseListener listener = new MouseListener() {
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() instanceof JButton) {
            	JButton button = (JButton) e.getSource();
            	int index = button.getText().charAt(0) - 65;
            	if(e.getButton() == 3){

            		if(!clicked[index] && model.colourLetters()){
						button.setForeground(Color.RED);
						clicked[index] = true ;
					}
					else if(clicked[index] || !model.colourLetters()){
						button.setForeground(Color.BLACK);
						clicked[index] = false ;
					}
            	}
            	else if (e.getButton() == 1){
                	String text = ((JButton) e.getSource()).getText();
                	model.setSelectedLetterText(text);
            	}
            }
        }
        public void mouseExited(MouseEvent e){}
        public void mouseEntered(MouseEvent e){
        	
        }
        public void mousePressed(MouseEvent e){}
        public void mouseReleased(MouseEvent e){}
    };

	public AlphabetBox(JottoModel aModel) {
		super();
		char currletter = 'A' ;
		for(int i=0; i<26; i++){
			letterarray[i] = new JButton(Character.toString(currletter));
			JButton button  = letterarray[i] ;
			button.setOpaque(false);
			button.setContentAreaFilled(false);
			button.setBorderPainted(false);
			button.addMouseListener(listener);
			currletter++;
		}
		this.model = aModel;
		this.layoutView();

		// Add a this view as a listener to the model
		this.model.addView(this);
	}


	/**
	 * What to do when the model changes.
	 */
	public void updateView() {
		for(int i=0; i<26; i++){
			JButton button  = letterarray[i] ;
			
		}
	}

	private void layoutView() {
		this.setLayout(new GridLayout(5,6));
		for(int i=0; i<26; i++){
			this.add(letterarray[i]);
		}
		Dimension d = letterarray[0].getPreferredSize();
		d.width = letterarray[0].getPreferredSize().width;
		d.height = letterarray[0].getPreferredSize().height;
		for(int i=0; i<26; i++){
			letterarray[i].setPreferredSize(d);
		}
	}

}