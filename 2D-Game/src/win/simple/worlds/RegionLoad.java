package win.simple.worlds;

import win.simple.*;

import java.util.ArrayList;
import java.util.List;

public class RegionLoad extends Game implements Runnable{
    /**
     *  ver 0.0.1
     *  区块加载器,卸载器
     *
     *  生成区块范围：
     *      - 玩家前面， 玩家脚下， 玩家后面
     *
     *  问题：
     *      - 加载区块特别慢
     *
     */

    private int RegionRange = 10;        //区块宽度
    private WorldType worldType;
    private List<Integer> RegionSpacing = new ArrayList<>();

    public RegionLoad(WorldType worldType) {
        this.worldType = worldType;
    }

    @Override
    public void run() {
        World world = this.world;
        Player player = world.getPlayer();
        DefaultWorld defaultWorld = new DefaultWorld(world, player);

        int old = 0;
        for(int X = 0; X <= (world.SizeW - 1); X++) {
            if(old <= X) {
                this.RegionSpacing.add(old);
                old += RegionRange;
            }
        }

        while(true) {
            int playerStart = 0, playerEnd = 0;
            int FrontStart = 0, FrontEnd = 0;
            int afterStart = 0, afterEnd = 0;

            int OldplayerStart = 0, OldplayerEnd = 0;
            int OldFrontStart = 0, OldFrontEnd = 0;
            int OldafterStart = 0, OldafterEnd = 0;

            int Region;
            for(Region = 0; Region <= this.RegionSpacing.size(); Region++) {
                if(this.RegionSpacing.get(Region) >= player.getLocation().getX()) {
                    break;
                }
            }

            //加载玩家前面的区块
            if((Region - 2) >= 0) {
                FrontStart = this.RegionSpacing.get(Region - 2);
            }
            if((Region - 1) >= 0) {
                FrontEnd = this.RegionSpacing.get(Region - 1);
            }

            //加载玩家脚下区块
            if((Region - 1) >= 0) {
                playerStart = this.RegionSpacing.get(Region - 1);
            }
            playerEnd = this.RegionSpacing.get(Region);

            //加载玩家后面的区块
            afterStart = this.RegionSpacing.get(Region);
            if((Region + 1) <= this.RegionSpacing.size()) {
                afterEnd = this.RegionSpacing.get(Region + 1);
            }


            defaultWorld.StartSpawn(playerStart, playerEnd);
            defaultWorld.StartSpawn(FrontStart, FrontEnd);
            defaultWorld.StartSpawn(afterStart, afterEnd);

            uninstallRegion(FrontStart, afterEnd);
        }

    }

    /**
     *  uninstallRegion
     *  除 newstartFront， newendbehind 之间的区块其余卸载
     *
     */
    public void uninstallRegion(int newstartFront,  int newendbehind) {
        for(int X = 0; X <= this.world.SizeW - 1; X++) {
            for(int Y = 0; Y <= this.world.SizeH - 1; Y++) {
                int FinalRange = newstartFront + newendbehind;
                if(X < newstartFront || X > newendbehind) {
                    if(X < 0) {
                        return;
                    }
                    if( X > (this.world.SizeW - 1)) {
                        return;
                    }
                    world.removeBlock(world.getBlock(new Location(X, Y)));
                    this.world.WorldGrid[X][Y] = null;

                }
            }
        }

    }
}
