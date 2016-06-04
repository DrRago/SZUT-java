package de.szut.dqi14.gahr.WinterHell;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

class WinterScene extends JFrame {

	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	
	// potential utf8-snowflakes: ❄❅❆❇❉❊❄❅❆✼✲✳❄❅❆

	public static void main(String[] args) {
		WinterScene scene = new WinterScene();
		scene.setVisible(true);
		scene.setExtendedState(scene.getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
	
	private WinterScene() {
		super();
		//noinspection MagicConstant
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new WinterPanel());
        pack();
	}
	
	public class WinterPanel extends JPanel implements ActionListener, MouseListener {

		@SuppressWarnings("unused")
		private static final long serialVersionUID = 1L;
		
		private final ArrayList<Snowflake> mSnowFlakes;
		private final Random mRandom;
		private final Timer mTimer;

		public WinterPanel() {
			addMouseListener(this);
            setResizable(false);
			mSnowFlakes = new ArrayList<>();
			mRandom = new Random();
			mTimer = new Timer(40, this);
			
			setBackground(new Color(0, 0, 255));
			
			mTimer.start();
		}

		/*public Dimension getPreferredSize() {

		}*/

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			
			cleanUpSnow();
			generateSnow();
			paintSnow(g);
		}

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == mTimer) {
				repaint();			
			}
		}
		
		private void cleanUpSnow() {
			Iterator<Snowflake> iterator = mSnowFlakes.iterator();
			while (iterator.hasNext()) {
				Snowflake snowFlake = iterator.next();
				if (snowFlake.getY() > (getHeight() + 10)) {
					iterator.remove();
				}
			}
		}
		
		private void generateSnow() {
			while (mSnowFlakes.size() < 1000) {
				int random = mRandom.nextInt(4);
				int random2 = mRandom.nextInt(4);
				if (random == 0 || random == 3) {
					float x = 10.0f + mRandom.nextInt(getWidth() - 20);
					float y = -10.0f - mRandom.nextInt(getHeight());
					float speed = 1.0f + (mRandom.nextInt(10) / 20.0f);
					BigSnowflake snowFlake = new BigSnowflake(x, y, speed);
					mSnowFlakes.add(snowFlake);
				} else if (random == 1){
                    float x = 10.0f + mRandom.nextInt(getWidth() - 20);
                    float y = -10.0f - mRandom.nextInt(getHeight());
                    float speed = 1.0f + (mRandom.nextInt(10) / 20.0f);
					LittleSnowflake snowFlake = new LittleSnowflake(x, y, speed);
					mSnowFlakes.add(snowFlake);
				}
				else if (random == 2 && random2 == 2){
					float x = 10.0f + mRandom.nextInt(getWidth() - 20);
					float y = -10.0f - mRandom.nextInt(getHeight());
					float speed = 10.0f + (mRandom.nextInt(10) / 20.0f);
					Hagel snowFlake = new Hagel(x, y, speed);
					mSnowFlakes.add(snowFlake);
				}
			}
		}
		
		private void paintSnow(Graphics g) {
			for (Snowflake snowFlake : mSnowFlakes) {
				snowFlake.paint(g);
			}
		}

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            Iterator<Snowflake> iterator = mSnowFlakes.iterator();
            while (iterator.hasNext()) {
                Snowflake snowflake = iterator.next();
                if (Math.abs(x - snowflake.getX() + 5) < 10 && Math.abs(y - snowflake.getY() + 10) < 10){
                    iterator.remove();
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}