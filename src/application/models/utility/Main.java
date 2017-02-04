package application.models.utility;

import application.models.commands.CommandGenerator;
import application.models.playerAsset.Player;
import application.models.tileInfo.DamageAoE;
import application.models.tileInfo.Decal;
import application.models.tileInfo.HealingAoE;
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

        CommandGenerator generator = new CommandGenerator();
        generator.generateCommand("CS_U001");

        java.util.Map<String, TileState> t = m.getTiles();
        
        t.get("T0").getTileInfo().addAoEs(new DamageAoE(), new HealingAoE());
        t.get("T0").getTileInfo().addItems(new OneShotItem(), new Decal("crossBones"));
        System.out.println(t.get("T0").getProperties());
    }
}