package de.szut.dqi14.gahr.E2.WinterHell;

import java.awt.*;

abstract class Snowflake {

    float mX;
    float mY;
    float mSpeed;
    String design;

    public void paint(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString(this.design, (int)mX, (int)mY);
        mY += mSpeed;
    }

	public float getY(){
        return mY;
    }

    public float getX() {
        return mX;
    }
}
