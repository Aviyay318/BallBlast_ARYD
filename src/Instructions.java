import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Instructions extends JPanel {
    private JLabel blast;
    private JLabel smallBlast;
    private JLabel ball;
    private JLabel smallBall;
    private JLabel highScore;
    private JLabel moveInstructions;

    private BufferedImage crown;
    private BufferedImage [] leftKey;
    private BufferedImage [] rightKey;

    private JButton start;
    private Icon startIcon;
    private boolean isStart;
    private int index;

    private Sound music;

    public Instructions(int x,int y,int width,int height,int highScore){
        this.setSize(width,height);
        this.setLocation(x,y);
        this.setLayout(null);
        this.setBackground(Color.red);
        this.setOpaque(false);
        this.isStart = false;
        this.index = 0;
        loadImages();
        addCrown();
        addBlastLabel();
        addBallLabel();
        addMoveInstructions();
        addHighScore(highScore);
        createButtonStart();
        this.music = new Sound();
        addMusic();
        this.setVisible(true);


    }

    public void addMusic(){
        this.music.loadMusicClip(Constants.INTRO_NUM);
        this.music.playMusic();
    }
    public boolean isStart() {
        return this.isStart;
    }
    public void addBlastLabel(){
        this.blast = new JLabel("Blast",JLabel.CENTER);
        this.blast.setBounds(Constants.INSTRUCTION_WIDTH/4,40,280,100);
        this.blast.setFont(new Font("arial",Font.BOLD,100));
        this.add(this.blast, BorderLayout.CENTER);
        this.blast.setVisible(true);
        this.blast.setForeground(new Color(238,177,255));

        this.smallBlast = new JLabel("Blast",JLabel.CENTER);
        this.smallBlast.setBounds(this.blast.getX()+3,this.blast.getY()+3,280,100);
        this.smallBlast.setFont(new Font("arial",Font.BOLD,100));
        this.add(this.smallBlast, BorderLayout.CENTER);
        this.smallBlast.setVisible(true);
        this.smallBlast.setForeground(new Color(166,183,204));
    }
    public void addBallLabel(){
        this.ball = new JLabel("Ball",JLabel.CENTER);
        this.ball.setBounds(this.blast.getX()+118,this.blast.getY()-50,120,100);
        this.ball.setFont(new Font("arial",Font.BOLD,58));
        this.add(this.ball, BorderLayout.CENTER);
        this.ball.setForeground(new Color(238,177,255));
        this.ball.setVisible(true);

        this.smallBall = new JLabel("Ball", JLabel.CENTER);
        this.smallBall.setBounds(this.ball.getX()+2,this.ball.getY()+2, 120,100);
        this.smallBall.setFont(new Font("arial",Font.BOLD,58));
        this.add(this.smallBall, BorderLayout.CENTER);
        this.smallBall.setForeground(new Color(166,183,204));
        this.smallBall.setVisible(true);
    }
    public void addCrown(){
        try {
            this.crown = ImageIO.read(new File("res/instructions/crown.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void addMoveInstructions(){
        this.moveInstructions = new JLabel("To move and shoot PRESS:",JLabel.LEFT);
        this.moveInstructions.setBounds(this.blast.getX()-40,this.blast.getY()+180,400,100);
        this.moveInstructions.setFont(new Font("arial",Font.BOLD,30));
        this.add(this.moveInstructions, BorderLayout.CENTER);
        this.moveInstructions.setVisible(true);
        this.moveInstructions.setForeground(new Color(238,177,255));
    }

    public void addHighScore(int highScore){
        this.highScore = new JLabel("",JLabel.LEFT);
        this.highScore.setText(Integer.toString(highScore));
        this.highScore.setBounds(this.blast.getX()+170,this.blast.getY()+120,100,20);
        this.highScore.setFont(new Font("arial",Font.BOLD,20));
        this.add(this.highScore, BorderLayout.CENTER);
        this.highScore.setVisible(true);
        this.highScore.setForeground(new Color(238,177,255));
    }

    private void createButtonStart() {
        this.start = new JButton();
        this.startIcon = new ImageIcon("res/instructions/start.png");
        this.start.setBounds(Constants.INSTRUCTION_WIDTH/3+45,Constants.INSTRUCTION_HEIGHT/3+200,this.startIcon.getIconWidth(),this.startIcon.getIconHeight());
        this.start.setIcon(this.startIcon);
        this.start.setOpaque(false);
        this.start.setContentAreaFilled(false);
        this.add(this.start);
        this.start.setVisible(true);
        this.start.addActionListener((e ->{
            this.isStart = true;
            this.setVisible(false);
            this.music.loadMusicClip(Constants.DURING_NUM);
            this.music.playMusic();
        }));
    }

    public void setStart(boolean start) {
        isStart = start;
    }

    private void loadImages(){
        try {
            this.rightKey = new BufferedImage[]{ImageIO.read(new File("res/instructions/rightUnPressed.png")), ImageIO.read(new File("res/instructions/rightPressed.png"))};
            this.leftKey = new BufferedImage[]{ImageIO.read(new File("res/instructions/leftUnPressed.png")), ImageIO.read(new File("res/instructions/leftPressed.png"))};
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void keysAnimation(Graphics2D graphics2D, BufferedImage[] images, int x, int y,int width,int height){
        graphics2D.drawImage(images[this.index],x,y,width,height,null);
        if(this.index==0){
            this.index=1;
        }else {
            this.index=0;
        }
    }
    public Sound getMusic(){
        return this.music;
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.drawImage(this.crown,this.highScore.getX()-38,this.highScore.getY(),30,20,null);
        keysAnimation(graphics2D,this.leftKey,this.moveInstructions.getX()+135,this.moveInstructions.getY()+80,50,50);
        keysAnimation(graphics2D,this.rightKey,this.moveInstructions.getX()+195,this.moveInstructions.getY()+80,50,50);
    }
}
