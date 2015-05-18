package jottomodel;

import jottoword.*;
import java.util.ArrayList;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoableEdit;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

public class JottoModel extends Object 
{
    public int numguess = 0;
    private String targetword = "TESTS" ;
    private boolean won = false ;
    private boolean done = false ;
	public static int NUM_LETTERS = 5;
	public static final String[] LEVELS = {
      "Easy", "Medium", "Hard", "Any Difficulty"};
    WordList wordlist = new WordList("jottoword/words.txt");

	/* A list of the model's views. */
	private ArrayList<IView> views = new ArrayList<IView>();
    private boolean DEBUG = false;

    private static String firstletter = "-";
    private static String secondletter = "-";
    private static String thirdletter = "-" ;
    private static String fourthletter = "-" ;
    private static String fifthletter = "-" ;

    private int selectedLetter = 0;

    private int exact = 0;
    private int partial = 0;

    private boolean annot = false ;
    private boolean vowels = false ;
    private boolean freq = false ;
    private boolean giver = false ;

    public class Guess extends Object{
            public String Guess ;
            public int Exact;
            public int Partial;

            public Guess(String guess, int exact, int partial){
                Guess = guess ;
                Exact = exact ;
                Partial = partial ;
            }
            public void printrow(){
                System.out.println("Guess: " + Guess + " exact: " + Exact + " Partial: " + Partial);
            }
    }
    private ArrayList<Guess> guesses = new ArrayList<Guess>() ;

// Override the default construtor, making it private.
	public JottoModel(String word) {
            this.targetword = word ;
    }

	/** Add a new view of this triangle. */
	public void addView(IView view) {
		this.views.add(view);
		view.updateView();
	}

    public String getGuess(){
        return firstletter + secondletter + thirdletter + fourthletter + fifthletter ;
    }

    public boolean colourLetters(){
        return annot;
    }

    public boolean getFreq(){
        return freq ;
    }

    public boolean getVowels(){
        return vowels;
    }

    public boolean getGiver(){
        return giver;
    }

    public void setcolourLetters(boolean b){
        annot = b;
        updateAllViews();
    }

    public void setFreq(boolean b){
        freq = b ;
        updateAllViews();
    }

    public void setVowels(boolean b){
        vowels= b;
        updateAllViews();
    }

    public void setGiver(boolean b){
        giver= b;
        updateAllViews();
    }

	/** Remove a view from this triangle. */
	public void removeView(IView view) {
		this.views.remove(view);
	}

	/** Update all the views that are viewing this triangle. */
	private void updateAllViews() {
		for (IView view : this.views) {
			view.updateView();
		}
	}

    public int getExact(){
        return exact ;
    }

    public int getPartial(){
        return partial ;
    }

    public int getLeft(){
        return 10 - numguess ;
    }
    public void setSelectedLetterText(String letter){
        if(selectedLetter == 1){
            firstletter = letter;
        }
        else if (selectedLetter == 2){
            secondletter = letter;
        }
        else if (selectedLetter == 3){
            thirdletter = letter;
        }
        else if (selectedLetter == 4){
            fourthletter = letter;
        }
        else if (selectedLetter == 5){
            fifthletter = letter;
        }
        updateAllViews();
    }

    public int getSelectedLetter(){
        return selectedLetter ;
    }

    public boolean isSelected(int num){
        return selectedLetter == num;
    }

	public String getLetter(int num){
    	if(num == 1){
    		return firstletter;
    	}
    	else if (num == 2){
    		return secondletter;
    	}
    	else if (num == 3){
    		return thirdletter;
    	}
    	else if (num == 4){
    		return fourthletter;
    	}
    	else return fifthletter;
    }

    public void clearLetters(){
        firstletter = "-" ;
        secondletter = "-" ;
        thirdletter = "-" ;
        fourthletter = "-";
        fifthletter = "-";
        updateAllViews();
    }

    public void setSelected(int num){
    	selectedLetter = num ;
    }

    private int findExact(String guess){
        int e = 0;
        for(int i=0; i<5; i++){
            if(guess.charAt(i) == targetword.charAt(i)) e++;
            if(DEBUG){
                System.out.println(guess.charAt(i) + " " + targetword.charAt(i)) ;
            }
        }
        return e;
    }

    private int findPartial(String guess){
        int p = 0;
        boolean[] arr = {false, false, false, false, false};
        for(int i=0; i<5; i++){
            if(guess.charAt(i) ==targetword.charAt(0) && !arr[0]) {p++;}
            else if(guess.charAt(i) == targetword.charAt(1) && !arr[1]) {p++;}
            else if(guess.charAt(i) == targetword.charAt(2) && !arr[2]){p++;}
            else if(guess.charAt(i) ==targetword.charAt(3) && !arr[3]) {p++;}
            else if(guess.charAt(i) == targetword.charAt(4) && !arr[4]) {p++;}
        }
        return p;
    }

    public void newGuess(String guess){
        if(numguess >= 10) {
            JFrame end = new JFrame("Game Over");
            JOptionPane.showMessageDialog(end,"You have run out of turns! The word was " + targetword);
            return;
        }
        int e = findExact(guess);
        int p = findPartial(guess);
        partial = p-e;
        exact = e ;
        if(e == 5){
            JFrame win = new JFrame("Game Over");
            JOptionPane.showMessageDialog(win,"You have guessed the correct word!");
            return;
        }
        Guess g = new Guess(guess,e,p-e);
        addGuess(g);
        
        numguess++;
        updateAllViews();
    }

    public boolean isVowel(int num){
        return "AEIOU".contains(targetword.substring(num,num+1));
    }

    /*
     * This doesn't do anything; just illustrates that CustomTableModel interface now
     * conforms much more closely to a pure MVC model, unpolluted by the 
     * AbstractTableModel stuff that's just there for one widget.
     */
    public void addGuess(Guess g) {
        this.guesses.add(g);
        atmInstance.fireTableDataChanged();//(this.guesses.size(), this.guesses.size());
    }

    /*
     * Make an instance of an abstract table model to use with JTables.
     */
    private AbstractTableModel atmInstance = new AbstractTableModel(){
        // our data, note we can call these whatever we want
        private String[] myDataColumnNames = {"Guess",
                                        "Exact",
                                        "Partial"};

        // need to define these three methods:
        
        public int getColumnCount() {
            return 3;
        }

        public int getRowCount() {
            return guesses.size();
        }

        public Object getValueAt(int row, int col) {
            switch(col) {
                case 0: return guesses.get(row).Guess; 
                case 1: return guesses.get(row).Exact; 
                case 2: return guesses.get(row).Partial;
            }
            return null;    // for the compiler :(
        }
        
        // define this if you don't want default 'A', 'B', ... names
        public String getColumnName(int col) {
            return myDataColumnNames[col];
        }

        /*
         * JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.
         */
        public Class getColumnClass(int c) {
            return getValueAt(0, c).getClass(); // reflection!
        }

        /*
         * Don't need to implement this method unless your table's
         * editable.
         */
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell appendress is constant,
            //no matter where the cell appears onscreen.
            //(because it's the appendress in the MODEL, not the VIEW)
            return false;
        }

        /*
         * Don't need to implement this method unless your table's
         * data can change.
         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.println("Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");
            }

            switch(col) {
                // columns 0, 1 aren't editable
                case 0: guesses.get(row).Guess = (String)value; break;
                case 1: guesses.get(row).Exact = (Integer)value; break;
                case 2: guesses.get(row).Partial = (Integer)value; break;

            }
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println("New value of data:");
                printDebugData();
            }
        }
    };

    public TableModel getTableModel() {
        return atmInstance;
    }

    private void printDebugData() {
        for(Guess p : this.guesses) {
            System.out.println(p);
        }
        System.out.println("--------------------------");
    }

    public String findWords(){
        IWordPredicate containletters = new IWordPredicate();
        Word[] words = wordlist.getWords(10, containletters, this);
        int length = words.length ;
        String end = "" ;
        for(int i=0; i<length; i++){
            end = end + "\n" + words[i].getWord();
        }
        return end ;
    }

    public String frequency(){
        String guess =this.getGuess();
        int one = 0;
        int two = 0;
        int three = 0;
        int four = 0;
        int five = 0;
        int letters = 0;
        for(int i=0; i<wordlist.numWords(); i++){
            Word w = wordlist.get(i);
            if(w.getWord().contains(guess.substring(0,1))){
                if(DEBUG){System.out.println(one);}
                one++;
            }
            else if(w.getWord().contains(guess.substring(1,2))){
                two++;
            }
            else if(w.getWord().contains(guess.substring(2,3))){
                three++;
            }
            else if(w.getWord().contains(guess.substring(3,4))){
                four++;
            }
            else if(w.getWord().contains(guess.substring(4,5))){
                five++;
            }
            letters++;
        }

        String first = guess.substring(0,1) + ": " + (float) one/letters*100 + "%\n";
        String second = guess.substring(1,2) + ": " + (float) two/letters*100 + "%\n";
        String third = guess.substring(2,3) + ": " + (float) three/letters*100 + "%\n";
        String fourth = guess.substring(3,4) + ": " + (float) four/letters*100 + "%\n";
        String fifth = guess.substring(4,5) + ": " + (float) five/letters*100 + "%\n";
        return first + second + third + fourth + fifth ;
    }

}