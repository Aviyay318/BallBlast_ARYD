public class Game implements Runnable{
    private GamePanel gamePanel;
    private final int FPS_SEC = 120;
    private final int UPS_SEC = 200;
    private Thread gameThread;
    private  TopPanel topPanel;
    public Game(){
       this.gamePanel = new GamePanel();
       this.topPanel = new TopPanel(Constants.WIDTH,Constants.HEIGHT/18);
       new Frame(this.topPanel ,gamePanel);
        startGameLoop();
    }




    private void update() {
        this.gamePanel.update();
        this.gamePanel.revalidate();

    }


    private void startGameLoop() {
        this.gameThread = new Thread(this);
        this.gameThread.start();
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS_SEC;
        double timePerUpdate = 1000000000.0 / UPS_SEC;
        long previousTime = System.nanoTime();
        int frames = 0;
        int update = 0;
        long lastCheck = System.currentTimeMillis();
        double deltaU = 0;
        double deltaF = 0;
        while (true) {
            long currentTime = System.nanoTime();
            deltaU += (currentTime - previousTime) / timePerUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;
            if (deltaU >= 1) {
                update();
             if (this.gamePanel.gameOver()){
                 this.topPanel.writeScore();
                 break;
             }
                update++;
                deltaU--;
            }
            if (deltaF >= 1) {
                gamePanel.repaint(0);
                frames++;
                deltaF--;

            }
            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: " + frames + " | UPS: " + update);
                frames = 0;
                update = 0;
            }
        }
    }
}
