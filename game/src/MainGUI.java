import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;

public class MainGUI implements ActionListener {
    //Creating some general buttons to press
    private JButton startGameButton  = new JButton("Start Game");
    private JButton exitButton = new JButton("Exit Game");
    private JButton nextButton = new JButton("Next");
    private JButton mainMenuButton = new JButton("Main Menu");
    //Creating the EntityItemCreation Frame
    private JFrame mainFrame = new JFrame();

    private int next = 0;
    private JPanel logPanel;
    private JTextArea text;
    private JScrollPane scroller;
    //Creating a label and some panels to hold things in
    private JLabel BGLabel;
    private JPanel BGPanel;
    private JPanel coinPanel;
    private JPanel buttonsPanel;

    //Buttons and Labels that are used in the Market
    private JLabel coinLabel;
    private JButton healthPotionBuy;
    private JButton enhancerPotionBuy;
    private JButton bigHealthPotionBuy;
    private JButton catPotionBuy;
    private JButton eagleEyePotionBuy;

    //Text Areas that are used to show visible stats to the player 
    private JTextArea playerStats;
    private JTextArea orcStats;
    private JTextArea attackLog;

     //Declaring and Initializing Attack Buttons and the Panel that will hold them
    private JButton quickAttackButton = new JButton("Quick Attack");
    private JButton normalAttackButton = new JButton("Normal Attack");
    private JButton heavyAttackButton = new JButton("Heavy Attack");
    private JPanel attackPanel = new JPanel();

    //The Current enemy that the player is facing and various checker booleans
    private Entity currentOrc;
    private Player player;
    private boolean playerTurn;
    private boolean inBattle;
    private int orcNum = 0;

    //Text that will be used at the ending of the game
    private JTextArea endGameText;

    //Panel that will be used in the win screen
    private WinPanel winPanel;

    //Audio/Music that will be used
    private Audio audio = new Audio();
    private URL titleMusic = getClass().getClassLoader().getResource("Audio/bensound-birthofahero.wav");
    private URL inBattleMusic = getClass().getClassLoader().getResource("Audio/bensound-epic.wav");
    private URL gameOverMusic = getClass().getClassLoader().getResource("Audio/bensound-ofeliasdream.wav");
    private URL winMusic = getClass().getClassLoader().getResource("Audio/bensound-happyrock.wav");
    private URL marketMusic = getClass().getClassLoader().getResource("Audio/bensound-ukulele.wav");
    private URL instructionsVoice = getClass().getClassLoader().getResource("Audio/Instruction Audio.wav");
    private URL currentAudio;

    MainGUI(){
        //Plays some music
        playAudio(titleMusic);

        //Sets the initial looks of a frame to act as the EntityItemCreation screen
        mainFrame.setSize(1200,950);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(null);
        //Changes Color of Frame
        mainFrame.getContentPane().setBackground(Color.BLACK);

        //Setting a text box and the looks of the words in it
        text = new JTextArea("ORC SLAYAA!");
        text.setBounds(350,20,450,90);
        text.setForeground(new Color(238,227,26));
        text.setBackground(new Color(150,111,51));
        text.setEditable(false);
        text.setLineWrap(true);
        text.setWrapStyleWord(true);
        text.setFont(new Font("Comic Sans", Font.BOLD,65));
        
        //Creates an imageicon of the title screem image
        ImageIcon titleBG = new ImageIcon(getClass().getClassLoader().getResource("Images/StartingBG.png"));

        //Declare and sets the background label as the title image 
        BGLabel = new JLabel();
        BGLabel.setBounds(0,0,1098,551);
        BGLabel.setIcon(titleBG);

        //Setting the buttons to their preferred looks
        startGameButton.setSize(300, 100);
        startGameButton.setForeground(Color.RED);
        startGameButton.setBackground(Color.YELLOW);
        startGameButton.setFont(new Font("Comic Sans",Font.BOLD,30));
        startGameButton.setBorder(BorderFactory.createEtchedBorder());
        startGameButton.addActionListener(this);

        exitButton.setSize(300, 100);
        exitButton.setForeground(Color.RED);
        exitButton.setBackground(Color.YELLOW);
        exitButton.setFont(new Font("Comic Sans",Font.BOLD,30));
        exitButton.setBorder(BorderFactory.createEtchedBorder());
        exitButton.addActionListener(this);

        //Creates a couple of panels to put on the frame
        BGPanel = new JPanel();
        BGPanel.setBounds(50,190,1098,551);
        BGPanel.setBackground(Color.BLACK);
        BGPanel.add(BGLabel);

        buttonsPanel = new JPanel();
        buttonsPanel.setBounds(440, 800, 300, 100);
        buttonsPanel.setBackground(Color.BLACK);
        buttonsPanel.add(startGameButton);
        buttonsPanel.add(exitButton);
    
        //EntityItemCreation Frame now has panels and text on it
        mainFrame.add(text);
        mainFrame.add(BGPanel);
        mainFrame.add(buttonsPanel);

        //After everything is added, the screen shows the panels
        mainFrame.setVisible(true);
    }

    public void instructions(){
        stopAudio(titleMusic);
        playAudio(instructionsVoice);

        //Changes the text to be of the appropriate instruction text
        text.setText("Instructions to Play This Game\n\n" + 
        "This is a turn-based game where you play as an adventurer tasked with clearing an orc hideout and eliminating the orc boss. " + 
        "When you encounter an orc enemy, you are presented with THREE attack choices:\nQuick attack(higher chance of hitting but lowered damage)\nNormal attack(regular attack)\n" + 
        "Heavy attack(higher damage but lower chance of hitting)\n\nThe enemy also have these attack choices when fighting you. " +
        "The chances of dealing damage to the enemy and also to you is based on the type of attack, hit accuracy stat, dodge skills, and the RNG behind these stats. " +
        "Each encounter starts with either you or the enemy attacking first based on, you guessed it, RNG. " + 
        "Once an entity(a term used to refer to player and enemy) finishes its turn and the opposing entity is still alive, the opposing entity starts their turn. Repeat until one entity is killed.");
        text.setBounds(60,50,1070,700);
        text.setFont(new Font("Bree Serif", Font.ITALIC, 30));

        //Changes the looks of the next and exit buttons
        nextButton.setSize(350, 100);
        nextButton.setForeground(Color.WHITE);
        nextButton.setBackground(new Color(102,102,255));
        nextButton.setFont(new Font("MV Boli",Font.PLAIN,25));
        nextButton.setBorder(BorderFactory.createEtchedBorder());
        nextButton.addActionListener(this);

        exitButton.setSize(350, 100);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBackground(new Color(102,102,255));
        exitButton.setFont(new Font("MV Boli",Font.PLAIN,25));
        nextButton.setBorder(BorderFactory.createEtchedBorder());
        //Sets some panel to be invisible
        BGPanel.setVisible(false);
        buttonsPanel.setVisible(false);
        //Remove, rearrange, and add buttons
        buttonsPanel.setBounds(430, 850, 350, 100);
        buttonsPanel.remove(startGameButton);
        buttonsPanel.remove(exitButton);
        buttonsPanel.add(nextButton);
        buttonsPanel.add(exitButton);

        //After everything is added, the panel and frames is now visible
        buttonsPanel.setVisible(true);
        mainFrame.setVisible(true);
    }

    public void market(){
        stopAudio(instructionsVoice);
        playAudio(marketMusic);
        //Changing the text and removing the starting background image
        text.setText("You are now in the market screen. CLICK on potions to buy them to increase your stats." + 
        "Their description and price is shown on each potion. You can only buy them when you have sufficient funds.");
        text.setFont(new Font("Bree Serif", Font.ITALIC, 30));
        text.setBackground(new Color(150,111,51));
        text.setBounds(70,10,800,170);
        BGPanel.remove(BGLabel);

        player = EntityItemCreation.getPlayer();

        ImageIcon coinImage = new ImageIcon(getClass().getClassLoader().getResource("Images/Coin.png"));
        coinLabel = new JLabel();
        coinLabel.setIcon(coinImage);
        coinLabel.setText(player.getCoins() + " coins" ); 
        coinLabel.setForeground(Color.WHITE);
        coinLabel.setFont(new Font("Normal", Font.BOLD,29));

        //Putting images into the respective buttons
        ImageIcon healthPotionImage = new ImageIcon(getClass().getClassLoader().getResource("Images/HealthPotion.png"));
        healthPotionBuy = new JButton(EntityItemCreation.getItem(0).getName() + ". Costs " + EntityItemCreation.getItem(0).getCost() + " coins");
        healthPotionBuy.setIcon(healthPotionImage);
        healthPotionBuy.setVerticalTextPosition(JButton.TOP);
        healthPotionBuy.setHorizontalTextPosition(JButton.CENTER);
        healthPotionBuy.setBackground(new Color(255,204,229));
        healthPotionBuy.setFont(new Font("Normal", Font.PLAIN,20));
        healthPotionBuy.addActionListener(this);
        ImageIcon enhancerPotionImage = new ImageIcon(getClass().getClassLoader().getResource("Images/Enhancer Potion.png"));
        enhancerPotionBuy = new JButton("<html>" + EntityItemCreation.getItem(1).getName() + ". Costs " + EntityItemCreation.getItem(1).getCost() + " coins" + "<html>");
        enhancerPotionBuy.setIcon(enhancerPotionImage);
        enhancerPotionBuy.setVerticalTextPosition(JButton.TOP);
        enhancerPotionBuy.setHorizontalTextPosition(JButton.CENTER);
        enhancerPotionBuy.setBackground(new Color(255,204,229));
        enhancerPotionBuy.setOpaque(true);
        enhancerPotionBuy.setFont(new Font("Normal", Font.PLAIN,20));
        enhancerPotionBuy.addActionListener(this);
        ImageIcon bigHealthPotionImage = new ImageIcon(getClass().getClassLoader().getResource("Images/BigHealthPotion.png"));
        bigHealthPotionBuy = new JButton("<html>" + EntityItemCreation.getItem(2).getName() + ". Costs " + EntityItemCreation.getItem(2).getCost() + " coins" + "<html>");
        bigHealthPotionBuy.setIcon(bigHealthPotionImage);
        bigHealthPotionBuy.setVerticalTextPosition(JButton.TOP);
        bigHealthPotionBuy.setHorizontalTextPosition(JButton.CENTER);
        bigHealthPotionBuy.setBackground(new Color(255,204,229));
        bigHealthPotionBuy.setFont(new Font("Normal", Font.PLAIN,20));
        bigHealthPotionBuy.addActionListener(this);
        ImageIcon catPotionImage = new ImageIcon(getClass().getClassLoader().getResource("Images/Cat Potion.png"));
        catPotionBuy = new JButton(EntityItemCreation.getItem(3).getName() + ". Costs " + EntityItemCreation.getItem(3).getCost() + " coins");
        catPotionBuy.setIcon(catPotionImage);
        catPotionBuy.setVerticalTextPosition(JButton.TOP);
        catPotionBuy.setHorizontalTextPosition(JButton.CENTER);
        catPotionBuy.setBackground(new Color(255,204,229));
        catPotionBuy.setFont(new Font("Normal", Font.PLAIN,20));
        catPotionBuy.addActionListener(this);
        ImageIcon eagleEyePotionImage = new ImageIcon(getClass().getClassLoader().getResource("Images/Eagle Eye Potion.png"));
        eagleEyePotionBuy = new JButton("<html>" + EntityItemCreation.getItem(4).getName() + ". Costs " + EntityItemCreation.getItem(4).getCost() + " coins" + "<html>");
        eagleEyePotionBuy.setIcon(eagleEyePotionImage);
        eagleEyePotionBuy.setVerticalTextPosition(JButton.TOP);
        eagleEyePotionBuy.setHorizontalTextPosition(JButton.CENTER);
        eagleEyePotionBuy.setBackground(new Color(255,204,229));
        eagleEyePotionBuy.setFont(new Font("Normal", Font.PLAIN,20));
        eagleEyePotionBuy.addActionListener(this);

        //Shows the initial player stats that will change when potions are bought
        playerStats = new JTextArea("" + player.toString());
        playerStats.setEditable(false);
        playerStats.setBackground(new Color(255,204,229));
        playerStats.setForeground(new Color(32,32,32));
        playerStats.setFont(new Font("MV Boli", Font.BOLD, 22));

        //Background panel is repurposed as the houser of potion buttons
        BGPanel.setBounds(70,190,1050,650);
        BGPanel.setLayout(new GridLayout(3,2,7,7));
        coinPanel = new JPanel();
        coinPanel.add(coinLabel);
        coinPanel.setBounds(900,10,220,120);
        coinPanel.setBackground(new Color(150,111,51));
        BGPanel.add(healthPotionBuy);
        BGPanel.add(enhancerPotionBuy);
        BGPanel.add(bigHealthPotionBuy);
        BGPanel.add(catPotionBuy);
        BGPanel.add(eagleEyePotionBuy);
        BGPanel.add(playerStats);

        mainFrame.add(coinPanel);

        text.setVisible(true);
        BGPanel.setVisible(true);
        mainFrame.setVisible(true);
    }

    public void battlePrep(){
        stopAudio(titleMusic);
        currentAudio = inBattleMusic;
        playAudio(currentAudio);

        //Changing settings of the attack buttons
        quickAttackButton.setBackground(new Color(132,97,45));
        quickAttackButton.setForeground(new Color(255,174,201));
        quickAttackButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        quickAttackButton.addActionListener(this);

        normalAttackButton.setBackground(new Color(132,97,45));
        normalAttackButton.setForeground(new Color(255,174,201));
        normalAttackButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        normalAttackButton.addActionListener(this);

        heavyAttackButton.setBackground(new Color(132,97,45));
        heavyAttackButton.setForeground(new Color(255,174,201));
        heavyAttackButton.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        heavyAttackButton.addActionListener(this);

        attackPanel.setLayout(new GridLayout(3,1));
        attackPanel.add(quickAttackButton);
        attackPanel.add(normalAttackButton);
        attackPanel.add(heavyAttackButton);
        attackPanel.setBounds(495, 640, 220, 200);

        //Sets some panels to invisible and removes all components of BGPanel to repurpose it again as a background panel
        BGPanel.setVisible(false);
        BGPanel.removeAll();
        coinPanel.setVisible(false);
        buttonsPanel.remove(nextButton);
        mainFrame.remove(text);

        //Setting the attack log and scroller so the user can see the damage dealing between player and orc
        attackLog = new JTextArea(8,20);
        attackLog.setEditable(false);
        attackLog.setLineWrap(true);
        attackLog.setBackground(new Color(150,111,51));
        attackLog.setForeground(new Color(238,227,26));
        attackLog.setFont(new Font("Times New Roman", Font.PLAIN, 25));

        scroller = new JScrollPane(attackLog,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        logPanel = new JPanel();
        logPanel.setBounds(100, 20, 500, 100);
        logPanel.add(scroller);
        logPanel.setBackground(Color.BLACK);
        logPanel.setVisible(false);

        text.setText("An orc slave stood in your way when you entered the orc hideout. These are weak, pitiful creatures that a simple farmer can handle.");
        text.setBounds(100,20,1000,80);

        //New images to be put in the background label
        ImageIcon slaveOrcScene = new ImageIcon(getClass().getClassLoader().getResource("Images/SlaveOrcScene.png"));
        BGLabel.setIcon(slaveOrcScene);
        BGLabel.setBounds(0, 0, 1010,470);
        BGLabel.setIcon(slaveOrcScene);
        BGPanel.setBounds(90,130,1010,470);
        BGPanel.setBackground(Color.WHITE);
        BGPanel.setLayout(null);
        BGPanel.add(BGLabel);

        playerStats.setBounds(90, 640, 300, 250);
        playerStats.setBackground(new Color(150,111,51));
        player.heal();
        playerStats.setText("" + player.toString()); 

        currentOrc = EntityItemCreation.getEnemy(orcNum);

        orcStats = new JTextArea(currentOrc.toString());
        orcStats.setEditable(false);
        orcStats.setBounds(800,640,300,250);
        orcStats.setForeground(new Color(32,32,32));
        orcStats.setBackground(new Color(150,111,51));
        orcStats.setFont(new Font("MV Boli", Font.BOLD, 22));

        mainFrame.add(text);
        mainFrame.add(attackPanel);
        mainFrame.add(playerStats);
        mainFrame.add(orcStats);
        mainFrame.add(logPanel);
        BGPanel.setVisible(true);
        mainFrame.setVisible(true);

        playerTurn = true;
        inBattle = true;
    }

    public void deathCheck(){
        if(player.getCurrentHP() <= 0){
            gameOver();
        }
        if(currentOrc.getCurrentHP() <= 0){
            orcNum++;
            player.heal();
            playerStats.setText("" + player.toString());
            attackLog.setText(""); 
            battleChanger();
        }
    }

    public void battleChanger(){
        if(orcNum == 1){
            text.setText("After defeating the slave orc, you ran into a grunt orc. These orcs make up the backbone of orc raiding parties.");
            text.setVisible(true);
            logPanel.setVisible(false);
            ImageIcon gruntOrcScene = new ImageIcon(getClass().getClassLoader().getResource("Images/GruntOrcScene.png"));
            BGLabel.setIcon(gruntOrcScene);
            currentOrc = EntityItemCreation.getEnemy(orcNum);
            orcStats.setText(currentOrc.toString());

            BGPanel.setVisible(true);
            mainFrame.setVisible(true);
        }
        else if(orcNum == 2){
            text.setText("After your last slaying, a berserker orc rushed into your location. " +
            "Berserker orcs are ferocious warriors and should not be underestimated.");
            text.setVisible(true);
            logPanel.setVisible(false);
            ImageIcon berserkOrcScene = new ImageIcon(getClass().getClassLoader().getResource("Images/BerserkOrcScene.png"));
            BGLabel.setIcon(berserkOrcScene);
            currentOrc = EntityItemCreation.getEnemy(orcNum);
            orcStats.setText(currentOrc.toString());

            BGPanel.setVisible(true);
            mainFrame.setVisible(true);
        }
        else if(orcNum == 3){
            text.setText("You hasten into the boss orc's chamber where you faced an armored behemoth, skilled in fencing and dodging.");
            text.setVisible(true);
            logPanel.setVisible(false);
            ImageIcon bossOrcScene = new ImageIcon(getClass().getClassLoader().getResource("Images/BossOrcScene.png"));
            BGLabel.setIcon(bossOrcScene);
            currentOrc = EntityItemCreation.getEnemy(orcNum);
            orcStats.setText(currentOrc.toString());

            BGPanel.setVisible(true);
            mainFrame.setVisible(true);
        }
        else{
            win();
        }
    }

    public void gameOver(){
        stopAudio(currentAudio);
        currentAudio = gameOverMusic;
        playAudio(currentAudio);

        endGameText = new JTextArea("Your HP has reached 0 and so you died. " +
         "To play again, press the MAIN MENU Button and START GAME button when returned to the main menu. " + 
         "Press the EXIT GAME button if you want to give up.");
        endGameText.setBounds(100, 620, 1000, 100);
        endGameText.setForeground(new Color(238,227,26));
        endGameText.setBackground(Color.BLACK);
        endGameText.setFont(new Font("Bree Serif", Font.ITALIC, 25));
        endGameText.setLineWrap(true);
        endGameText.setWrapStyleWord(true);
        endGameText.setEditable(false);
        endGameText.setVisible(true);

        //Resets the Main Frame
        mainFrame.getContentPane().removeAll();

        text.setBounds(450, 300, 500, 300);
        text.setText("YOU DIED");
        text.setFont(new Font("", Font.BOLD, 69));
        text.setForeground(Color.RED);
        text.setBackground(Color.BLACK);
        text.setVisible(true);

        mainMenuButton.setSize(350, 100);
        mainMenuButton.setForeground(Color.WHITE);
        mainMenuButton.setBackground(new Color(102,102,255));
        mainMenuButton.setFont(new Font("MV Boli",Font.PLAIN,25));
        mainMenuButton.setBorder(BorderFactory.createEtchedBorder());
        mainMenuButton.addActionListener(this);

        buttonsPanel.remove(exitButton);
        buttonsPanel.add(mainMenuButton);
        buttonsPanel.add(exitButton);
        buttonsPanel.setVisible(true);

        mainFrame.add(endGameText);
        mainFrame.add(text);
        mainFrame.add(buttonsPanel);
        mainFrame.repaint();
        
        mainFrame.setVisible(true);
    }

    public void win(){
        stopAudio(currentAudio);
        currentAudio = winMusic;
        playAudio(currentAudio);

        mainFrame.getContentPane().removeAll();
        winPanel = new WinPanel();
        winPanel.setBounds(10, 400, 1190, 200);

        endGameText = new JTextArea("Congratulations on clearing the orc hideout and defeating the Orc Boss.\n" + 
        "Here are two dancing knights for your reward:");
        endGameText.setBounds(100, 100, 1000, 180);
        endGameText.setForeground(new Color(238,227,26));
        endGameText.setBackground(new Color(0,0,0,140));
        endGameText.setFont(new Font("Bree Serif", Font.ITALIC, 40));
        endGameText.setLineWrap(true);
        endGameText.setWrapStyleWord(true);
        endGameText.setEditable(false);
        endGameText.setVisible(true);

        text.setText("THANK YOU FOR PLAYING!");
        text.setBounds(400, 750, 400, 50);
        text.setBackground(new Color(0,0,0,140));
        text.setVisible(true);

        ImageIcon fireWorkImage = new ImageIcon(getClass().getClassLoader().getResource("Images/FireworksBG.png"));
        BGLabel.setIcon(fireWorkImage);
        BGLabel.setBounds(0, 0, 1180, 830);
        BGPanel.setBounds(10, 10, 1180, 830);
        BGPanel.setVisible(true);

        buttonsPanel.remove(nextButton);
        buttonsPanel.setBackground(new Color(0,0,0,5));
        mainFrame.add(winPanel);
        mainFrame.add(buttonsPanel);
        mainFrame.add(endGameText);
        mainFrame.add(text);
        mainFrame.add(BGPanel);
        

        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    //Used when a button is pressed and makes it do something
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == exitButton){
            System.exit(0);
        }
        if(e.getSource() == startGameButton){
            instructions();
        }
        if(e.getSource() == nextButton && next == 1){
            next++;
            battlePrep();
        }
        if(e.getSource() == nextButton && next == 0){
            next++;
            market();
        }
        if(e.getSource() == healthPotionBuy && (player.getCoins() - EntityItemCreation.getItem(0).getCost() >= 0)){
            player.setMaxHP(24);
            player.setCoins(EntityItemCreation.getItem(0).getCost());
            coinLabel.setText(player.getCoins() + " coins" );
            playerStats.setText("" + player.toString()); 
        }
        if(e.getSource() == enhancerPotionBuy && (player.getCoins() - EntityItemCreation.getItem(1).getCost() >= 0)){
            player.setDamage(8);
            player.setCoins(EntityItemCreation.getItem(1).getCost());
            coinLabel.setText(player.getCoins() + " coins" ); 
            playerStats.setText("" + player.toString()); 
        }
        if(e.getSource() == bigHealthPotionBuy && (player.getCoins() - EntityItemCreation.getItem(2).getCost() >= 0)){
            player.setMaxHP(48);
            player.setCoins(EntityItemCreation.getItem(2).getCost());
            coinLabel.setText(player.getCoins() + " coins" ); 
            playerStats.setText("" + player.toString()); 
        }
        if(e.getSource() == catPotionBuy && (player.getCoins() - EntityItemCreation.getItem(3).getCost() >= 0)){
            player.setDodge(12);
            player.setCoins(EntityItemCreation.getItem(3).getCost());
            coinLabel.setText(player.getCoins() + " coins" ); 
            playerStats.setText("" + player.toString()); 
        }
        if(e.getSource() == eagleEyePotionBuy && (player.getCoins() - EntityItemCreation.getItem(4).getCost() >= 0)){
            player.setHitAccuracy(0.04);
            player.setCoins(EntityItemCreation.getItem(4).getCost());
            coinLabel.setText(player.getCoins() + " coins" ); 
            playerStats.setText("" + player.toString()); 
        }
        //This if statement is used to dictate what happens during a player's and enemy's turn
        if(inBattle){
            if(playerTurn){
                if(e.getSource() == quickAttackButton){
                    int beforeAttack = currentOrc.getCurrentHP();
                    player.quickAttack(currentOrc);
                    int afterAttack = currentOrc.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nYou deal " + damageDeal + " damage to the orc.");
                    orcStats.setText(currentOrc.toString());
                    playerTurn = false;
                    text.setVisible(false);
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
                if(e.getSource() == normalAttackButton){
                    int beforeAttack = currentOrc.getCurrentHP();
                    player.normalAttack(currentOrc);
                    int afterAttack = currentOrc.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nYou deal " + damageDeal + " damage to the orc.");
                    orcStats.setText(currentOrc.toString());
                    playerTurn = false;
                    text.setVisible(false);
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
                if(e.getSource() == heavyAttackButton){
                    int beforeAttack = currentOrc.getCurrentHP();
                    player.heavyAttack(currentOrc);
                    int afterAttack = currentOrc.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nYou deal " + damageDeal + " damage to the orc.");
                    orcStats.setText(currentOrc.toString());
                    playerTurn = false;
                    text.setVisible(false);
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
            }
            if(!playerTurn){
                double random = Math.random();
                if(random < 0.2){
                    int beforeAttack = player.getCurrentHP();
                    currentOrc.quickAttack(player);
                    int afterAttack = player.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nThe orc deal " + damageDeal + " damage to you.");
                    playerStats.setText(player.toString());
                    playerTurn = true;
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
                else if(random > 0.7){
                    int beforeAttack = player.getCurrentHP();
                    currentOrc.heavyAttack(player);
                    int afterAttack = player.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nThe orc deal " + damageDeal + " damage to you.");
                    playerStats.setText(player.toString());
                    playerTurn = true;
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
                else{
                    int beforeAttack = player.getCurrentHP();
                    currentOrc.normalAttack(player);
                    int afterAttack = player.getCurrentHP();
                    int damageDeal = beforeAttack - afterAttack;
                    attackLog.setText(attackLog.getText() + "\nThe orc deal " + damageDeal + " damage to you.");
                    playerStats.setText(player.toString());
                    playerTurn = true;
                    logPanel.setVisible(true);
                    mainFrame.setVisible(true);
                    deathCheck();
                }
            }
        }
        if(e.getSource() == mainMenuButton){
            mainFrame.getContentPane().removeAll();
            mainFrame.dispose();
            stopAudio(currentAudio);
            EntityItemCreation creation = new EntityItemCreation();
            MainGUI gui = new MainGUI();
        }
    }

    public void playAudio(URL url){
        audio.play(url);
        audio.loop(url);
    }

    public void stopAudio(URL url){
        audio.stop(url);
    }


}