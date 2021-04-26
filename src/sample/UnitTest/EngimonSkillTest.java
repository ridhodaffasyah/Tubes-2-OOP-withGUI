package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class EngimonSkillTest {
    private EngimonSkill testEngiSkill = new EngimonSkill();
    private EngimonSkill testEngiSkill2 = new EngimonSkill("Electric Ball", 70, "Electric");
    private EngimonSkill testEngiSkill3 = new EngimonSkill(new Skill());

    @Test
    public void EngimonSkillTestSuccess() {
        EngimonSkill tempEngiSkill = new EngimonSkill();
        assertEquals(tempEngiSkill, testEngiSkill);
    }

    @Test
    public void EngimonSkillTestFail() {
        EngimonSkill tempEngiSkill = new EngimonSkill();
        assertNotEquals(tempEngiSkill, testEngiSkill2);
    }

    @Test
    public void EngimonSkillWithParamTestSuccess() {
        EngimonSkill tempEngiSkill2 = new EngimonSkill("Electric Ball", 70, "Electric");
        assertEquals(tempEngiSkill2, testEngiSkill2);
    }

    @Test
    public void EngimonSkillWithParamTestFail() {
        EngimonSkill tempEngiSkill2 = new EngimonSkill("Electric Ball", 70, "Electric");
        assertNotEquals(tempEngiSkill2, testEngiSkill);
    }

    @Test
    public void EngimonSkillBaseTestSuccess() {
        EngimonSkill tempEngiSkill3 = new EngimonSkill(new Skill());
        assertEquals(tempEngiSkill3, testEngiSkill3);
    }

    @Test
    public void EngimonSkillBaseTestFail() {
        EngimonSkill tempEngiSkill3 = new EngimonSkill(new Skill());
        assertNotEquals(tempEngiSkill3, testEngiSkill);
    }

    @Test
    public void DamageTestSuccess() {
        assertEquals(65, testEngiSkill.damage());
    }

    @Test
    public void DamageBaseTestFail() {
        assertEquals(0, testEngiSkill.damage());
    }

    @Test
    public void getMasteryTestSuccess() {
        assertEquals(1, testEngiSkill.getMasteryLevel());
    }

    @Test
    public void getMasterBaseTestFail() {
        assertEquals(5, testEngiSkill.getMasteryLevel());
    }

    @Test
    public void setMasteryTestSuccess() {
        testEngiSkill.setMasteryLevel(3);
        assertEquals(3, testEngiSkill.getMasteryLevel());
    }

    @Test
    public void setMasterBaseTestFail() {
        testEngiSkill.setMasteryLevel(3);
        assertEquals(1, testEngiSkill.getMasteryLevel());
    }
}