import java.lang.reflect.Array;
import java.util.*;
import javax.swing.*;

/**
 * Created by Jesse on 8/3/2016.
 */
public class Mastermind {
    int[] code = new int[4];
    Random ran = new Random();

    public int[] makeCode() {
        int newInt, codeCount = 4;
        newInt = ran.nextInt(6);
        code[3] = newInt;
        codeCount = 3;
        while (codeCount > 0) {
            newInt = ran.nextInt(6);
            code[codeCount - 1] = newInt;
            codeCount--;
        }
        return code;
    }

    public static void main(String[] args) {
        Mastermind m = new Mastermind();
        int[] gameCode = m.makeCode(), availbleObj = {0, 1, 2, 3, 4, 5};
        int[] guessArray = new int[4];
        boolean runningMastermind = true;
        String[] options = {"Red", "Blue", "Green", "Orange", "Purple", "White"};
        String pastGuessStr = "";

        int guess, guessNum = 0, guesses = 10, fullCor = 0, partCor = 0;
        ArrayList<Integer> GC = new ArrayList();
        startLoop:
        while (runningMastermind) {
            System.out.println(Arrays.toString(gameCode));
            while (guesses > 0) {
                GC.clear();

                while (guessNum < 4) {
                    guess = JOptionPane.showOptionDialog(null, "Past guesses:\n" + pastGuessStr+ "\nYou have " + guesses + " guesses remaining.\nWhat is your guess?", "Guess",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                    guessArray[guessNum] = guess;
                    guessNum++;
                    if (guess == -1) {
                        break startLoop;
                    }
                }
                for (int i = 0; i < guessArray.length; i++){
                    pastGuessStr += options[guessArray[i]] + " ";
                }
                pastGuessStr += "\n";
                guesses--;
                guessNum = 0;

                if (Arrays.equals(guessArray, gameCode)) {
                    JOptionPane.showMessageDialog(null, "You Win!", "Winner!", JOptionPane.INFORMATION_MESSAGE);
                    guesses = 0;
                    break startLoop;
                }
                fullCor = 0;
                partCor = 0;
                for(int i = 0; i < 4; i++) {
                    GC.add(gameCode[i]);
                }
                for(int i = 0; i < 4; i++) {
                    if (guessArray[i] == GC.get(i)) {
                        fullCor++;
                        GC.remove(i);
                        GC.add(i, 9);
                    }
                }
                for(int i = 0; i < 4; i++){
                    if(GC.contains(guessArray[i])) {
                        partCor++;
                        int j =GC.indexOf(guessArray[i]);
                        GC.remove(j);
                        GC.add(i,9);
                    }
                }
                JOptionPane.showMessageDialog(null, "Correct color and position: " + fullCor + "\nCorrect color: " + partCor);

            }
            JOptionPane.showMessageDialog(null, "You Lose.");
            runningMastermind = false;
        }
    }
}