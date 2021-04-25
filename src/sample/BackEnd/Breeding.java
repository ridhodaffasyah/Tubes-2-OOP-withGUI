package sample.BackEnd;

import java.util.*;

public class Breeding {
    public static Engimon makeBreeding(Engimon A, Engimon B){
        if (A.get_level() >= 4 && B.get_level() >= 4) {
            String species = getSpecies(A, B);
            Engimon child = new Engimon(species, 1, new Point(-1,-1));
            child.set_name();
            child.set_species(species);
            setParentName(A, B, child);
            inheritSkill(A, B, child);
            setElement(A, B, child);
            A.set_level(A.get_level() - 3);
            B.set_level(B.get_level() - 3);
            return child;
        }
        else{
            return null;
        }
    }
    public static void setParentName(Engimon A, Engimon B, Engimon C){
        String parentA = A.get_name();
        String parentB = B.get_name();
        String x = parentA + "/";
        C.set_parentsName(x + parentB);
    }
    public static String getSpecies(Engimon A, Engimon B) {
        String childSpecies = "";
        List<String> list1 = Arrays.asList("Dragon", "Cat", "Plant", "Tiger"); // without bird, fish, octopus, worm
        List<String> list2 = Arrays.asList("Dragon", "Cat", "Octopus", "Tiger"); // without worm, bird, fish, plant
        List<String> list3 = Arrays.asList("Bird", "Fish", "Octopus", "Plant", "Worm"); // without tiger, dragon, cat
        if (A.get_elements().equals(B.get_elements())) {
            childSpecies = A.get_species();
        } else {
            switch (A.get_elements()) {
                case "Fire":
                    switch (B.get_elements()) {
                        case "Water":
                            childSpecies = B.get_species();
                            break;
                        case "Electric":
                            childSpecies = list3.get(new Random().nextInt(list3.size()));
                            break;
                        case "Ground":
                            childSpecies = B.get_species();
                            break;
                        case "Ice":
                            childSpecies = A.get_species();
                            break;
                        default:
                            // kombinasi fire dengan engimon dua elemen

                            break;
                    }
                    break;
                case "Water":
                    switch (B.get_elements()) {
                        case "Fire":
                            childSpecies = A.get_species();
                            break;
                        case "Electric":
                            childSpecies = B.get_species();
                            break;
                        case "Ground":
                            childSpecies = list2.get(new Random().nextInt(list2.size()));
                            //C.set_species("Worm");
                            break;
                        case "Ice":
                            childSpecies = list1.get(new Random().nextInt(list1.size()));
                            //C.set_species("Bird");
                            break;
                        default:
                            // kombinasi water dengan engimon dua elemen

                            break;
                    }
                    break;
                case "Electric":
                    switch (B.get_elements()) {
                        case "Fire":
                            childSpecies = list3.get(new Random().nextInt(list3.size()));
                            //C.set_species("Tiger");
                            break;
                        case "Water":
                            childSpecies = A.get_species();
                            break;
                        case "Ground":
                            childSpecies = B.get_species();
                            break;
                        case "Ice":
                            childSpecies = A.get_species();
                            break;
                        default:
                            // kombinasi electric dengan engimon dua element

                            break;
                    }
                    break;
                case "Ground":
                    switch (B.get_elements()) {
                        case "Fire":
                            childSpecies = A.get_species();
                            break;
                        case "Water":
                            childSpecies = list2.get(new Random().nextInt(list2.size()));
                            //C.set_species("Worm");
                            break;
                        case "Electric":
                            childSpecies = A.get_species();
                            break;
                        case "Ice":
                            childSpecies = B.get_species();
                            break;
                        default:
                            // kombinasi ground dengan engimon dua elementt
                            break;
                    }
                    break;
                case "Ice":
                    switch (B.get_elements()) {
                        case "Fire":
                            childSpecies = B.get_species();
                            break;
                        case "Water":
                            childSpecies = list1.get(new Random().nextInt(list1.size()));
                            //C.set_species("Bird");
                            break;
                        case "Electric":
                            childSpecies = B.get_species();
                            break;
                        case "Ground":
                            childSpecies = A.get_species();
                            break;
                        default:
                            // kombinasi Ice dengan engimon dua element
                            break;
                    }
                    break;
            }
        }
        return childSpecies;
    }
    public static void setElement(Engimon A, Engimon B, Engimon C){
        if (A.get_elements().equals(B.get_elements())) {
            C.set_elements(A.get_elements());
        } else {
            switch (A.get_elements()) {
                case "Fire":
                    switch (B.get_elements()) {
                        case "Water":
                            C.set_elements(B.get_elements());
                            break;
                        case "Electric":
                            C.set_elements("Fire/Electric");
                            //C.set_species("Tiger");
                            break;
                        case "Ground":
                            C.set_elements(B.get_elements());
                            break;
                        case "Ice":
                            C.set_elements(A.get_elements());
                            break;
                        default:
                            // kombinasi fire dengan engimon dua elemen

                            break;
                    }
                    break;
                case "Water":
                    switch (B.get_elements()) {
                        case "Fire":
                            C.set_elements(A.get_elements());
                            break;
                        case "Electric":
                            C.set_elements(B.get_elements());
                            break;
                        case "Ground":
                            C.set_elements("Water/Ground");
                            //C.set_species("Worm");
                            break;
                        case "Ice":
                            C.set_elements("Water/Ice");
                            //C.set_species("Bird");
                            break;
                        default:
                            // kombinasi water dengan engimon dua elemen

                            break;
                    }
                    break;
                case "Electric":
                    switch (B.get_elements()) {
                        case "Fire":
                            C.set_elements("Fire/Electric");
                            //C.set_species("Tiger");
                            break;
                        case "Water":
                            C.set_elements(A.get_elements());
                            break;
                        case "Ground":
                            C.set_elements(B.get_elements());
                            break;
                        case "Ice":
                            C.set_elements(A.get_elements());
                            break;
                        default:
                            // kombinasi electric dengan engimon dua element

                            break;
                    }
                    break;
                case "Ground":
                    switch (B.get_elements()) {
                        case "Fire":
                            C.set_elements(A.get_elements());
                            break;
                        case "Water":
                            C.set_elements("Water/Ground");
                            //C.set_species("Worm");
                            break;
                        case "Electric":
                            C.set_elements(A.get_elements());
                            break;
                        case "Ice":
                            C.set_elements(B.get_elements());
                            break;
                        default:
                            // kombinasi ground dengan engimon dua elementt
                            break;
                    }
                    break;
                case "Ice":
                    switch (B.get_elements()) {
                        case "Fire":
                            C.set_elements(B.get_elements());
                            break;
                        case "Water":
                            C.set_elements("Water/Ice");
                            //C.set_species("Bird");
                            break;
                        case "Electric":
                            C.set_elements(B.get_elements());
                            break;
                        case "Ground":
                            C.set_elements(A.get_elements());
                            break;
                        default:
                            // kombinasi Ice dengan engimon dua element
                            break;
                    }
                    break;
            }
        }
    }
    public static void inheritSkill(Engimon A, Engimon B, Engimon C){
        int countA, countB, i;
        i = 1;
        countA = 1;
        countB = 1;
        if (C.get_species().equals(A.get_species()) && !(C.get_species().equals(B.get_species()))) {
            C.getEngiSkill().get(0).setNamaSkill(A.getEngiSkill().get(0).getNamaSkill());
            C.getEngiSkill().get(0).setUnique(A.getEngiSkill().get(0).getUnique());
            C.getEngiSkill().get(0).setBasePower(A.getEngiSkill().get(0).getBasePower());
            C.getEngiSkill().get(0).setMasteryLevel(A.getEngiSkill().get(0).getMasteryLevel());
        } else if (C.get_species().equals(B.get_species()) && !(C.get_species().equals(A.get_species()))) {
            C.getEngiSkill().get(0).setNamaSkill(B.getEngiSkill().get(0).getNamaSkill());
            C.getEngiSkill().get(0).setUnique(B.getEngiSkill().get(0).getUnique());
            C.getEngiSkill().get(0).setBasePower(B.getEngiSkill().get(0).getBasePower());
            C.getEngiSkill().get(0).setMasteryLevel(B.getEngiSkill().get(0).getMasteryLevel());
        } else if (C.get_species().equals(A.get_species()) && C.get_species().equals(B.get_species())) {
            if (A.getEngiSkill().get(0).getMasteryLevel() == B.getEngiSkill().get(0).getMasteryLevel()) {
                C.getEngiSkill().get(0).setNamaSkill(A.getEngiSkill().get(0).getNamaSkill());
                C.getEngiSkill().get(0).setUnique(A.getEngiSkill().get(0).getUnique());
                C.getEngiSkill().get(0).setBasePower(A.getEngiSkill().get(0).getBasePower());
                C.getEngiSkill().get(0).setMasteryLevel(A.getEngiSkill().get(0).getMasteryLevel()+1);
            } else if (A.getEngiSkill().get(0).getMasteryLevel() > B.getEngiSkill().get(0).getMasteryLevel()) {
                C.getEngiSkill().get(0).setNamaSkill(A.getEngiSkill().get(0).getNamaSkill());
                C.getEngiSkill().get(0).setUnique(A.getEngiSkill().get(0).getUnique());
                C.getEngiSkill().get(0).setBasePower(A.getEngiSkill().get(0).getBasePower());
                C.getEngiSkill().get(0).setMasteryLevel(A.getEngiSkill().get(0).getMasteryLevel());
            } else {
                C.getEngiSkill().get(0).setNamaSkill(B.getEngiSkill().get(0).getNamaSkill());
                C.getEngiSkill().get(0).setUnique(B.getEngiSkill().get(0).getUnique());
                C.getEngiSkill().get(0).setBasePower(B.getEngiSkill().get(0).getBasePower());
                C.getEngiSkill().get(0).setMasteryLevel(B.getEngiSkill().get(0).getMasteryLevel());
            }
        }
        while (i < 4) {
            boolean sama = false;
            int j = 1;
            if (A.getEngiSkill().get(countA).getMasteryLevel() > B.getEngiSkill().get(countB).getMasteryLevel()) {
                while(j < 4 && !sama) {
                    if (A.getEngiSkill().get(i).getNamaSkill().equals(C.getEngiSkill().get(j).getNamaSkill())) {
                        sama = true;
                    } j++;
                }
                if (!sama) {
                    C.getEngiSkill().get(i).setNamaSkill(A.getEngiSkill().get(countA).getNamaSkill());
                    C.getEngiSkill().get(i).setUnique(A.getEngiSkill().get(countA).getUnique());
                    C.getEngiSkill().get(i).setBasePower(A.getEngiSkill().get(countA).getBasePower());
                    C.getEngiSkill().get(i).setMasteryLevel(A.getEngiSkill().get(countA).getMasteryLevel());
                }
                countA++;
            } else if (A.getEngiSkill().get(countA).getMasteryLevel() == B.getEngiSkill().get(countB).getMasteryLevel()) {
                while(j < 4 && !sama) {
                    if (A.getEngiSkill().get(i).getNamaSkill().equals(C.getEngiSkill().get(j).getNamaSkill())) {
                        sama = true;
                    } j++;
                }
                if (!sama) {
                    C.getEngiSkill().get(i).setNamaSkill(A.getEngiSkill().get(countA).getNamaSkill());
                    C.getEngiSkill().get(i).setUnique(A.getEngiSkill().get(countA).getUnique());
                    C.getEngiSkill().get(i).setBasePower(A.getEngiSkill().get(countA).getBasePower());
                    if (A.getEngiSkill().get(countA).getNamaSkill().equals(B.getEngiSkill().get(countB).getNamaSkill())) {
                        C.getEngiSkill().get(i).setMasteryLevel(A.getEngiSkill().get(countA).getMasteryLevel()+1);
                    }
                }
                countA++;
            } else {
                while(j < 4 && !sama) {
                    if (B.getEngiSkill().get(i).getNamaSkill().equals(C.getEngiSkill().get(j).getNamaSkill())) {
                        sama = true;
                    } j++;
                }
                if (!sama) {
                    C.getEngiSkill().get(i).setNamaSkill(A.getEngiSkill().get(countA).getNamaSkill());
                    C.getEngiSkill().get(i).setUnique(A.getEngiSkill().get(countA).getUnique());
                    C.getEngiSkill().get(i).setBasePower(A.getEngiSkill().get(countA).getBasePower());
                    C.getEngiSkill().get(i).setMasteryLevel(A.getEngiSkill().get(countA).getMasteryLevel());
                }
                countB++;
            }
            i++;
        }
    }
}