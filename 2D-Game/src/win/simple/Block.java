package win.simple;

public class Block extends World{
    private BlockType blockType;
    private int X = 0;
    private int Y = 0;
    private int Size = 0;
    private Location location;

    public Block(BlockType blockType, int X, int Y, int Size) {
        this.blockType = blockType;
        this.X = X;
        this.Y = Y;
        this.Size = Size;
        this.location = new Location(X, Y);
    }

    public BlockType getBlockType() {
        return blockType;
    }

    public int getSize() {
        return Size;
    }

    public void setLocation(int X, int Y) {
        this.X = X;
        this.Y = Y;
        this.location.setLocation(X, Y);
        WorldGrid[X][Y] = this.blockType.name();
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setType(BlockType blockType) {
        this.blockType = blockType;
    }
}
