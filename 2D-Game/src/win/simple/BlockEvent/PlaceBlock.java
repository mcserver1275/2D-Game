package win.simple.BlockEvent;

import win.simple.Block;
import win.simple.Player;

public class PlaceBlock {
    private Block block;
    private Player player;

    public PlaceBlock(Block block, Player player) {
        this.block = block;
        this.player = player;
    }

    public Block getPlaceBlock() {
        return this.block;
    }

    public Player getPlaceBlockPlayer() {
        return this.player;
    }
}
