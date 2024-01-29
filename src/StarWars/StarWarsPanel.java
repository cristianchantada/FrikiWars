package StarWars;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

public class StarWarsPanel extends JFrame implements ActionListener {
	
	
	// Insert your own absolute paths to the /src and /model directories of the project 
    private final String PATH_TO_IMG_SRC = "C:\\DAW\\Programación\\Java2Evaluacion\\starWarsFriki\\src\\";
    private final String PATH_TO_MODEL = "C:\\DAW\\Programación\\Java2Evaluacion\\starWarsFriki\\src\\model\\";
    
    
    private final String MODEL_FILE_NAME = "datosFrikiWars.txt";

    private FileWriter fw;

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel backgroundApplicationImg;
    private JLabel selectedCardImgLabel;

    private JLabel leftRandomCard;
    private JLabel centerRandomCard;
    private JLabel rightRandomCard;

    private JLabel imageRightOverlay;
    private JLabel imageLeftOverlay;
    private JLabel imageCenterOverlay;

    private JLabel gameOverImg;
    private JLabel deathStar;
    private JLabel vaders;

    private JButton nextMatchButton;
    private JButton leftCardButton;
    private JButton centerCardButton;
    private JButton rightCardButton;

    private Integer[] randomNumsArray = new Integer[3];
    private int randomNumMainCard;
    private static int numMatches = 0;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StarWarsPanel frame = new StarWarsPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public StarWarsPanel() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 810, 635);
        contentPane = new JPanel();

        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logic for obtaining 3 cards randomly
        for(int i = 0; i < 3; i++) {
            Integer randomNumber = (int) (Math.random() * 10) + 1;
            if(!Arrays.asList(randomNumsArray).contains(randomNumber)){
                randomNumsArray[i] = randomNumber;
            } else {
                i--;
            }
        }

        // Obtaining the main card from the previously chosen 3 cards.
        randomNumMainCard = (int) (Math.random() * 3);
        
        // Game over Star Wars images
        gameOverImg = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "gameOver.jpg"));
        gameOverImg.setBounds(273, 14, 265, 240);
        gameOverImg.setVisible(false);
        contentPane.add(gameOverImg);
        
        deathStar = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "star.jpg"));
        deathStar.setBounds(550, 9, 265, 240);
        deathStar.setVisible(false);
        contentPane.add(deathStar);
        
        vaders = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "vaders.jpg"));
        vaders.setBounds(10, 5, 265, 240);
        vaders.setVisible(false);
        contentPane.add(vaders);
        
        // Image hiding the right card
        imageRightOverlay = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "traseraCromo.jpg"));
        imageRightOverlay.setBounds(544, 295, 225, 225);
        contentPane.add(imageRightOverlay);

        // Image hiding the center card
        imageCenterOverlay = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "traseraCromo.jpg"));
        imageCenterOverlay.setBounds(287, 295, 225, 225);
        contentPane.add(imageCenterOverlay);

        // Image hiding the left card
        imageLeftOverlay = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "traseraCromo.jpg"));
        imageLeftOverlay.setBounds(31, 295, 225, 225);
        contentPane.add(imageLeftOverlay);

        // Image of the selected card
        selectedCardImgLabel = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[randomNumMainCard] + ".jpg"));
        selectedCardImgLabel.setBounds(288, 14, 225, 225);
        contentPane.add(selectedCardImgLabel);

        // New match button
        nextMatchButton = new JButton("Volver a lanzar");
        nextMatchButton.setBounds(31, 563, 738, 23);
        contentPane.add(nextMatchButton);
        nextMatchButton.addActionListener(this);
        nextMatchButton.setEnabled(false);

        // Discover right button
        rightCardButton = new JButton("Aquí");
        rightCardButton.setBounds(544, 531, 225, 23);
        contentPane.add(rightCardButton);
        rightCardButton.addActionListener(this);

        // Discover center button
        centerCardButton = new JButton("Aquí");
        centerCardButton.setBounds(287, 531, 225, 23);
        contentPane.add(centerCardButton);
        centerCardButton.addActionListener(this);

        // Discover left button
        leftCardButton = new JButton("Aquí");
        leftCardButton.setBounds(31, 531, 225, 23);
        contentPane.add(leftCardButton);
        leftCardButton.addActionListener(this);

        // Background image
        backgroundApplicationImg = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + "FondoPantalla.jpg"));
        backgroundApplicationImg.setBounds(-5,-17,810,635);
        contentPane.add(backgroundApplicationImg);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        boolean hit = false;

        // Logic when pressing each of the buttons
        if(e.getSource()==leftCardButton){
            if(randomNumsArray[0] == randomNumsArray[randomNumMainCard]) {
                hit = true;
                leftRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[0] + "ok.jpg"));
            } else {
                hit = false;
                leftRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[0] + "bad.jpg"));
            }
            centerRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[1] + ".jpg"));
            rightRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[2] + ".jpg"));

        } else if (e.getSource()== centerCardButton){
            if(randomNumsArray[1] == randomNumsArray[randomNumMainCard]) {
                hit = true;
                centerRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[1] + "ok.jpg"));
            } else {
                hit = false;
                centerRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[1] + "bad.jpg"));
            }

            leftRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[0] + ".jpg"));
            rightRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[2] + ".jpg"));

        } else if (e.getSource()== rightCardButton){

            if(randomNumsArray[2] == randomNumsArray[randomNumMainCard]) {
                hit = true;
                rightRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[2] + "ok.jpg"));
            } else {
                hit = false;
                rightRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[2] + "bad.jpg"));
            }

            leftRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[0] + ".jpg"));
            centerRandomCard = new JLabel(new ImageIcon(PATH_TO_IMG_SRC + randomNumsArray[1] + ".jpg"));

        } else if (e.getSource()== nextMatchButton) {
            dispose();
            main(null);
        }

        leftRandomCard.setBounds(31, 295, 225, 225);
        centerRandomCard.setBounds(287, 295, 225, 225);
        rightRandomCard.setBounds(544, 295, 225, 225);
        contentPane.add(leftRandomCard);
        contentPane.add(centerRandomCard);
        contentPane.add(rightRandomCard);
        contentPane.setComponentZOrder(leftRandomCard, 0);
        contentPane.setComponentZOrder(centerRandomCard, 0);
        contentPane.setComponentZOrder(rightRandomCard, 0);

        // Logic for enabling and disabling buttons
        if(e.getSource() != nextMatchButton) {
            numMatches++;
            imageRightOverlay.setVisible(false);
            imageLeftOverlay.setVisible(false);
            imageCenterOverlay.setVisible(false);
            rightCardButton.setEnabled(false);
            leftCardButton.setEnabled(false);
            centerCardButton.setEnabled(false);
            nextMatchButton.setEnabled(true);

            // Recording data in the model when pressing buttons other than the roll again button
            try{
                fw = new FileWriter(PATH_TO_MODEL + "\\" + MODEL_FILE_NAME, true);
                fw.write("Tirada nº " + numMatches + " | Cromo " + randomNumsArray[0] + " | Cromo " + randomNumsArray[1] + " | Cromo " + randomNumsArray[2] + "| ¿Acierto? = " + hit + ".\n" );
                fw.close();
            } catch(IOException ex){
                System.out.println("Error writing to the geek database: " + ex);
                ex.printStackTrace();
            }

            // End of the game when reaching 10 rolls
            if(numMatches >= 10){
                nextMatchButton.setEnabled(false);
                rightCardButton.setEnabled(false);
                leftCardButton.setEnabled(false);
                centerCardButton.setEnabled(false);
                gameOverImg.setVisible(true);
                deathStar.setVisible(true);
                vaders.setVisible(true);
                contentPane.setComponentZOrder(gameOverImg, 0);
            }
        }
    }
}
