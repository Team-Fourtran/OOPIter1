package application.views;

import javax.swing.*;
import application.controllers.KeyPressInformer;
import application.models.playerAsset.PlayerAsset;
import application.models.tileState.Map;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.border.Border;

public class MainScreen{
    private JFrame mainScreen;
    private JPanel areaViewPort;
    private JPanel statusViewPort;
    private JPanel buttonPanel;
    private JPanel statusTablePanel;
    private JButton unitOVButton;
    private JButton structureOVButton;
    private JTable statusTable;
    
    public Object[][] unitData;
    public Object[][] structData;
    
    private final int ROW = 15;
    private final int COL = 15;
    private final int PIXELS = 45;
    private final int prefWidth = COL * PIXELS;
    private final int prefHeight = ROW * PIXELS;

    //Iterator
    public ListIterator unitIterator;
    public ListIterator structureIterator;
    public ListIterator armyIterator;

    //KeyPressInformer for Controller
    private KeyPressInformer keyInformer;
    private HashMap<String, Boolean> keyList;

    private JLabel[][] Grid;
    private Map map;
    private final ImageIcon NORMAL = new ImageIcon("iteration1/TileImages/Normal/Normal.png");
    private final ImageIcon NORMAL_COLONIST = new ImageIcon("iteration1/TileImages/Normal/Normal-Colonist.png");
    private final ImageIcon NORMAL_EXPLORER = new ImageIcon("iteration1/TileImages/Normal/Normal-Explorer.png");
    private final ImageIcon NORMAL_MELEE = new ImageIcon("iteration1/TileImages/Normal/Normal-MeleeUnit.png");
    private final ImageIcon NORMAL_RANGED = new ImageIcon("iteration1/TileImages/Normal/Normal-RangedUnit.png");
    private final ImageIcon NORMAL_STRUCTURE = new ImageIcon("iteration1/TileImages/Structure/Normal-Structure.png");
    private final ImageIcon NORMAL_ARMY = new ImageIcon("iteration1/TileImages/Normal/Normal-Army.png");
    private final ImageIcon SLOW_ARMY = new ImageIcon("iteration1/TileImages/Normal/Slow-Army.png");
    private final ImageIcon SLOW = new ImageIcon("iteration1/TileImages/Slow/Slow.png");
    private final ImageIcon SLOW_COLONIST = new ImageIcon("iteration1/TileImages/Slow/Slow-Colonist.png");
    private final ImageIcon SLOW_EXPLORER = new ImageIcon("iteration1/TileImages/Slow/Slow-Explorer.png");
    private final ImageIcon SLOW_MELEE = new ImageIcon("iteration1/TileImages/Slow/Slow-MeleeUnit.png");
    private final ImageIcon SLOW_RANGED = new ImageIcon("iteration1/TileImages/Slow/Slow-RangedUnit.png");
    private final ImageIcon SLOW_STRUCTURE = new ImageIcon("iteration1/TileImages/Structure/Slow-Structure.png");

    private final ImageIcon IMPASSABLE = new ImageIcon("iteration1/TileImages/Impassable/Impassable.png");
    private final ImageIcon[] TERRAIN = {
            NORMAL,
            SLOW,
            IMPASSABLE
    };

    private final String[] unitColumnStats = {"Player Resource", "Offensive Damage",
            "Defensive Damage", "Armor", "Movement",
            "Health", "Upkeep"};
    
    public MainScreen(Map map, ListIterator unitIterator, ListIterator structureIterator, ListIterator armyIterator){
    	this.map = map;
    	this.unitIterator = unitIterator;
    	this.structureIterator = structureIterator;
    	this.armyIterator = armyIterator;
    }
    public void showMainScreen(){
        mainScreen.setVisible(true);
    }
    public KeyPressInformer getKeyInformer(){
        return keyInformer;
    }
    public void generateMainScreen(){
        mainScreen = new JFrame("Fourtran Game");
        Grid = new JLabel[ROW][COL];

        unitData = new Object[25][9];
        structData = new Object[25][9];
        
        int z = 0;
        
        String[] unitTypeArr = new String[ROW * COL];
        String[] structTypeArr = new String[ROW * COL];
        String[] armyTypeArr = new String[ROW * COL];
        Arrays.fill(unitTypeArr, "");
        Arrays.fill(structTypeArr, "");
        Arrays.fill(armyTypeArr, "");
        
        while(unitIterator.hasNext()){
			PlayerAsset asset = (PlayerAsset) unitIterator.next();
			unitData[z][0] = asset.getID();
			unitData[z][1] = asset.getType();
			unitData[z][2] = asset.getOffDamage();
			unitData[z][3] = asset.getDefDamage();
			unitData[z][4] = asset.getArmor();
			unitData[z][5] = asset.getMaxHealth();
			unitData[z][6] = asset.getCurrentHealth();
			unitData[z][7] = asset.getUpkeep();
			unitData[z][8] = asset.getLocation();
			String str = asset.getLocation().replaceAll("\\D+","");
			int location = Integer.parseInt(str);
			unitTypeArr[location] = asset.getType();
			if(z == 24){
				z = 0;
			}
			else{
				z++;
			}
		}

        z = 0;
        while(structureIterator.hasNext()){
			PlayerAsset asset = (PlayerAsset) structureIterator.next();
			structData[z][0] = asset.getID();
			structData[z][1] = asset.getType();
			structData[z][2] = asset.getOffDamage();
			structData[z][3] = asset.getDefDamage();
			structData[z][4] = asset.getArmor();
			structData[z][5] = asset.getMaxHealth();
			structData[z][6] = asset.getCurrentHealth();
			structData[z][7] = asset.getUpkeep();
			structData[z][8] = asset.getLocation();
			
			String str = asset.getLocation().replaceAll("\\D+","");
			int location = Integer.parseInt(str);
			structTypeArr[location] = asset.getType();
			if(z == 24){
				z = 0;
			}
			else{
				z++;
			}
		}
        z = 0;
//        while(armyIterator.hasNext()){
//            PlayerAsset asset = (PlayerAsset) armyIterator.next();
//            String str = asset.getLocation().replaceAll("\\D+","");
//            int location = Integer.parseInt(str);
//            armyTypeArr[location] = asset.getType();
//            if(z == 24){
//                z = 0;
//            }
//            else{
//                z++;
//            }
//        }
        while(unitIterator.hasPrevious()){
            unitIterator.previous();
        }
        while(structureIterator.hasPrevious()){
            structureIterator.previous();
        }
//        while(armyIterator.hasPrevious()){
//            armyIterator.previous();
//        }
        
        String terrains2d[][] = new String[ROW][COL];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                terrains2d[i][j] = map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
            }
        }
        
        
        for(int i = 0; i < 15; i++){
        	for(int j = 0; j < 15; j++){
        		if(unitTypeArr[i*(ROW) + j].toString().toUpperCase().equals("COLONIST")){
            		if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
            			terrains2d[i][j] = "NORMALCOLONIST";
            		} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
            			terrains2d[i][j] = "SLOWCOLONIST";
            		}
            	} else if(unitTypeArr[i*(ROW) + j].toString().toUpperCase().equals("EXPLORER")){
            		if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
            			terrains2d[i][j] = "NORMALEXPLORER";
            		} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
            			terrains2d[i][j] = "SLOWEXPLORER";
            		}
            	} else if(unitTypeArr[i*(ROW) + j].toString().toUpperCase().equals("MELEE")){
            		if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
            			terrains2d[i][j] = "NORMALMELEE";
            		} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
            			terrains2d[i][j] = "SLOWMELEE";
            		}
            	} else if(unitTypeArr[i*(ROW) + j].toString().toUpperCase().equals("RANGED")){
            		if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
            			terrains2d[i][j] = "NORMALRANGED";
            		} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
            			terrains2d[i][j] = "SLOWRANGED";
            		}
            	}
        	}
        }
        for(int i = 0; i < 15; i++){
        	for(int j = 0; j < 15; j++){
        		if(structTypeArr[i*(ROW) + j].toString().toUpperCase().equals("STRUCTURE")){
            		if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
            			terrains2d[i][j] = "NORMALSTRUCTURE";
            		} else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
            			terrains2d[i][j] = "SLOWSTRUCTURE";
            		}
            	} 
        	}
        }
//        for(int i = 0; i < 15; i++){
//            for(int j = 0; j < 15; j++){
//                if(armyTypeArr[i*(ROW) + j].toUpperCase().equals("ARMY")){
//                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
//                        terrains2d[i][j] = "NORMALARMY";
//                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
//                        terrains2d[i][j] = "ARMYSTRUCTURE";
//                    }
//                }
//            }
//        }
        //Initializing the Grid of JLabels.
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                    Grid[i][j].setIcon(TERRAIN[0]);
                } else if(terrains2d[i][j].equals("NORMALARMY")) {
                    Grid[i][j].setIcon(NORMAL_ARMY);
                } else if(terrains2d[i][j].equals("SLOWARMY")) {
                    Grid[i][j].setIcon(SLOW_ARMY);
                } else if(terrains2d[i][j].equals("NORMALCOLONIST")){
                    Grid[i][j].setIcon(NORMAL_COLONIST);
                } else if(terrains2d[i][j].equals("NORMALEXPLORER")){
                    Grid[i][j].setIcon(NORMAL_EXPLORER);
                } else if(terrains2d[i][j].equals("NORMALMELEE")){
                    Grid[i][j].setIcon(NORMAL_MELEE);
                } else if(terrains2d[i][j].equals("NORMALRANGED")){
                    Grid[i][j].setIcon(NORMAL_RANGED);
                } else if(terrains2d[i][j].toUpperCase().equals("IMPASSABLE")){
                    Grid[i][j].setIcon(TERRAIN[2]);
                } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                    Grid[i][j].setIcon(TERRAIN[1]);
                } else if(terrains2d[i][j].equals("SLOWCOLONIST")){
                    Grid[i][j].setIcon(SLOW_COLONIST);
                } else if(terrains2d[i][j].equals("SLOWEXPLORER")){
                    Grid[i][j].setIcon(SLOW_EXPLORER);
                } else if(terrains2d[i][j].equals("SLOWMELEE")){
                    Grid[i][j].setIcon(SLOW_MELEE);
                } else if(terrains2d[i][j].equals("SLOWRANGED")){
                    Grid[i][j].setIcon(SLOW_RANGED);
                } else if(terrains2d[i][j].equals("NORMALSTRUCTURE")){
                    Grid[i][j].setIcon(NORMAL_STRUCTURE);
                } else if(terrains2d[i][j].equals("SLOWSTRUCTURE")){
                    Grid[i][j].setIcon(SLOW_STRUCTURE);
                } else{
                    Grid[i][j].setIcon(TERRAIN[0]);
                }
            }
        }

        //Initializing areaViewPort with Grid.
        areaViewPort = new JPanel(new GridLayout(ROW, COL));
        areaViewPort.setPreferredSize(new Dimension(prefWidth, prefHeight));
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                areaViewPort.add(Grid[i][j]);
            }
        }

        //Adding KeyListener for areaViewPort.
        keyList = new HashMap<String, Boolean>();
        keyList.put("UP", false);
        keyList.put("DOWN", false);
        keyList.put("LEFT", false);
        keyList.put("RIGHT", false);
        keyList.put("CONTROL", false);
        keyList.put("ENTER", false);

        keyInformer = new KeyPressInformer(keyList);

        areaViewPort.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e){}
            @Override
            public void keyReleased(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyInformer.update("RIGHT", false);
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    keyInformer.update("LEFT", false);
                } else if(e.getKeyCode() == KeyEvent.VK_UP){
                    keyInformer.update("UP", false);
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    keyInformer.update("DOWN", false);
                } else if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                    keyInformer.update("CONTROL", false);
                } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyInformer.update("ENTER", false);
                }
            }
            @Override
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                    keyInformer.update("RIGHT", true);
                } else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                    keyInformer.update("LEFT", true);
                } else if(e.getKeyCode() == KeyEvent.VK_UP){
                    keyInformer.update("UP", true);
                } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                    keyInformer.update("DOWN", true);
                } else if(e.getKeyCode() == KeyEvent.VK_CONTROL){
                    keyInformer.update("CONTROL", true);
                } else if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    keyInformer.update("ENTER", true);
                }
            }
        });
        areaViewPort.setFocusable(true);
        areaViewPort.requestFocusInWindow();
        
        /*Selecting tiles
        areaViewPort.addMouseListener(new MouseListener(){
	         Border redBorder = BorderFactory.createLineBorder(Color.RED,5);
        	 @Override
             public void mouseEntered(MouseEvent e)
             {}
             @Override
             public void mouseExited(MouseEvent e)
             {}
        	 @Override public void mouseClicked(MouseEvent e){}
             @Override public void mousePressed(MouseEvent e){
            	 areaViewPort.setBorder(redBorder);
             }
             @Override public void mouseReleased(MouseEvent e){}
        });*/
        
        //Initializing Status View Port.
        statusViewPort = new JPanel(new BorderLayout());

        //Initializing buttons.
        buttonPanel = new JPanel();
        unitOVButton = new JButton("Unit Overview");
        structureOVButton = new JButton("Structure Overview");
        unitOVButton.setActionCommand("openUnitOV");
        structureOVButton.setActionCommand("openStructOV");
        unitOVButton.addActionListener(new MainScreenAction(unitData, structData));
        structureOVButton.addActionListener(new MainScreenAction(unitData, structData));
        buttonPanel.add(unitOVButton);
        buttonPanel.add(structureOVButton);

        NonEditableTable table = new NonEditableTable(unitData, unitColumnStats);
        statusTable = new JTable(table);
        statusTablePanel = new JPanel();
        statusTablePanel.add(new JScrollPane(statusTable), BorderLayout.CENTER);
        statusViewPort.add(buttonPanel, BorderLayout.NORTH);
        statusViewPort.add(statusTablePanel);

        //Finalizing Main Screen
        mainScreen.add(areaViewPort);
        mainScreen.add(statusViewPort, BorderLayout.EAST);
        mainScreen.setSize(500, 500);
        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainScreen.pack();
    
    }

    public void renderMainScreen(){
        int z = 0;
        String[] unitTypeArr = new String[ROW * COL];
        String[] structTypeArr = new String[ROW * COL];
        String[] armyTypeArr = new String[ROW * COL];
        Arrays.fill(unitTypeArr, "");
        Arrays.fill(structTypeArr, "");
        while(unitIterator.hasNext()){
            PlayerAsset asset = (PlayerAsset) unitIterator.next();
            unitData[z][0] = asset.getID();
            unitData[z][1] = asset.getType();
            unitData[z][2] = asset.getOffDamage();
            unitData[z][3] = asset.getDefDamage();
            unitData[z][4] = asset.getArmor();
            unitData[z][5] = asset.getMaxHealth();
            unitData[z][6] = asset.getCurrentHealth();
            unitData[z][7] = asset.getUpkeep();
            unitData[z][8] = asset.getLocation();
            String str = asset.getLocation().replaceAll("\\D+","");
            int location = Integer.parseInt(str);
            unitTypeArr[location] = asset.getType();
            if(z == 24){
                z = 0;
            }
            else{
                z++;
            }
        }

        z = 0;
        while(structureIterator.hasNext()){
            PlayerAsset asset = (PlayerAsset) structureIterator.next();
            structData[z][0] = asset.getID();
            structData[z][1] = asset.getType();
            structData[z][2] = asset.getOffDamage();
            structData[z][3] = asset.getDefDamage();
            structData[z][4] = asset.getArmor();
            structData[z][5] = asset.getMaxHealth();
            structData[z][6] = asset.getCurrentHealth();
            structData[z][7] = asset.getUpkeep();
            structData[z][8] = asset.getLocation();

            String str = asset.getLocation().replaceAll("\\D+","");
            int location = Integer.parseInt(str);
            structTypeArr[location] = asset.getType();
            if(z == 24){
                z = 0;
            }
            else{
                z++;
            }
        }
        z = 0;
//        while(armyIterator.hasNext()){
//            PlayerAsset asset = (PlayerAsset) armyIterator.next();
//            String str = asset.getLocation().replaceAll("\\D+","");
//            int location = Integer.parseInt(str);
//            armyTypeArr[location] = asset.getType();
//            if(z == 24){
//                z = 0;
//            }
//            else{
//                z++;
//            }
//        }
        while(unitIterator.hasPrevious()){
            unitIterator.previous();
        }
        while(structureIterator.hasPrevious()){
            structureIterator.previous();
        }
        while(armyIterator.hasPrevious()){
            structureIterator.previous();
        }

        String terrains2d[][] = new String[ROW][COL];
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                terrains2d[i][j] = map.getTiles().get(("T"+ String.valueOf((j*15) + i))).getProperties().get("terrain").get(0);
            }
        }


        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(unitTypeArr[i*(ROW) + j].toUpperCase().equals("COLONIST")){
                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                        terrains2d[i][j] = "NORMALCOLONIST";
                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                        terrains2d[i][j] = "SLOWCOLONIST";
                    }
                } else if(unitTypeArr[i*(ROW) + j].toUpperCase().equals("EXPLORER")){
                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                        terrains2d[i][j] = "NORMALEXPLORER";
                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                        terrains2d[i][j] = "SLOWEXPLORER";
                    }
                } else if(unitTypeArr[i*(ROW) + j].toUpperCase().equals("MELEE")){
                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                        terrains2d[i][j] = "NORMALMELEE";
                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                        terrains2d[i][j] = "SLOWMELEE";
                    }
                } else if(unitTypeArr[i*(ROW) + j].toUpperCase().equals("RANGED")){
                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                        terrains2d[i][j] = "NORMALRANGED";
                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                        terrains2d[i][j] = "SLOWRANGED";
                    }
                }
            }
        }
        for(int i = 0; i < 15; i++){
            for(int j = 0; j < 15; j++){
                if(structTypeArr[i*(ROW) + j].toUpperCase().equals("STRUCTURE")){
                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                        terrains2d[i][j] = "NORMALSTRUCTURE";
                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                        terrains2d[i][j] = "SLOWSTRUCTURE";
                    }
                }
            }
        }
//        for(int i = 0; i < 15; i++){
//            for(int j = 0; j < 15; j++){
//                if(armyTypeArr[i*(ROW-1) + j].toUpperCase().equals("ARMY")){
//                    if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
//                        terrains2d[i][j] = "NORMALARMY";
//                    } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
//                        terrains2d[i][j] = "ARMYSTRUCTURE";
//                    }
//                }
//            }
//        }
        //Initializing the Grid of JLabels.
        for(int i = 0; i < ROW; i++){
            for(int j = 0; j < COL; j++){
                if(terrains2d[i][j].toUpperCase().equals("NORMAL")){
                    Grid[i][j].setIcon(TERRAIN[0]);
                } else if(terrains2d[i][j].equals("NORMALARMY")) {
                    Grid[i][j].setIcon(NORMAL_ARMY);
                } else if(terrains2d[i][j].equals("SLOWARMY")) {
                    Grid[i][j].setIcon(SLOW_ARMY);
                } else if(terrains2d[i][j].equals("NORMALCOLONIST")){
                    Grid[i][j].setIcon(NORMAL_COLONIST);
                } else if(terrains2d[i][j].equals("NORMALEXPLORER")){
                    Grid[i][j].setIcon(NORMAL_EXPLORER);
                } else if(terrains2d[i][j].equals("NORMALMELEE")){
                    Grid[i][j].setIcon(NORMAL_MELEE);
                } else if(terrains2d[i][j].equals("NORMALRANGED")){
                    Grid[i][j].setIcon(NORMAL_RANGED);
                } else if(terrains2d[i][j].toUpperCase().equals("IMPASSABLE")){
                    Grid[i][j].setIcon(TERRAIN[2]);
                } else if(terrains2d[i][j].toUpperCase().equals("SLOWING")){
                    Grid[i][j].setIcon(TERRAIN[1]);
                } else if(terrains2d[i][j].equals("SLOWCOLONIST")){
                    Grid[i][j].setIcon(SLOW_COLONIST);
                } else if(terrains2d[i][j].equals("SLOWEXPLORER")){
                    Grid[i][j].setIcon(SLOW_EXPLORER);
                } else if(terrains2d[i][j].equals("SLOWMELEE")){
                    Grid[i][j].setIcon(SLOW_MELEE);
                } else if(terrains2d[i][j].equals("SLOWRANGED")){
                    Grid[i][j].setIcon(SLOW_RANGED);
                } else if(terrains2d[i][j].equals("NORMALSTRUCTURE")){
                    Grid[i][j].setIcon(NORMAL_STRUCTURE);
                } else if(terrains2d[i][j].equals("SLOWSTRUCTURE")){
                    Grid[i][j].setIcon(SLOW_STRUCTURE);
                } else{
                    Grid[i][j].setIcon(TERRAIN[0]);
                }
            }
        }

//        //Initializing areaViewPort with Grid.
//        areaViewPort = new JPanel(new GridLayout(ROW, COL));
//        areaViewPort.setPreferredSize(new Dimension(prefWidth, prefHeight));
//        for(int i = 0; i < ROW; i++){
//            for(int j = 0; j < COL; j++){
//                areaViewPort.add(Grid[i][j]);
//            }
//        }
//
//        mainScreen.add(areaViewPort);
////        mainScreen.update(areaViewPort.getGraphics());
//        mainScreen.add(statusViewPort, BorderLayout.EAST);
//        mainScreen.setSize(500, 500);
//        mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        mainScreen.pack();
//        mainScreen.setVisible(false);
//        mainScreen.revalidate();
//        mainScreen.repaint();
//        mainScreen.setVisible(true);
    }
}

