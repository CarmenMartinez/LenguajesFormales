import java.util.*;

public class DFA {
	public int StatesCount;
	public String[] Symbols;
	public boolean[] IsFavorable;
	public int FavorableState;
	public int StartState;
	public Set<Data> Rules;
	
	public DFA() {	
		this.StatesCount = 0;
		this.FavorableState = 0;
		this.StartState = 0;
		Rules = new HashSet<>();
	}
}
