package sample.UnitTest;

import org.junit.jupiter.api.Test;
import sample.BackEnd.*;

import static org.junit.jupiter.api.Assertions.*;

public class BreedingTest {
    private Engimon Parent1 = new Engimon("Cat", 30, new Point(1,2));
    private Engimon Parent2 = new Engimon("Fish", 30, new Point(2,2));

    @Test
    public void makeBreedingTestSuccess() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertNotNull(child);
    }

    @Test
    public void makeBreedingTestFail() {
        Parent1.set_level(2);
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertNull(child);
    }

    @Test
    public void getSpeciesTestSucces() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertEquals("Cat", child.get_species());
    }

    @Test
    public void getSpeciesTestFail() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertNotEquals("Dragon", child.get_species());
    }

    @Test
    public void setParentNameTestSuccess() {
        Engimon tempParent = new Engimon();
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(tempParent, Parent2);
        assertEquals("Pikachu/NONE", child.get_parentsName());
    }

    @Test
    public void setParentNameTestFail() {
        Engimon tempParent = new Engimon();
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(tempParent, Parent2);
        assertNotEquals("NONE/NONE", child.get_parentsName());
    }

    @Test
    public void setElementTestSuccess() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertEquals("Electric", child.get_elements());
    }

    @Test
    public void setElementTestFail() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertNotEquals("Fire", child.get_elements());
    }

    @Test
    public void inheritSkillTestSuccess() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertEquals(Parent1.getEngiSkill().get(0), child.getEngiSkill().get(0));
    }

    @Test
    public void inheritSkillTestFail() {
        Breeding Breed = new Breeding();
        Engimon child = Breed.makeBreeding(Parent1, Parent2);
        assertNotEquals(Parent1.getEngiSkill().get(0), child.getEngiSkill().get(0));
    }
}
