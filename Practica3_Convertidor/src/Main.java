import java.io.*;
import java.util.*;

public class Main {
	public static void main(String...strings) {
	DFA dfa = readInput();

	//Dictionary<Pair, bool> pairs = setupMarkedPairs(dfa); 
	//processPairs(dfa, pairs);

	//int[] e_class = createEqClasses(dfa, pairs);
	
	//outputResults(dfa, pairs, e_class);
	
	}
	
	public static DFA readInput() {
		
		DFA dfa = new DFA();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	    Scanner scanner = new Scanner(System.in);

		try {
			//Se lee la cantidad de estados que el automáta tiene
		    System.out.print("Ingresa la cantidad de estados que el autmáta posee: ");
			dfa.StatesCount = scanner.nextInt();
			
			//Se leen los elementos del alfabeto
			System.out.print("Ingresa los elementos del alfabeto separado por espacios : ");
			String symbols = reader.readLine();
			dfa.Symbols = symbols.split(" ");
			
			//Se lee a lista de los estados finales
			System.out.println("Ingresa la lista de los estados finales, separados por espacios: ");
			String states = reader.readLine(); 
			String[] statesList = states.split(" ");
			dfa.IsFavorable = new boolean[dfa.StatesCount + 1];
			for(String i : statesList) {
				dfa.IsFavorable[Integer.parseInt(i)] = true;
			}
			System.out.print("Inserta el estado inicial: ");
			dfa.StartState = scanner.nextInt();
			dfa.FavorableState = Integer.parseInt(statesList[0]);
			System.out.println("Transiciones:");
			System.out.println("Estado  simbolo  nuevo estado");
			String s;
			while((s = reader.readLine()) != "") {
				if(s.isEmpty())
					break;
				String[] tr = s.split(" ");
				LeftSide aux = new LeftSide(Integer.parseInt(tr[0]),tr[1]);
				dfa.Rules.add(new Data(aux,Integer.parseInt(tr[2])));
			}		
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*
		1 0 2 
		1 1	3
		2 0	1
		2 1	3
		3 0	4
		3 1 4
		4 0	4
		4 1 4*/
		
		return dfa;
	}
	
	public void outputResults(DFA dfa, Set<Pair> pairs, int[] eClass) {
		
		Dictionary<Integer, Boolean> states = new Hashtable<Integer, Boolean>();
		
		for(int state = 1; state <= dfa.StatesCount; ++state) {
			states.put(eClass[state], true);
		}
		Dictionary<String, Boolean> rules = new Hashtable<String, Boolean>();
		
		for(Data rule : dfa.Rules){
			String strRule = "(" + eClass[rule.leftS.State] + "," +
					rule.leftS.Symbol + ") -> " + eClass[rule.stateResult];
			rules.put(strRule, true);
		}
		
		//Comienza a imprimir
		
		System.out.println("Estados: ");
		Enumeration<Integer> keyStates = states.keys();
		while(keyStates.hasMoreElements()){
			System.out.print(keyStates.nextElement() + " ");
		}
		System.out.println();
		
		//Estado inicial
		System.out.println("Estado Inicial : " + eClass[dfa.StartState]);
		System.out.println("Estados Finales: " + eClass[dfa.FavorableState]);
		
		//Transiciones
		Enumeration<String> keyRules = rules.keys();
		while(keyRules.hasMoreElements()){
			System.out.print(keyRules.nextElement());
		}
		System.out.println();
	}
	
	
}
