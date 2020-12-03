import java.io.IOException;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static PlayerList strikerList = new PlayerList();
    private static WriteToFile fileWriter = new WriteToFile();
    private static ReadFromFile fileReader = new ReadFromFile();

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        boolean listON = false;
        int coice;
        while (!listON) {
            showMenu();
            coice = scanner.nextInt();
            scanner.nextLine();
            switch (coice) {
                case 1:

                    fileReader.readFromFile();
                    //printFromFile();


                    break;

                case 2:
                    System.out.println("Lägg till ny spelare.");
                    addNewStriker();
                    fileWriter.createFile();
                    fileWriter.writeDataToFile(strikerList);

                    strikerList.listStriker();
                    break;

                case 3:

                    System.out.println("Uppdatera Spelare");
                    updateStriker();
                    break;

                case 4:

                    System.out.println("Tar bort Spelare.");
                    removeStriker();
                    break;

                case 5:
                    System.out.println("Söker efter spelare");
                    searchStriker();


                    break;

                case 6:
                    System.out.println("Stänger lista.");
                    listON = true;

                    break;
                default:
                    System.out.println("Inget val gjort");
                    break;
            }

        }


    }

    private static void addNewStriker() throws IOException, ClassNotFoundException {
        System.out.print("Skriv in namnet på anfallaren: ");
        String strikerName = scanner.nextLine();
        System.out.print("Skriv in vilket land spelaren kommer ifrån: ");
        String countryName = scanner.nextLine();
        System.out.print("Skriv in vilket klubb som spelaren tillhör: ");
        String teamName = scanner.nextLine();
        System.out.print("Skriv in vilken position spelaren har: ");
        String position = scanner.nextLine();
        int rating = (int) (Math.random() * 100);
        if (rating == 0) {
            rating = 1;
        }

        System.out.print("Skriv in vilken fot som är spelarens bästa: ");
        String bestFoot = scanner.nextLine();

        Striker newStriker = new Striker(strikerName, countryName, teamName, position, rating, bestFoot);
        if (strikerList.addStriker(newStriker)) {
            System.out.println("Ny anfallare tillagd: Namn: " + strikerName + "| " +
                    "land: " + countryName + "| " + "Klubb: " + teamName +
                    " |" + " Position:" + position + ", Rating: " + rating +
                    "| " + " bästa fot: " + bestFoot
            );

        }

    }


    private static void updateStriker() {
        System.out.println("Skriv in vilken anfallare som skall uppdateras: ");
        String strikerName = scanner.nextLine();
        Striker listStriker = strikerList.searchStriker(strikerName);
        if (listStriker == null) {
            System.out.println("Anfallaren finns inte.");
            return;
        }
        System.out.println("Välj vad som skall uppdateras");
        System.out.println("1. Uppdatera Klubb");
        System.out.println("2. Uppdatera Rating");
        System.out.println("3. Uppdatera Land");
        int updateChoice = scanner.nextInt();
        scanner.nextLine();

        switch (updateChoice) {
            case 1:

                System.out.println("Skriv in ny klubb för spelaren");
                String newTeamName = scanner.nextLine();
                Striker updatedStrikerTeam = Striker.createStriker(listStriker.getName(), listStriker.getCountry(), newTeamName,
                        listStriker.getPosition(), listStriker.getRating(), listStriker.getBestFoot());


                if ((strikerList.updateStriker(listStriker, updatedStrikerTeam))) {
                    System.out.println("Spelarens klubb är uppdaterad");

                } else {
                    System.out.println("Gick inte att uppdatera");
                }

                break;

            case 2:
                System.out.println("Uppdatera spelarens Rating");
                int newRating = (int) (Math.random() * 100);
                Striker updatedStrikerRating = Striker.createStriker(listStriker.getName(), listStriker.getCountry(), listStriker.getTeamName(),
                        listStriker.getPosition(), newRating, listStriker.getBestFoot());


                if (strikerList.updateStriker(listStriker, updatedStrikerRating)) {
                    System.out.println("Spelarens rating är uppdaterad");
                } else {
                    System.out.println("Gick inte att uppdatera");
                }
                break;

            case 3:
                System.out.println("Skriv in nytt land för spelaren");
                String newCountry = scanner.nextLine();
                Striker updatedStrikerCountry = Striker.createStriker(listStriker.getName(), newCountry, listStriker.getTeamName(),
                        listStriker.getPosition(), listStriker.getRating(), listStriker.getBestFoot());

                if (strikerList.updateStriker(listStriker, updatedStrikerCountry)) {
                    System.out.println("Spelarens land är uppdaterad");
                } else {
                    System.out.println("Gick inte att uppdatera");
                    break;

                }

        }


    }

    private static void removeStriker() {
        System.out.println("Skriv vilken spelare som du vill ta bort.");
        String strikerName = scanner.nextLine();
        Striker strikerInList = strikerList.searchStriker(strikerName);
        if (strikerInList == null) {
            System.out.println("Spelaren finns inte.");
            return;
        }
        if (strikerList.removeStriker(strikerInList)) {
            System.out.println("Spelaren har tagits bort.");
        } else {
            System.out.println("Kan inte ta bort spelaren.");
        }

    }


    private static void searchStriker() {
        System.out.println("Vilken spelare letar du efter?");
        String strikerName = scanner.nextLine();
        Striker strikerInList = strikerList.searchStriker(strikerName);
        if (strikerInList == null) {
            System.out.println("Spelaren finns inte.");
        } else {
            System.out.println("Name: " + strikerInList.getName() + " Land: " +
                    strikerInList.getCountry() + " Klubb: " + strikerInList.getTeamName() +
                    " Postition: " + strikerInList.getPosition() + " Rating: " + strikerInList.getRating() +
                    " Bästa fot: " + strikerInList.getBestFoot()
            );
        }


    }

    private static void showMenu() {
        System.out.println("Gör ett val:");
        System.out.println("1.Visa spelarlista. " +
                "\n2.Lägg till ny spelare. " +
                "\n3.Uppdatera spelare." +
                "\n4.Tar bort spelare. " +
                "\n5.Sök efter spelare." +
                "\n6.Stänger Lista");
    }


}
