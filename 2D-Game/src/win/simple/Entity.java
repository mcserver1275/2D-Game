package win.simple;

public class Entity extends World {

    /**
     *
     *  实体类
     *
     */

    private int Health;
    private EntityType entityType;
    private DirectionType directionType = DirectionType.Left;
    private WorldType currentWorld;
    private Location location;
    private int Speed = 1;
    private int Size = 30;

    private boolean isJump = false;

    public Entity(int X, int Y, WorldType currentWorld) {
        this.location = new Location(X, Y);
        this.currentWorld = currentWorld;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(int X, int Y) {
        this.location.setLocation(X, Y);
        WorldGrid[X][Y] = this.getDirectionType().name();
    }
    public void setLocation(Location location) { this.location = location; }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        this.Health = health;
    }

    public int getSpeed() {
        return Speed;
    }

    public void setSpeed(int Speed) {
        this.Speed = Speed;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int Size) {
        this.Size = Size;
    }

    public EntityType getEntityType() {
        return entityType;
    }

    public void setEntityType(EntityType entityType) {
        this.entityType = entityType;
    }

    public DirectionType getDirectionType() {
        return directionType;
    }

    public void setDirectionType(DirectionType directionType) {
        this.directionType = directionType;
    }

    public boolean isJump() {
        return isJump;
    }

    public void stratJump() {
        this.isJump = true;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                isJump = false;
            }
        }).start();
    }
}
