package sample.BackEnd;

public class EngimonSkill extends Skill{
    protected int masteryLevel;
    
    protected static int MAX_MASTERY_LEVEL = 3;

    public EngimonSkill(){
        super();
    }
    public EngimonSkill(String skill, int power, String element){
        super(skill, element, power);
        this.masteryLevel = 1;
    }
    public EngimonSkill(Skill baseSkill){
        super(baseSkill);
        this.masteryLevel = 1;
    }
    public int damage(){
        return basePower*masteryLevel;
    }
    public int getMasteryLevel(){
        return this.masteryLevel;
    }
    public void setMasteryLevel(int masteryLevel){
        if (masteryLevel<=MAX_MASTERY_LEVEL){
            this.masteryLevel = masteryLevel;
        }else{
            this.masteryLevel = MAX_MASTERY_LEVEL;
        }
    }
}
