public class EntityItemCreation{
    private static Item[] itemList = new Item[5];
    private static Entity[] enemyList = new Entity[4];
    private static Player player;

    public EntityItemCreation(){
        player = new Player(170, 15, 30, 0.7, 90);
        Entity orcSlave = new Orc(40, 5, 5, 0.3,false);
        Entity orcGrunt = new Orc(90, 15, 12, 0.55, false);
        Entity orcBerserker = new Orc(160, 20, 25, 0.75, true);
        Entity orcBoss = new Orc(250, 35, 18, 0.85, false);

        enemyList[0] = orcSlave;
        enemyList[1] = orcGrunt;
        enemyList[2] = orcBerserker;
        enemyList[3] = orcBoss;

        Item healthPotion = new Item("Health Potion (Increases MAX HP)", 23);
        Item bigHealthPotion = new Item("Big Health Potion (Increases MAX HP Even More)", 45);
        Item enhancerPotion = new Item("Enhancer Potion (Increases Damage Dealt)",34);
        Item catPotion = new Item("Cat Potion (Increases Dodge Skill)",49);
        Item eagleEyePotion = new Item("Eagle's Eye Potion (Increases Hit Accuracy)", 58);
    
        itemList[0] = healthPotion;
        itemList[1] = bigHealthPotion;
        itemList[2] = enhancerPotion;
        itemList[3] = catPotion;
        itemList[4] = eagleEyePotion;
    
        SearchSort.mergeSort(itemList, 0, 4);

    }

    public static Item getItem(int index){
        return itemList[index];
    }
    public static Player getPlayer(){
        return player;
    }
    public static Entity getEnemy(int index){
        return enemyList[index];
    }
}
