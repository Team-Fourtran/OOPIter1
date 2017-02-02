import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Map extends JPanel{

	public static final Color NORMAL = new Color(102,153,0);
	public static final Color SLOWING = new Color(222,184,135);
	public static final Color IMPASSABLE = new Color(0,0,0);
	public static final Color FIELD = new Color(255,204,102);
	public static final Color SWAMP = new Color(0,102,0);
	public static final Color WATER = new Color(0,0,153);

	public static final int ROW = 30;
	public static final int COL = 30;

	public static final int PIXELS = 15;

	public static final Color[] TERRAIN = {
		NORMAL,
		SLOWING,
		IMPASSABLE,
		FIELD,
		SWAMP,
		WATER
	};

	private final Color[][] Grid;

	public Map(){
		this.Grid = new Color[ROW][COL];
		Random r = new Random();

		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				int randomTerrainIndex = r.nextInt(TERRAIN.length);
				Color randomColor = TERRAIN[randomTerrainIndex];
				this.Grid[i][j] = randomColor;
			}
		}
		int prefWidth = COL * PIXELS;
		int prefHeight = ROW * PIXELS;
		setPreferredSize(new Dimension(prefWidth, prefHeight));
	}

	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//Clears the board
		g.clearRect(0,0, getWidth(), getHeight());
		// Drawing the grid
		int rectWidth = getWidth() / COL;
		int rectHeight = getHeight() / ROW;

		for(int i = 0; i < ROW; i++){
			for(int j = 0; j < COL; j++){
				int x = i * rectWidth;
				int y = j * rectHeight;
				Color terrainColor = Grid[i][j];
				g.setColor(terrainColor);
				g.fillRect(x, y, rectWidth, rectHeight);
			}
		}
	}

	public static void main(String[] args){
		SwingUtilities.invokeLater(new Runnable(){
			public void run() {
				JFrame mapView = new JFrame("Fourtran Game");
				mapView.setSize(500, 500);
				JFrame unitOverView = new JFrame("Unit Overview");
				JFrame structureOverView = new JFrame("Structure Overview");
				JMenuBar menuBar = new JMenuBar();
				JMenu optionMenu = new JMenu("Options");
				//optionMenu.setMnemonic(KeyEvent.VK_F);
				menuBar.add(optionMenu);
				Map map = new Map();
				mapView.add(map);
				mapView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				mapView.pack();
				mapView.setVisible(true);
				JButton unitOVButton = new JButton("Unit Overview");
				JButton structureOVButton = new JButton("Structure Overview");
				unitOVButton.setBounds(20, 20, 10, 10);
				structureOVButton.setBounds(24, 20, 10, 10);
				mapView.add(unitOVButton);
				mapView.add(structureOVButton);
			}
		});
	}
}