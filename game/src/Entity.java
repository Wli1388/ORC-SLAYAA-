public class Entity {
    private int maxHP;
    private int currentHP;
    private int dodge;
    private int damage;
    private double hitAccuracy;

    public Entity(){
    }

    public Entity(int hp, int dodge, int dam, double hit){
        maxHP = hp;
        currentHP = hp;
        this.dodge = dodge;
        damage = dam;
        hitAccuracy = hit;
    }

    public int getMaxHP(){return maxHP;}
    public int getCurrentHP(){return currentHP;}
    public int getDodge(){return dodge;}
    public int getDamage(){return damage;}
    public double getHitAccuracy(){return hitAccuracy;}

    public void setMaxHP(int num){maxHP += num;}
    public void setCurrentHP(int num){currentHP += num;}
    public void setDodge(int num){dodge += num;}
    public void setDamage(int num){damage += num;}
    public void setHitAccuracy(double num){hitAccuracy += num;}

    //Different Attacks that entities can do
    public void quickAttack(Entity entity){ 
        double randomNum = Math.random();
        if(hitAccuracy*1.1 > randomNum && entity.getDodge()*0.01 < randomNum){
            entity.setCurrentHP(-(int)(damage*0.85));
        }
        else if(hitAccuracy*1.1 > randomNum){
            entity.setCurrentHP(-(int)(damage*0.4*0.85));
        }
        else{}
    }
    public void normalAttack(Entity entity){ 
        double randomNum = Math.random();
        if(hitAccuracy > randomNum && entity.getDodge()*0.01  < randomNum){
            entity.setCurrentHP(-damage);
        }
        else if(hitAccuracy > randomNum){
            entity.setCurrentHP(-(int)(damage*0.4));
        }
        else{}
    }
    public void heavyAttack(Entity entity){ 
        double randomNum = Math.random();
        if(hitAccuracy*0.85 > randomNum && entity.getDodge()*0.01  < randomNum){
            entity.setCurrentHP(-(int)(damage*1.2));
        }
        else if(hitAccuracy*0.85 > randomNum){
            entity.setCurrentHP(-(int)(damage*0.4*1.2));
        }
        else{}
    }

    public boolean equals(Entity entity){
        return(this.maxHP == entity.getMaxHP() && this.damage == entity.getDamage());
    }

    public String toString(){
        return ("Entity's Initial Stats\nMax HP: " + maxHP + "\nDodge Skill: " + dodge +
         "\nDamage Dealing: " + damage + "\nHit Accuracy: " + hitAccuracy);
    }
}
