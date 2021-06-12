public class Orc extends Entity{
    private boolean isBerserker; 

    public Orc(int hp, int dodge, int damage, double hit, boolean berserker){
        super(hp,dodge,damage,hit);
        isBerserker = berserker;
    }

    public boolean getIsBerserker(){
        return(isBerserker);
    }

    public void enrage(){
        if(getCurrentHP() <= getMaxHP()*0.2 && isBerserker){
            setHitAccuracy(getHitAccuracy()*1.2);
            setDamage((int)(getDamage()*1.7));
            setDodge((int)(getDodge()*0.7));
        }
    }

    public boolean equals(Orc orc){
        return(this.isBerserker == orc.getIsBerserker());
    }

    public String toString(){
        return ("Orc's Visible Stats\nHP: " + getCurrentHP() + "/" + getMaxHP());
    }
}
