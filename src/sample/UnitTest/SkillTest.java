package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class SkillTest {
    private Skill testSkill = new Skill();
    private Skill testSkill2 = new Skill("Fire Ball", "Fire", 100);

    @Test
    public void SkillTestSuccess() {
        Skill tempSkill = new Skill();
        assertEquals(tempSkill, testSkill);
    }

    @Test
    public void SkillTestFail() {
        EngimonSkill tempEngiSkill = new EngimonSkill();
        assertNotEquals(tempEngiSkill, testSkill2);
    }

    @Test
    public void SkillWithParamTestSuccess() {
        Skill tempSkill = new Skill("Fire Ball", "Fire", 100);
        assertEquals(tempSkill, testSkill2);
    }

    @Test
    public void SkillWithParamTestFail() {
        Skill tempSkill = new Skill("Fire Ball", "Fire", 100);
        assertNotEquals(tempSkill, testSkill);
    }

    @Test
    public void SkillAssignmentTestSuccess() {
        Skill tempSkill = new Skill(testSkill2);
        assertEquals(tempSkill, testSkill2);
    }

    @Test
    public void SkillAssignmentTestFail() {
        Skill tempSkill = new Skill(testSkill2);
        assertNotEquals(tempSkill, testSkill);
    }

    @Test
    public void getNamaSkillTestSuccess() {
        assertEquals("Fire Ball", testSkill2.getNamaSkill());
    }

    @Test
    public void getNamaSkillTestFail() {
        assertNotEquals("Bom", testSkill2.getNamaSkill());
    }

    @Test
    public void getUniqueTestSuccess() {
        assertEquals("Fire", testSkill2.getUnique());
    }

    @Test
    public void getUniqueTestFail() {
        assertNotEquals("Light", testSkill2.getUnique());
    }

    @Test
    public void getBasePowerTestSuccess() {
        assertEquals(100, testSkill2.getBasePower());
    }

    @Test
    public void getBasePowerTestFail() {
        assertNotEquals(0, testSkill2.getBasePower());
    }

    @Test
    public void setNamaSkillTestSuccess() {
        testSkill2.setNamaSkill("Electric Ball");
        assertEquals("Electric Ball", testSkill2.getNamaSkill());
    }

    @Test
    public void setNamaSkillTestFail() {
        testSkill2.setNamaSkill("Electric Ball");
        assertNotEquals("Fire Ball", testSkill2.getNamaSkill());
    }

    @Test
    public void setUniqueTestSuccess() {
        testSkill2.setUnique("Electric");
        assertEquals("Electric", testSkill2.getUnique());
    }

    @Test
    public void setUniqueTestFail() {
        testSkill2.setUnique("Electric");
        assertNotEquals("Electric", testSkill2.getUnique());
    }

    @Test
    public void setBasePowerTestSuccess() {
        testSkill2.setBasePower(1000);
        assertEquals(1000, testSkill2.getBasePower());
    }

    @Test
    public void setBasePowerTestFail() {
        testSkill2.setBasePower(1000);
        assertNotEquals(0, testSkill2.getBasePower());
    }

    @Test
    public void CompareToTestSuccess() {
        assertEquals(-1, testSkill.compareTo(testSkill2));
    }

    @Test
    public void CompareToTestFail() {
        assertNotEquals(1, testSkill.compareTo(testSkill2));
    }
}