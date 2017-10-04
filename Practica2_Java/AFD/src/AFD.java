
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AFD {
	private String sigmaIn, statesIn, finalStatesIn;
	private String[] sigma, states, finalStates;
	private static String[][] transitionTable; 
	private  BufferedReader reader;

	public AFD() {
		reader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void setSigma() throws IOException {
		sigmaIn = reader.readLine();
		sigma = separate(sigmaIn);
	}
	
    public void setStates() throws IOException{
    		statesIn = reader.readLine();
    		states = separate(statesIn);
    }
    
    public void setFinalState() throws IOException {
    		finalStatesIn  = reader.readLine();
    		finalStates = separate(finalStatesIn);
    }
	
	public void setTransitionTable() throws IOException {
		int sigmaSize = sigma.length;
		int statesSize = states.length;
	    transitionTable = new String[sigmaSize][statesSize];
	    for(int r = 0; r < sigmaSize; r ++) {
		   for(int c = 0; c < statesSize; c ++) {
			   System.out.print("Ingresa " + states[c] + " "  + sigma[r] + " ");
			   transitionTable[r][c] = reader.readLine();
		   }
	    }
	}
	
	public int getPositionSigma(String caracter) {
		for(int i = 0; i < sigma.length; i ++) {
			if(sigma[i].equals(caracter)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPositionStates(String state) {
		for(int i = 0; i < states.length; i ++) {
			if(states[i].equals(state)) {
				return i;
			}
		}
		return -1;
	}
	
	public int getPositionFinalStates(String state) {
		for(int i = 0; i < finalStates.length; i ++) {
			if(finalStates[i].equals(state)) {
				return i;
			}
		}
		return -1;
	}
	
	private String[] separate(String str) {
		return str.split(",");
	}
	
	public boolean search(String word) {
		String state = states[0];
		int counter = 0;
		int indexSigma = 0, indexStates = 0;
		while(counter < word.length()) {
			indexSigma = getPositionSigma(Character.toString(word.charAt(counter)));
			if(indexSigma == -1) {
				return false;
			}
			indexStates = getPositionStates(state);
			state = transitionTable[indexSigma][indexStates];
			counter ++;
		}
		if(getPositionFinalStates(state) > -1) {
			return true;
		}
		return false;
	}
	
	
}
