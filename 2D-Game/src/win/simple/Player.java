package win.simple;

public class Player extends Entity {

    /**
     *
     *  玩家类
     *
     */

    private String Name;
    private Location SpanwLocation = null;


    public Player(int X, int Y, int Health, int Size, int Speed, WorldType currentWorld) {
        super(X, Y, currentWorld);
        this.setHealth(Health);
        this.setSize(Size);
        this.setSpeed(Speed);
        this.setEntityType(EntityType.Player);
        this.SpanwLocation = new Location(X, Y);
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public World getWorld() {
        return this;
    }
    
    public void ReturnSpawn() {     //返回重生点
        this.setLocation(this.SpanwLocation.getX(), this.SpanwLocation.getY());
        if(this.getLocation().getX() < 15) {
            getWorld().WorldmoveFront = 0;
        }else {
            getWorld().WorldmoveFront = this.getLocation().getX() - 15;
        }
        if(this.getLocation().getY() < 20) {
            getWorld().WorldmoveHigh = 0;
        }else {
            getWorld().WorldmoveHigh = this.getLocation().getY() - 20;
        }
    }

    public void ScreenFollow() {        //屏幕跟随
        if(this.getLocation().getX() < 15) {
            getWorld().WorldmoveFront = 0;
        }else {
            getWorld().WorldmoveFront = this.getLocation().getX() - 15;
        }
        if(this.getLocation().getY() < 20) {
            getWorld().WorldmoveHigh = 0;
        }else {
            getWorld().WorldmoveHigh = this.getLocation().getY() - 20;
        }
    }
}
