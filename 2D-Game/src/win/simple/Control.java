package win.simple;

import win.simple.BlockEvent.BlockEventListener;
import win.simple.BlockEvent.PlaceBlock;
import win.simple.BlockEvent.PlayerDestructionBlock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Control extends Game implements KeyListener {

    /**
     *  控制玩家方向
     *  放置方块破坏方块等操作
     */

    Player player = world.getPlayer();

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()) {
            case KeyEvent.VK_W :            //玩家移动
                if(player.isJump()) {
                    return;
                }
                if((player.getLocation().getY() - player.getSpeed()) > 0 && world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() - 1] == null) {
                    player.stratJump();
                    player.setLocation(player.getLocation().getX(), player.getLocation().getY() - player.getSpeed());
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + player.getSpeed()] = null;
                    player.ScreenFollow();
                }
                break;
            case KeyEvent.VK_A :
                if((player.getLocation().getX() - 1) >= 0 && world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY()] == null) {
                    if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 1] != null) {
                        return;
                    }
                    player.setLocation(player.getLocation().getX() - player.getSpeed(), player.getLocation().getY());
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                    world.WorldGrid[player.getLocation().getX() + player.getSpeed()][player.getLocation().getY()] = null;
                    player.ScreenFollow();

                }
                break;
            case KeyEvent.VK_S :
                if((player.getLocation().getY() + player.getSpeed()) < world.SizeH && world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 1] == null) {
                    if((player.getLocation().getY() + 2) < world.SizeH && world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] != null) {
                        return;
                    }
                    player.setLocation(player.getLocation().getX(), player.getLocation().getY() + player.getSpeed());
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() - player.getSpeed()] = null;
                    player.ScreenFollow();
                }
                break;
            case KeyEvent.VK_D :
                if((player.getLocation().getX() + player.getSpeed()) < world.SizeW && world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY()] == null) {
                    if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 1] != null) {
                        return;
                    }
                    player.setLocation(player.getLocation().getX() + player.getSpeed(), player.getLocation().getY());
                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY()] = player.getEntityType().name();
                    world.WorldGrid[player.getLocation().getX() - player.getSpeed()][player.getLocation().getY()] = null;
                    player.ScreenFollow();

                }
                break;
            case KeyEvent.VK_J :        //设置玩家放置方块
            	switch(player.getDirectionType()) {
            		case Left:
            			if((player.getLocation().getX() - player.getSpeed()) >= 0) {
            			    if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 2] == null) {
                                Block block = new Block(BlockType.Mud, player.getLocation().getX() - 1, player.getLocation().getY() + 2, 30);
            			        if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
            			        break;
                            }

        					if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 1] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX() - 1, player.getLocation().getY() + 1, 30);
                                if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
                				break;
        					}
            				if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY()] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX() - 1, player.getLocation().getY(), 30);
                                if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
                				break;
            				}
            			}
            			break;
            		case Right :
            			if(player.getLocation().getX() + player.getSpeed() < world.SizeW) {
        					if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 2] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX() + 1, player.getLocation().getY() + 2, 30);
                                if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
                				break;
        					}
                            if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 1] == null) {
                                Block block = new Block(BlockType.Mud, player.getLocation().getX() + 1, player.getLocation().getY() + 1, 30);
                                if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
                                break;
                            }
            				if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY()] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX() + 1, player.getLocation().getY(), 30);
                				if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                				}
                				break;
            				}
            			}
            			break;
            		case Upper :
            			if((player.getLocation().getY() - 1) >= 0) {
            				if(world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() - 1] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX(), player.getLocation().getY() - 1, 30);
                                if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                                }
                				break;
            				}
            			}
            			break;
            		case Lower :
            			if((player.getLocation().getY() + 1) < world.SizeH) {
            				if(world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] == null) {
                				Block block = new Block(BlockType.Mud, player.getLocation().getX(), player.getLocation().getY() + 2, 30);
                				if(BlockEventListener.onPlaceBlock(new PlaceBlock(block, player))) {
                                    world.addBlock(block);
                				}
                				break;
            				}
            			}
            			break;
            	}
            	break;
            case KeyEvent.VK_K :
                    switch(player.getDirectionType()) {         //破坏方块
                        case Left:
                            if((player.getLocation().getX() - player.getSpeed()) >= 0) {
                                if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY()] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY())), player))) {
                                        world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY()] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY())));
                                    }
                                    break;
                                }
                                if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 1] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY() + 1)), player))) {
                                        world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 1] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY() + 1)));
                                    }
                                    break;
                                }
                                if(world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 2] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY() + 2)), player))) {
                                        world.WorldGrid[player.getLocation().getX() - 1][player.getLocation().getY() + 2] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() - 1, player.getLocation().getY() + 2)));
                                    }
                                    break;
                                }
                            }
                            break;
                        case Right:
                            if(player.getLocation().getX() + player.getSpeed() < world.SizeW) {
                                if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY()] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY())), player))) {
                                        world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY()] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY())));
                                    }
                                    break;
                                }
                                if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 1] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY() + 1)), player))) {
                                        world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 1] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY() + 1)));
                                    }
                                    break;
                                }
                                if(world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 2] != null) {
                                    if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY() + 2)), player))) {
                                        world.WorldGrid[player.getLocation().getX() + 1][player.getLocation().getY() + 2] = null;
                                        world.removeBlock(world.getBlock(new Location(player.getLocation().getX() + 1, player.getLocation().getY() + 2)));
                                    }
                                    break;
                                }
                            }
                            break;
                        case Upper:
                            if(world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() - 1] != null) {
                                if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX(), player.getLocation().getY() - 1)), player))) {
                                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() - 1] = null;
                                    world.removeBlock(world.getBlock(new Location(player.getLocation().getX(), player.getLocation().getY() - 1)));
                                }
                                break;
                            }
                            break;
                        case Lower:
                            if(world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] != null) {
                                if(BlockEventListener.onPlayerDestructionBlockEvent(new PlayerDestructionBlock(world.getBlock(new Location(player.getLocation().getX(), player.getLocation().getY() + 2)), player))) {
                                    world.WorldGrid[player.getLocation().getX()][player.getLocation().getY() + 2] = null;
                                    world.removeBlock(world.getBlock(new Location(player.getLocation().getX(), player.getLocation().getY() + 2)));
                                }
                                break;
                            }
                            break;
                    }
                break;
            case KeyEvent.VK_Q :
                player.setDirectionType(DirectionType.Left);
                break;
            case KeyEvent.VK_E :
                player.setDirectionType(DirectionType.Right);
                break;
            case KeyEvent.VK_Z :
                player.setDirectionType(DirectionType.Upper);
                break;
            case KeyEvent.VK_X :
                player.setDirectionType(DirectionType.Lower);
                break;
            case KeyEvent.VK_ESCAPE :
                System.exit(0);
                break;
            case KeyEvent.VK_HOME :
                player.ReturnSpawn();
                break;
            case KeyEvent.VK_F3 :
                if(player.getWorld().DisPlayGameState) {
                    player.getWorld().DisPlayGameState = false;
                }else {
                    player.getWorld().DisPlayGameState = true;
                }
                break;
                
                
                
            case KeyEvent.VK_UP :
            	break;
            case KeyEvent.VK_DOWN :
            	break;
            case KeyEvent.VK_LEFT :
            	break;
            case KeyEvent.VK_RIGHT :
            	break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }


}
