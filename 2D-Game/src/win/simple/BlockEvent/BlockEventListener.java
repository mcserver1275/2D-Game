package win.simple.BlockEvent;

import win.simple.BlockType;

public class BlockEventListener {

    /**
     *
     *  方块事件
     *
     */

    public static boolean onPlayerDestructionBlockEvent(PlayerDestructionBlock e) {     //破坏方块事件
        if(e.getDestructionBlock().getBlockType().equals(BlockType.Bedrock)) {
            return false;
        }
        return true;
    }

    public static boolean onPlaceBlock(PlaceBlock e) {      //放置方块事件
        return true;
    }
}
