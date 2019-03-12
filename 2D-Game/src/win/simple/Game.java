package win.simple;

import win.simple.worlds.WorldStructure;

import java.awt.*;

public class Game extends Frame {

    /**
     *
     *  初始化游戏
     *
     */


    public static final World world = new World(1000, 700, WorldType.Default);

    public static void GameEnable() {

        new WorldStructure(WorldType.Default);

        Block block = new Block(BlockType.Mud, 8, 22, 30);
        world.addBlock(block);

        Block block1 = new Block(BlockType.Mud, 7, 21, 30);
        world.addBlock(block1);

        Block block5 = new Block(BlockType.Stone, 6, 22, 30);
        world.addBlock(block5);

        Block block2 = new Block(BlockType.Stone, 7, 22, 30);
        world.addBlock(block2);

        Block block3 = new Block(BlockType.Stone, 9, 22, 30);
        world.addBlock(block3);

        Block block4 = new Block(BlockType.Sand, 8, 5, 30);
        world.addBlock(block4);

        Block block7 = new Block(BlockType.Sand, 27, 5, 30);
        world.addBlock(block7);

        Block block8 = new Block(BlockType.Sand, 10, 4, 30);
        world.addBlock(block8);

        Block block6 = new Block(BlockType.Stone, 10, 22, 30);
        world.addBlock(block6);

        Player player = new Player(10, 640, 697, 30, 1, WorldType.Default);
        world.addEntity(player);
    }
}
