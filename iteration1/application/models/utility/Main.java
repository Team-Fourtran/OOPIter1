package application.models.utility;

import application.models.commands.Command;
import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileInfo.DamageAoE;
import application.models.tileInfo.Decal;
import application.models.tileInfo.HealingAoE;
import application.models.tileInfo.ObstacleItem;
import application.models.tileInfo.OneShotItem;
import application.models.tileState.Map;
import application.models.tileState.TileState;

public class Main {
    public static void main(String args[]) {
    	int length = 4 , width = 4;
        TileGen T = new TileGen(length, width);
        Map m = new Map(T.execute(), length, width);
        m.printOut();

        Player p = new Player();

//        CommandGenerator cGen = new CommandGenerator(p, m);
//        cGen.generateCommand("CS_U001");

//        for(Command c : cGen.generateCommand("MV_U5_T5_T10")){
//            c.execute();
//        }
        
        CommandGenerator cGen = new CommandGenerator(p, m);   
        
        // Adding unit to tile T0
        Command c = cGen.generateCommand("IU_0_T0_colonist").get(0);
        c.execute();
        
        Command c1 = cGen.generateCommand("IU_0_T0_colonist").get(0);
        c.execute();
        
        java.util.Map<String, TileState> t = m.getTiles();
        
        // Testing for tile properties, including Units moving
        t.get("T0").getTileInfo().addAoEs(new DamageAoE(), new HealingAoE());
        t.get("T0").getTileInfo().addItems(new ObstacleItem(), new Decal("crossBones"));
        System.out.println(t.get("T0").getProperties());
        System.out.println(t.get("T1").getProperties());
        System.out.println("Blcked? " + t.get("T0").getTileInfo().isBlocked());
        
        // Move unit 0 from T0 to T1
        c = cGen.generateCommand("MD_u0_0").get(0);
        System.out.println("\nPrevious Contents in T0: " + m.getTileState("T0").getOccupance("u0").getClass().getSimpleName());
        System.out.println("nPrevious Contents in T1: " + m.getTileState("T1").getOccupance("u0"));
        c.execute();
        System.out.println("\nNew Contents in T0: " + m.getTileState("T0").getOccupance("u0"));
        System.out.println("nNew Contents in T1: " + m.getTileState("T1").getOccupance("u0").getClass().getSimpleName());
        
        System.out.println(t.get("T0").getProperties());
        System.out.println(t.get("T1").getProperties());

    }
}