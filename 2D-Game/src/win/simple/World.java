package win.simple;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class World {

    /**
     *
     *  负责添加实体，方块，获取实体，方块等功能
     *  以及绘画图形
     *
     */

    private static List<Block> blocks = new ArrayList<>();
    private static List<Entity> entities = new ArrayList<>();
    public static int SizeW, SizeH;
    public WorldType worldType;
    public static String[][] WorldGrid;

    public static int WorldmoveFront = 0;
    public static int WorldmoveHigh = 0;

    public static boolean DisPlayGameState = true;
    public static int TPS;
    public static int _TPS;

    private static ImageIcon EntityPlayerImage = null;

    static {
        //清空TPS，重新计算
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                _TPS = TPS;
                TPS = 0;
            }
        }, 0, 1000);
    }

    public World() { }

    public World(int SizeW, int SizeH, WorldType worldType) {
        this.SizeW = SizeW;
        this.SizeH = SizeH;
        this.worldType = worldType;
        this.WorldGrid = new String[SizeW][SizeH];

        new Thread(new PlayerDrooping()).start();     //玩家掉落
        new Thread(new EntityDrooping()).start();       //实体掉落
        new Thread(new BlockState()).start();       //检测方块状态
    }

    public void addBlock(Block block) {
        WorldGrid[block.getLocation().getX()][block.getLocation().getY()] = block.getBlockType().name();
        blocks.add(block);
    }

    public boolean addEntity(Entity entity) {
        boolean tianjia = true;
        for(Entity shiti : entities) {
            if(shiti.getDirectionType().name().equals(EntityType.Player.name())) {
                System.out.println("已经存在玩家，不能重复添加！");
                tianjia = false;
            }
        }
        if(tianjia) {
            WorldGrid[entity.getLocation().getX()][entity.getLocation().getY()] = entity.getEntityType().name();
            entities.add(entity);
        }
        return tianjia;
    }

    public Block getBlock(Location location) {
        for(Block block : blocks) {
            if(block.getLocation().getX() == location.getX()) {
                if(block.getLocation().getY() == location.getY()) {
                    return block;
                }
            }
        }
        return null;
    }

    public void removeBlock(Block block) {
        blocks.remove(block);
    }

    public Player getPlayer() {
        for(Entity entity : entities) {
            if(entity.getEntityType().name().equals("Player")) {
                return (Player) entity;
            }
        }
        return null;
    }

    public List<Entity> getEntitys() {
        return entities;
    }

    public void draw(Graphics graphics) {       //绘制
        //底图
        Color color = null;
        switch (worldType) {
            case Default :
                color = graphics.getColor();
                graphics.setColor(new Color(135, 206, 250));
                graphics.fillRect(0,0, SizeW, SizeH);
                break;
        }

        ImageIcon blockImage = null;
        ImageIcon EntityImage = null;
        Block block = null;

        int OldRight = 0;
        int OldTop = 0;
        for(int X = WorldmoveFront; X < SizeW; X++) {
            for(int Y = WorldmoveHigh; Y < SizeH; Y++) {
                String type = WorldGrid[X][Y];
                if(type != null) {
                    switch (type) {
                        case "Mud" :
                            blockImage = new ImageIcon("block//dirt.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Stone" :
                            blockImage = new ImageIcon("block//stone.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Sand" :
                            blockImage = new ImageIcon("block//sand.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Bedrock" :
                            blockImage = new ImageIcon("block//bedrock.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Grass_Side" :
                            blockImage = new ImageIcon("block//grass_side.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Wood_Oak" :
                            blockImage = new ImageIcon("block//log_oak.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;
                        case "Oak_Leaves" :
                            blockImage = new ImageIcon("block//leaves.png");
                            block = this.getBlock(new Location(X, Y));
                            graphics.drawImage(blockImage.getImage(), OldTop, OldRight, blockImage.getImageObserver());
                            OldRight += block.getSize();
                            break;



                        case "Player" :
                            for(Entity entity : entities) {
                                if(entity.getEntityType().name().equals("Player")) {
                                    switch(entity.getDirectionType()) {
                                        case Left:
                                            EntityPlayerImage = new ImageIcon("block//steve_left.bmp");
                                            break;
                                        case Right:
                                            EntityPlayerImage = new ImageIcon("block//steve_right.bmp");
                                            break;
                                        case Upper:
                                            break;
                                        case Lower:
                                    }
                                    graphics.drawImage(EntityPlayerImage.getImage(), OldTop, OldRight, EntityPlayerImage.getImageObserver());
                                    OldRight += entity.getSize();
                                    break;
                                }
                            }
                            break;
                        case "Player_lower" :
                            EntityImage = new ImageIcon("block//grass_side.png");
                            for(Entity entity : entities) {
                                if(entity.getEntityType().name().equals("Player_lower")) {
                                    graphics.drawImage(EntityImage.getImage(), OldTop, OldRight, EntityImage.getImageObserver());
                                    OldRight += entity.getSize();
                                    break;
                                }
                            }
                            break;
                    }
                }else {
                    OldRight += 30;
                }
            }
            OldRight = 0;
            OldTop += 30;
        }

        graphics.setColor(color);

        if(DisPlayGameState) {
            Graphics2D graphics2D = (Graphics2D) graphics;
            graphics2D.setColor(Color.BLACK);
            graphics2D.setFont(new Font("微软雅黑", Font.PLAIN, 15));
            graphics2D.drawString("FPS：" + _TPS, 10, 50);
            graphics2D.drawString("X:" + getPlayer().getLocation().getX() + " Y:" + getPlayer().getLocation().getY(), 10, 70);
        }
        TPS++;
    }

    private class PlayerDrooping implements Runnable {      //玩家浮空掉落
        @Override
        public void run() {
            boolean loop = true;

            while(loop) {
                for(Entity entity : entities) {
                    if(entity.getEntityType().equals(EntityType.Player)) {
                        Location location = entity.getLocation();
                        if((location.getY() + 1) < SizeH && WorldGrid[location.getX()][location.getY() + 1] == null) {
                            if(entity.isJump()) {
                                try {
                                    Thread.sleep(300);
                                    loop = false;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                Player player = (Player) entity;
                                if((player.getLocation().getY() + player.getSpeed()) < SizeH && WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] == null) {
                                    player.setLocation(player.getLocation().getX(), player.getLocation().getY() + player.getSpeed());
                                    WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                                    WorldGrid[player.getLocation().getX()][player.getLocation().getY() - player.getSpeed()] = null;

                                    player.ScreenFollow();
                                }
                            }else {
                                try {
                                    Thread.sleep(90);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }

                                if(!entity.isJump()) {
                                    Player player = (Player) entity;
                                    if((player.getLocation().getY() + player.getSpeed()) < SizeH && WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] == null) {
                                        player.setLocation(player.getLocation().getX(), player.getLocation().getY() + player.getSpeed());
                                        WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                                        WorldGrid[player.getLocation().getX()][player.getLocation().getY() - player.getSpeed()] = null;

                                        player.ScreenFollow();
                                    }
                                }
                            }
                        }
                    }
                }
            }

            new Thread(new PlayerDrooping()).start();     //玩家掉落
        }
    }

    private class EntityDrooping implements Runnable {      //实体浮空掉落
        @Override
        public void run() {
            boolean loop = true;

            while(loop) {
                for(Entity entity : entities) {
                    if(!entity.getEntityType().equals(EntityType.Player)) {
                        if((entity.getLocation().getY() + 1) < SizeH && WorldGrid[entity.getLocation().getX()][entity.getLocation().getY() + 1] == null) {
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            entity.setLocation(entity.getLocation().getX(), entity.getLocation().getY() + 1);
                            WorldGrid[entity.getLocation().getX()][entity.getLocation().getY()] = entity.getEntityType().name();
                            WorldGrid[entity.getLocation().getX()][entity.getLocation().getY() - 1] = null;
                        }
                    }
                }
            }
        }
    }

    private class BlockState implements Runnable {      //检测方块状态，即特殊方块操作
        @Override
        public void run() {
            boolean loop = true;
            while(loop) {


                for(int i = 0; i < blocks.size(); i++) {
                    Block block = blocks.get(i);
                    if(block.getBlockType().equals(BlockType.Sand)) {       //沙子掉落
                        if(WorldGrid[block.getLocation().getX()][block.getLocation().getY() + 1] == null) {
                            block.setLocation(block.getLocation().getX(), block.getLocation().getY() + 1);;
                            WorldGrid[block.getLocation().getX()][block.getLocation().getY()] = BlockType.Sand.name();
                            WorldGrid[block.getLocation().getX()][block.getLocation().getY() - 1] = null;

                        }
                    }


                }

                try {
                    Thread.sleep(90);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
