package win.simple.worlds;

import win.simple.Block;
import win.simple.BlockType;
import win.simple.Game;
import win.simple.WorldType;

public class WorldStructure extends Game {

    /**
     *
     *  生成世界
     *
     */

    public WorldStructure(WorldType worldType) {
        new Thread(new RegionLoad(worldType)).start();
        /*
        switch(worldType) {
            case Default:

                new DefaultWorld(this.world);
                break;
        }
        */
    }

}
