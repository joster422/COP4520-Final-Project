import java.util.ArrayList;
import java.util.Random;

/**
 * Node class used to store information about each minimax position
 * @author Cindy
 *
 */
public class Node
{
	private ArrayList<Node> _children;
	private Node 			_parent;
	private int 			_negamaxScore;
	private int 			_minimaxScore; 
	private double 			_distribution;
	private int 			_num_expanded;
	
	// these variables will only be initialized for leaf nodes
	private Board 			_board;
	private char 			_player;
	private int 			_position;
	
	// used for distribution method
	private boolean NORMAL = 		true;
	private boolean EXPONENTIAL = 	false;
	private boolean CHISQUARE = 	false;
	
	/**
	 * This constructor is only called when the end state of minimax node is not reached, and must branch off.
	 * Thus, certain variables will be initialized later using set methods
	 * @param parent - of current node
	 */
	public Node(Node parent){
		_distribution = 0;
		_minimaxScore = 0;
		_parent = parent;
		_num_expanded = 0;
		_negamaxScore = 0;
	}
	
	/**
	 * This constructor is called when an end state of a minimax node is reached
	 * @param parent - of current node
	 * @param score - minimax score
	 * @param position - position that player has moved to
	 */
	public Node(Node parent, int score, int position){
		_negamaxScore = 0;
		_minimaxScore = score;
		_position = position;
		_parent = parent;
		_num_expanded = 0;
	}
	
	/**
	 * Negamax score of a node is the maximum of the negated minimax score of its children
	 * @return negamax score
	 */
	public int getNegaMaxScore(){
		int max = Integer.MIN_VALUE;
		for (Node child : _children){
			max = Math.max(max, -child.getScore());
		}
		return max;
	}
	
	/**
	 * Needed for distribution method. Is called whenever a node is expanded
	 * A leaf node is the only one that can be expanded. Expanding a node
	 * means generating children of the leaf node and evalulating them by
	 * assigning them minimax scores. This particular process is done in the RBFM class
	 */
	public void expanded(){
		_num_expanded++;
	}
	
	/**
	 * @return random child of parent node, any move
	 */
	public Node choose_random_child()
	{
		Random r = new Random();
		int randomIndex = r.nextInt(_children.size());
		return _children.get(randomIndex);
	}
	
	/**
	 * @return best child is defined as the best move
	 */
	public Node best_child(){
		double min=Double.MAX_VALUE;
		Node best = null;
		for (Node n : _children)
		{
			if (n._distribution < min)
			{
				min = n._distribution;
				best=n;
			}
		}
		return best;
	}
	
	// not done :(
	/**
	 * Distribution method used to estimate node distribution
	 */
	public void calculateDistribution()
	{
		
	}
	
	public void setParent(char p){
		_player = p;
	}
	
	public char getPlayer(){
		return _player;
	}
	
	public void setChildren(ArrayList<Node> children){
		this._children = children;
	}
	
	public void setScore(int score){
		_minimaxScore = score;
	}
	
	public int getPosition(){
		return _position;
	}
	
	public void setPosition(int position){
		_position = position;
	}
	
	public void addChild(Node child){
		_children.add(child);
	}
	
	public boolean isLeaf(){
		return (_children.size() == 0);
	}
	
	public void setBoard(Board b){
		_board = b;
	}
	
	public void setPlayer(char p){
		_player = p;
	}
	
	public Board getBoard(){
		return _board;
	}
	public int getScore(){
		return _minimaxScore;
	}
	
	public double getDistribution(){
		return _distribution;
	}
	
	public Node getParent(){
		return _parent;
	}
	
}