import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TopPanel extends JPanel {
    private  JLabel showScore;
    private File file;
    private static int score;

    public TopPanel(int width,int height){
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(51,130,254));
        this.file = new File(Constants.SCORE_PATH);
        readScore();
        this.showScore = new JLabel("High Score: "+Integer.toString(score),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,30));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(15,0,200,100);
        this.add(this.showScore);

    }

    public static int getScore() {
        return score;
    }

    private void readScore(){

        if (this.file.exists()&&this.file!=null){
            try {
                FileReader fileReader =  new FileReader(this.file);
                BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    score = Integer.valueOf(bufferedReader.readLine());
                }catch (Exception e){
                    System.out.println("wtf");
                }
                bufferedReader.close();
                fileReader.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

    public void writeScore(){

        if (score<Ball.getScore()){
            try {
                FileWriter fileWriter = new FileWriter(this.file);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(Integer.toString(Ball.getScore()));
                bufferedWriter.close();
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }

        }
    }

}
