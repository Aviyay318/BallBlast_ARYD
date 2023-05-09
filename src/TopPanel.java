import javax.swing.*;
import java.awt.*;
import java.io.*;

public class TopPanel extends JPanel {
    private  JLabel showScore;
    private File file;
    private int score;
    public TopPanel(int width,int height){
        this.score= 0;
        this.setPreferredSize(new Dimension(width,height));
        this.setBackground(new Color(51,130,254));
        this.file = new File(Constants.SCORE_PATH);
        readScore();
         this.showScore = new JLabel("High Score: "+Integer.toString(this.score),JLabel.CENTER);
        this.showScore.setFont(new Font("arial",Font.BOLD,30));
        this.showScore.setForeground(Color.white);
        this.showScore.setBounds(15,0,200,100);
        this.add(this.showScore);

    }
private void readScore(){

    if (this.file.exists()&&this.file!=null){
        try {
            FileReader fileReader =  new FileReader(this.file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
                try {
                    this.score = Integer.valueOf(bufferedReader.readLine());
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

        if (this.score<Ball.getScore()){
            System.out.println(this.score+"hhhhhhhhhhhh0" + Ball.getScore(  ));
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
