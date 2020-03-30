package Maestus.Porkyman;

public class HumanPlayer extends Player {

	private final Pockymon pokeball;
	private IItem tool;
	
	public HumanPlayer(Pockymon pokeball, IItem tool) {
		super(true);
		this.pokeball = pokeball;
		this.tool = tool;
	}

	@Override
	public Pockymon getPockymon() {
		return pokeball;
	}

	@Override
	public IItem getItem() {
		if (tool.depleteItem()) {
			tool = new NullItem();
		}
		return tool;
	}
	
	@Override
	public void run() {
		System.out.println("You can't run away from this fight!");
	}

}
