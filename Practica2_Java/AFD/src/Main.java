import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main (String... args){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		
		 AFD afd = new AFD();
		 try {
			 System.out.println("Ingresa Sigma: Ejemplo a,b,c ó 0,1");
			 afd.setSigma();
			 System.out.println("Ingresa los estados: Ejemplo q0,q1,q2");
			 afd.setStates();
			 System.out.println("Ingresa los estados finales: Ejemplo q2,q3");
			 afd.setFinalState();
			 System.out.println("*Tabla de transiciones*");
			 afd.setTransitionTable();
			 int counter;
			 System.out.println("Ingresa la cantidad de palabras: ");
			 counter = Integer.parseInt(reader.readLine());
			 String word;
			 do {
				 System.out.println("Ingresa la palabra:");
				 word = reader.readLine();
				 boolean result = afd.search(word);
				 if(result) {
					 System.out.println("La palabra es aceptada por el autómata");
				 }
				 else {
					 System.out.println("La palabra es rechazada por el autómata");
				 }
				 counter --;
			}while(counter > 0);
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
	}
}
