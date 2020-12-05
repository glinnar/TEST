import java.io.FileInputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class ReadFromFile {




    /*
    * FileInputStream fin = new FileInputStream("test.txt");
            ObjectInputStream in = new ObjectInputStream(fin);
            list2 = (ArrayList<Contact>)in.readObject();
            System.out.println(list2 +"\n");
            in.close();*/


    public PlayerList readFromFile() throws IOException, ClassNotFoundException {

        ArrayList<Striker> slist = new ArrayList<>();
        PlayerList striker = null;

        try {
            FileInputStream fin = new FileInputStream("StrikerList.txt");
            ObjectInputStream in = new ObjectInputStream(fin);
            //list = (PlayerList) in.readObject();



            striker =(PlayerList) in.readObject();




           // list.listStriker();

            in.close();
            fin.close();


        } catch (InvalidObjectException e){
            e.printStackTrace();
            System.out.println("Något gick fel");
        }

        return striker;

    }

}