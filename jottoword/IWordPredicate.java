package jottoword;

import jottomodel.*;

/** Classes implementing <class>IWordPredicate</code> are used to find out
 * whether or not a given word meets some criteria determined by the
 * implementation of {@link #isOK(Word w)}.
 *
 * @author Byron Weber Becker */
public class IWordPredicate 
{

	public IWordPredicate(){};

   /** Does the given Word meet a condition?
    * @param w The word-difficulty object to test
    * @return true if it meets the condition;  false otherwise. */
   public boolean isOK(Word w, JottoModel model){
        String guess = model.getGuess();
        if(w.getWord().contains(guess.substring(0,1)) &&
            w.getWord().contains(guess.substring(1,2)) &&
            w.getWord().contains(guess.substring(2,3)) &&
            w.getWord().contains(guess.substring(3,4)) &&
            w.getWord().contains(guess.substring(4,5))){
            return true;
        }
        else return false ;
    }
}
