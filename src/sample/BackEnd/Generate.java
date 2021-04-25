

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class Generate {
    public static Vector<Vector<String>> readFromFile(String nameOfFile) throws IOException {
        Vector<Vector<String>> retval = new Vector<Vector<String>>();
        try {
            Scanner scanFile = new Scanner(new File("src/" + nameOfFile));
            
            String line;
            while(scanFile.hasNextLine()){
                Vector<String> tempVec = new Vector<String>();
                line = scanFile.nextLine();
                tempVec.add(line);
                boolean stopIterate = false;
                while(scanFile.hasNextLine() && !stopIterate){
                    line=scanFile.nextLine();
                    
                    if (line.equals("")){
                        stopIterate = true;
                    }else{
                        tempVec.add(line);
                    }
                }
                retval.add(tempVec);
                tempVec = null;
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return retval;
    }
    public static void main(String[] args) throws IOException {
        Vector<Vector<String>> temp = readFromFile("Engimon.txt");
        System.out.println(temp);
    }
}
