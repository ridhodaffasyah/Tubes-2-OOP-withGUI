
public class Skill implements Comparable<Skill>{
    protected String namaSkill;
    protected String unique;
    protected int basePower;

    public Skill(){
        this.namaSkill = "XXX";
        this.unique = "XXX";
        this.basePower = 0;
    }
    public Skill(String namaSkill, String unique, int basePower){
        this.namaSkill = namaSkill;
        this.unique = unique;
        this.basePower = basePower;
    }
    public Skill(Skill S){
        this.namaSkill = S.namaSkill;
        this.basePower = S.basePower;
        this.unique = S.unique;
    }
    public String getNamaSkill(){
        return this.namaSkill;
    }
    public String getUnique(){
        return this.unique;
    }
    public int getBasePower(){
        return this.basePower;
    }
    public void setNamaSkill(String namaSkill){
        this.namaSkill = namaSkill;
    }
    public void setUnique(String unique){
        this.unique = unique;
    }
    public void setBasePower(int basePower){
        this.basePower = basePower;
    }
    @Override
    public int compareTo(Skill o) {
        if (this.getBasePower()>o.getBasePower()){
            return 1;
        }else if (this.getBasePower()==o.getBasePower()){
            return 0;
        }else{
            return -1;
        }
    }
}
