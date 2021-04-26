package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class EngimonTest {
    private Engimon testEngi = new Engimon();
    private Engimon testEngi2 = new Engimon("Plant", 5, new Point(3,3));

    @Test
    public void EngimonTestSuccess() {
        Engimon tempEngi = new Engimon();
        assertEquals(tempEngi, testEngi);
    }

    @Test
    public void EngimonTestFail() {
        assertNotEquals(testEngi2, testEngi);
    }

    @Test
    public void EngimonAssignmentTestSuccess() {
        Engimon copyTempEngi = new Engimon(testEngi2);
        assertEquals(testEngi2, copyTempEngi);
    }

    @Test
    public void EngimonAssigmentTestFail() {
        Engimon copyTempEngi = new Engimon(testEngi2);
        assertNotEquals(testEngi2, copyTempEngi);
    }

    @Test
    public void EngimonWithParamTestSuccess() {
        Engimon tempEngi = new Engimon("Plant", 5, new Point(3,3));
        assertEquals(testEngi2, tempEngi);
    }

    @Test
    public void EngimonWithParamTestFail() {
        Engimon tempEngi = new Engimon("Plant", 5, new Point(3,3));
        assertNotEquals(testEngi2, tempEngi);
    }

    @Test
    public void getNameTestSuccess() {
        assertEquals("Pikachu", testEngi);
    }

    @Test
    public void getNameTestFail() {
        assertNotEquals("NONE", testEngi);
    }

    @Test
    public void getLifeTestSuccess() {
        assertEquals(2, testEngi);
    }

    @Test
    public void getLifeTestFail() {
        assertNotEquals(0, testEngi);
    }

    @Test
    public void getParentNameTestSuccess() {
        assertEquals("Pika", testEngi);
    }

    @Test
    public void getParentNameTestFail() {
        assertNotEquals("NONE", testEngi);
    }

    @Test
    public void getSpeciesTestSuccess() {
        assertEquals("Cat", testEngi);
    }

    @Test
    public void getSpeciesTestFail() {
        assertNotEquals("Dragon", testEngi);
    }

    @Test
    public void getEngiSkillTestSuccess() {
        assertEquals("Electric Ball", testEngi.getEngiSkill().get(0).getNamaSkill());
    }

    @Test
    public void getEngiSkillTestFail() {
        assertNotEquals("Fire Ball", testEngi.getEngiSkill().get(0).getNamaSkill());
    }

    @Test
    public void getElementTestSuccess() {
        assertEquals("Electric", testEngi.get_elements());
    }

    @Test
    public void getElementTestFail() {
        assertNotEquals("Fire", testEngi.get_elements());
    }

    @Test
    public void getLevelTestSuccess() {
        assertEquals(1, testEngi.get_level());
    }

    @Test
    public void getLevelTestFail() {
        assertNotEquals(30, testEngi.get_level());
    }

    @Test
    public void getExpTestSuccess() {
        assertEquals(0, testEngi.get_exp());
    }

    @Test
    public void getExpTestFail() {
        assertNotEquals(100, testEngi.get_exp());
    }

    @Test
    public void getCumExpTestSuccess() {
        assertEquals(100, testEngi.get_cumExp());
    }

    @Test
    public void getCumExpTestFail() {
        assertNotEquals(0, testEngi.get_cumExp());
    }

    @Test
    public void getIdTestSuccess() {
        assertEquals(1, testEngi.get_id());
    }

    @Test
    public void getIdTestFail() {
        assertNotEquals(0, testEngi.get_id());
    }

    @Test
    public void getPosisiTestSuccess() {
        assertEquals(0, testEngi.get_posisi().getX());
        assertEquals(0, testEngi.get_posisi().getY());
    }

    @Test
    public void getPosisiTestFail() {
        assertNotEquals(10, testEngi.get_posisi().getX());
        assertNotEquals(10, testEngi.get_posisi().getY());
    }

    @Test
    public void getNumOfEngimonTestSuccess() {
        assertEquals(495, testEngi.get_sum_power());
    }

    @Test
    public void getNumOfEngimonTestFail() {
        assertNotEquals(0, testEngi.get_sum_power());
    }

    @Test
    public void setParentNameTestSuccess() {
        String tempParentName = "Headshott";
        testEngi.set_parentsName(tempParentName);
        assertEquals("Headshott", testEngi.get_parentsName());
    }

    @Test
    public void setParentNameTestFail() {
        String tempParentName = "Headshott";
        testEngi.set_parentsName(tempParentName);
        assertNotEquals("Pika", testEngi.get_parentsName());
    }

    @Test
    public void setSpeciesTestSuccess() {
        String tempSpecies = "Dragon";
        testEngi.set_species(tempSpecies);
        assertEquals("Dragon", testEngi.get_species());
    }

    @Test
    public void setSpeciesTestFail() {
        String tempSpecies = "Dragon";
        testEngi.set_species(tempSpecies);
        assertNotEquals("Cat", testEngi.get_species());
    }

    @Test
    public void setElementTestSuccess() {
        String tempElement = "Fire";
        testEngi.set_elements(tempElement);
        assertEquals("Fire", testEngi.get_elements());
    }

    @Test
    public void setElementTestFail() {
        String tempElement = "Fire";
        testEngi.set_elements(tempElement);
        assertNotEquals("Electric", testEngi.get_elements());
    }

    @Test
    public void setLevelTestSuccess() {
        int tempLvl = 7;
        testEngi.set_level(tempLvl);
        assertEquals(7, testEngi.get_level());
    }

    @Test
    public void setLevelTestFail() {
        int tempLvl = 7;
        testEngi.set_level(tempLvl);
        assertNotEquals(1, testEngi.get_level());
    }

    @Test
    public void setExpTestSuccess() {
        int tempExp = 50;
        testEngi.set_exp(tempExp);
        assertEquals(50, testEngi.get_exp());
    }

    @Test
    public void setExpTestFail() {
        int tempExp = 50;
        testEngi.set_exp(tempExp);
        assertNotEquals(0, testEngi.get_exp());
    }

    @Test
    public void setCumExpTestSuccess() {
        int tempCumExp = 500;
        testEngi.set_cumExp(tempCumExp);
        assertEquals(500, testEngi.get_cumExp());
    }

    @Test
    public void setCumExpTestFail() {
        int tempCumExp = 500;
        testEngi.set_cumExp(tempCumExp);
        assertNotEquals(100, testEngi.get_cumExp());
    }

    @Test
    public void setIncExpTestSuccess() {
        assertEquals(0, testEngi.incExp(testEngi.get_level()));
    }

    @Test
    public void setIncExpTestFail() {
        assertNotEquals(1, testEngi.incExp(testEngi.get_level()));
    }

    @Test
    public void setCompareToTestSuccess() {
        assertEquals(1, testEngi.compareTo(testEngi2));
    }

    @Test
    public void setCompareToTestFail() {

        assertNotEquals(-1, testEngi.compareTo(testEngi2));
    }
}