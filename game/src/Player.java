public class Player extends Entity {
    private int coins;

    public Player(int hp, int dodge, int damage, double hit, int coin){
        super(hp,dodge,damage,hit);
        coins = coin;
    }

    public int getCoins(){return coins;}
    public void setCoins(int num){coins -= num;}

    public void heal(){
        int healing = (int)(getCurrentHP()*0.2); 
        if(healing + getCurrentHP() > getMaxHP())
             setCurrentHP(getMaxHP()-getCurrentHP());
        else
            setCurrentHP(healing);
        
    }

    public boolean equals(Player player){
        return(this.coins == player.getCoins());
    }

    public String toString(){
        return ("Player's Stats\nHP: " + getCurrentHP() + "/" + getMaxHP() + "\nDodge Skill: " + getDodge() +
         "\nDamage Dealing: " + getDamage() + "\nHit Accuracy: " + getHitAccuracy());
    }
    
}
