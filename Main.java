//package jotto;

import javax.swing.JFrame;

import jottomodel.*;
import jottoview.*;
import jottoword.*;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.BorderFactory;


public class Main {

	public static void main(String[] args) {
		WordList wordlist = new WordList("jottoword/words.txt");
		Word word = wordlist.randomWord();

		JottoModel model = new JottoModel(word.getWord());
		GuessWordView view = new GuessWordView(model);
		AlphabetBox alphabet = new AlphabetBox(model);
		GuessTableView guesses = new GuessTableView(model);
		LastGuessStats lastguess = new LastGuessStats(model);
		HintsView hints = new HintsView(model);
		FindWordView find = new FindWordView(model);
		FrequencyView freq = new FrequencyView(model);

		JFrame frame = new JFrame("Jotto");
		frame.getContentPane().setLayout(new BorderLayout());
		
		// Guess Section
		JPanel guesssection = new JPanel();
		guesssection.setLayout(new BoxLayout(guesssection, BoxLayout.PAGE_AXIS));

		guesssection.add(view);
		guesssection.add(find);
		guesssection.add(freq);
		guesssection.add(alphabet);
		guesssection.add(lastguess);

		guesssection.setBorder(BorderFactory.createTitledBorder("Guess a Word"));

		// Guesses Table
		JPanel guesstable = new JPanel();
		guesstable.setLayout(new BorderLayout());
		guesstable.add(guesses);
		guesstable.add(guesses.table.getTableHeader(), BorderLayout.PAGE_START);
		guesstable.setBorder(BorderFactory.createTitledBorder("Previous Guesses"));
		
		// Hints
		hints.setBorder(BorderFactory.createTitledBorder("Hints"));

		frame.getContentPane().add(hints, BorderLayout.SOUTH);
		frame.getContentPane().add(guesssection, BorderLayout.EAST);
		frame.getContentPane().add(guesstable, BorderLayout.WEST);

		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);



		//DemoPanel north = new DemoGridLayout();
		//DemoPanel east = new DemoGridLayout();
		//DemoPanel west = new DemoBoxLayout3();	
		
	}
}