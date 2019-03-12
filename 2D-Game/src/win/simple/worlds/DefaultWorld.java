package win.simple.worlds;

import win.simple.*;

import java.util.Random;

public class DefaultWorld {

    /**
     *
     *  默认主世界
     *  基岩层 -> 1 ~ 2
     *  石头层 -> 2 ~ 18
     *  泥土层 -> 19 ~ 36
     *  草方块层 -> 37
     *  树木 -> 38
     *
     */

    private World world;
    private Player player;
    private Random random = new Random();

    public DefaultWorld(World world, Player player) {
        this.world = world;
        this.player = player;
    }

    public void StartSpawn(int start, int end) {
        SpawnBedrockLayer(start, end);
        SpawnStoneLayer(start, end);
        SpawnDirtLayer(start, end);
        SpawnGrass_SideLayer(start, end);
        SpawnTree_Oak(start, end);
    }

    public void SpawnBedrockLayer(int start, int end) {        //生成基岩层
        for(int X = start; X <= end; X++) {
            if(X > (world.SizeW - 1) || X < 0) {
                return;
            }
            world.addBlock(new Block(BlockType.Bedrock, X, world.SizeH - 1, 30));
            if(this.random.nextInt(10) == 1) {
                if(world.getBlock(new Location(X, world.SizeH - 2)) == null || world.getBlock(new Location(X, world.SizeH - 3)) == null) {
                    world.addBlock(new Block(BlockType.Bedrock, X, world.SizeH - 2, 30));
                }
            }
        }
    }

    public void SpawnStoneLayer(int start, int end) {     //生成石头层
        int startLayer = 2;
        int height = 18;
        for(int X = end; X >= start; X--) {
            for(int Y = world.SizeH - startLayer; Y >= world.SizeH - height; Y--) {
                if(world.getBlock(new Location(X, Y)) == null) {
                    world.addBlock(new Block(BlockType.Stone, X, Y, 30));
                }
            }
        }
    }

    public void SpawnDirtLayer(int start, int end) {        //生成泥土层
        int startLayer = 19;
        int height = 36;
        for(int X = end; X >= start; X--) {
            for(int Y = world.SizeH - startLayer; Y >= world.SizeH - height; Y--) {
                if(world.getBlock(new Location(X, Y)) == null) {
                    world.addBlock(new Block(BlockType.Mud, X, Y, 30));
                }
            }
        }
    }

    public void SpawnGrass_SideLayer(int start, int end) {      //生成草方块层
        int startLayer = 37;
        int height = 37;
        for(int X = end; X >= start; X--) {
            for(int Y = world.SizeH - startLayer; Y >= world.SizeH - height; Y--) {
                if(world.getBlock(new Location(X, Y)) == null) {
                    world.addBlock(new Block(BlockType.Grass_Side, X, Y, 30));
                }
            }
        }
    }

    public void SpawnTree_Oak(int start, int end) {     //生成橡树
        int startLayer = 38;
        int height = 38;
        for(int X = end; X >= start; X--) {
            for(int Y = world.SizeH - startLayer; Y >= world.SizeH - height; Y--) {
                if(world.getBlock(new Location(X, Y)) == null) {
                    if(random.nextInt(100) == 1) {
                        world.addBlock(new Block(BlockType.Wood_Oak, X, Y, 30));
                        world.addBlock(new Block(BlockType.Wood_Oak, X, Y - 1, 30));
                        world.addBlock(new Block(BlockType.Wood_Oak, X, Y - 2, 30));

                        world.addBlock(new Block(BlockType.Stone, X, Y - 3, 30));
                        world.addBlock(new Block(BlockType.Stone, X, Y - 4, 30));
                        if((X + 1) <= world.SizeH) {
                            world.addBlock(new Block(BlockType.Stone, X + 1, Y - 2, 30));
                            world.addBlock(new Block(BlockType.Stone, X + 1, Y - 3, 30));
                        }
                        if((X + 2) <= world.SizeH) {
                            world.addBlock(new Block(BlockType.Stone, X + 2, Y - 2, 30));
                        }
                        if((X - 1)>= 0) {
                            world.addBlock(new Block(BlockType.Stone, X - 1, Y - 2, 30));
                            world.addBlock(new Block(BlockType.Stone, X - 1, Y - 3, 30));
                        }
                        if((X - 2)>= 0) {
                            world.addBlock(new Block(BlockType.Stone, X - 2, Y - 2, 30));
                        }

                    }
                }
            }
        }
    }


}
