//package sample.UnitTest;
//
//import org.junit.jupiter.api.Test;
//import sample.BackEnd.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class InventoryTest {
//    private Inventory testInventory = new Inventory();
//
//
//    @Test
//    public void deleteThingTestSuccess() {
//        Engimon test = new Engimon();
//        try{
//            testInventory.addThing(test);
//            assertEquals(true, testInventory.deleteThing(test));
//        }catch (Exception e){
//
//        }
//    }
//
//    @Test
//    public void deleteThingTestFail() {
//        Engimon test = new Engimon();
//        testInventory.addThing(test);
//        testInventory.deleteThing(test);
//        assertEquals(false, testInventory.deleteThing(test));
//    }
//
//    @Test
//    public void addThingTestSuccess() {
//        Engimon test = new Engimon();
//        testInventory.addThing(test);
//        assertEquals(test, testInventory.getThings());
//    }
//
//    @Test
//    public void addThingTestFail() {
//        Engimon test = new Engimon();
//        testInventory.addThing(test);
//        testInventory.deleteThing(test);
//        assertEquals(null, testInventory.getThings());
//    }
//}