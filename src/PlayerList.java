import java.io.Serializable;
import java.util.ArrayList;

public class PlayerList implements Serializable {
    private ArrayList<Striker> strikers;


    public PlayerList() {
        this.strikers = new ArrayList<>();
    }

    // Skriver ut den vanliga listan.

    public void listStriker() {
        System.out.println("ANFALLARE ______");
        System.out.println(" Namn " + " | " + " Land" + " | " + " KLubb " + " | "
                + " Position " + " | " + " Rating " + " | " + " Bästa fot ");

        System.out.println("___________________________________________" +
                "___________________");
        for (int i = 0; i < this.strikers.size(); i++) {
            System.out.println((i + 1) + ". " +
                    this.strikers.get(i).getName() + " | " +
                    this.strikers.get(i).getCountry() + " | " +
                    this.strikers.get(i).getTeamName() + " | " +
                    this.strikers.get(i).getPosition() + " | " +
                    this.strikers.get(i).getRating() + " | " +
                    this.strikers.get(i).getBestFoot()
            );

            System.out.println("___________________________________________" +
                    "___________________");

        }


    }
    // Lägger till en spelare i den vanliga listan.

    public boolean addStriker(Striker striker) {
        if (getStriker(striker.getName()) >= 0) {
            System.out.println("Spelaren finns redan i listan");
            return false;
        }
        strikers.add(striker);
        return true;

    }


    // Returnerar index platsen på objektet som skickas in i den vanliga listan.
    private int getStriker(Striker striker) {
        return this.strikers.indexOf(striker);
    }

    private int getStriker(String strikerName) {
        for (int i = 0; i < this.strikers.size(); i++) {
            Striker striker = this.strikers.get(i);
            if (striker.getName().equals(strikerName)) {
                return i;
            }
        }
        return -1;
    }


    public boolean updateStriker(Striker striker, Striker upDatedStriker) {
        int listPosition = getStriker(striker);
        if (listPosition < 0) {
            System.out.println(striker.getName() + ",finns inte i listan");
            return false;
        }
        this.strikers.set(listPosition, upDatedStriker);

        System.out.println(striker.getName() + ", har uppdaterats");
        return true;

    }



    public boolean removeStriker(Striker striker) {
        int listPosition = getStriker(striker);
        if (listPosition < 0) {
            System.out.println(striker.getName() + " finns inte i listan");
            return false;
        }
        this.strikers.remove(listPosition);
        System.out.println(striker.getName() + " har tagits bort");
        return true;
    }



    public Striker searchStriker(String strikerName) {
        int listPosition = getStriker(strikerName);
        if (listPosition >= 0) {
            return this.strikers.get(listPosition);
        }
        return null;
    }


}

