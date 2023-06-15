package game;

public class Blocks {
    float x,y,w,h;

    public Blocks(float x, float y, float w, float h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public boolean isCollide(float xp, float yp){
        if (xp <= x+w && yp <= y+h && xp>= x && yp>= y){
            return true;
        }else {
            return false;
        }
    }
}
