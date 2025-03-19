class Ship {
    private String name;
    private int size;
    private int hitCount;
    private boolean isSunk;

    public Ship(String name, int size) {
        this.name = name;
        this.size = size;
        this.hitCount = 0;
        this.isSunk = false;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public int getHitCount() {
        return hitCount;
    }

    public boolean isSunk() {
        return isSunk;
    }

    public void hit() {
        hitCount++;
        if (hitCount >= size) {
            isSunk = true;
        }
    }
}