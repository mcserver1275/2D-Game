package win.simple.BlockEvent;

import win.simple.Block;
import win.simple.Player;

public class PlayerDestructionBlock {
    private Block block;
    private Player player;
    private boolean stop;

    public PlayerDestructionBlock(Block block, Player player) {
        this.block = block;
        this.player = player;
    }

    public Block getDestructionBlock() {
        return this.block;
    }

    public Player getDestructionBlockPlayer() {
        return this.player;
    }

    public void isStop(boolean stop) {
        this.stop = stop;
    }
}
