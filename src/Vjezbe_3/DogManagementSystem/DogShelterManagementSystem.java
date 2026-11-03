import java.util.ArrayList;
import java.util.Scanner;

public class DogShelterManagementSystem {

    public static void main(String[] args) {
        ArrayList<Dog> psi = new ArrayList<>();
        Scanner input = new Scanner(System.in);

        int unos;

        do {
            System.out.println("\n============= MENI =============");
            System.out.println("1 - Unesi novog cuku");
            System.out.println("2 - Izlistaj sve cuke");
            System.out.println("3 - Udomi psa");
            System.out.println("4 - Pretraga po imenu");
            System.out.println("5 - Izlaz");
            System.out.println("================================");
            System.out.print("Unesi opciju: ");

            unos = input.nextInt();
            input.nextLine(); // uklanja Enter nakon nextInt()

            switch (unos) {
                case 1:
                    Dog noviPas = new Dog();

                    noviPas.unosCuka(input);
                    psi.add(noviPas);

                    System.out.println("Pas je uspješno unesen.");
                    break;

                case 2:
                    if (psi.isEmpty()) {
                        System.out.println("Nema unesenih pasa.");
                    } else {
                        System.out.println("\nPodaci o psima:");

                        for (int i = 0; i < psi.size(); i++) {
                            Dog pas = psi.get(i);

                            System.out.println("\nPas broj " + pas.getID());
                            System.out.println("Ime: " + pas.getIme());
                            System.out.println("Dob: " + pas.getDob());
                            System.out.println("Pasmina: " + pas.getPasmina());
                        }
                    }
                    break;

                case 3:
                    if (psi.isEmpty()) {
                        System.out.println("Nema pasa za udomljavanje.");
                    } else {
                        System.out.println("\nPsi koje možete udomiti:");

                        for (int i = 0; i < psi.size(); i++) {
                            System.out.println(
                                    (i + 1) + " - " + psi.get(i).getIme()
                            );
                        }

                        System.out.print("Unesite broj psa kojeg želite udomiti: ");
                        int brojPsa = input.nextInt();
                        input.nextLine();

                        if (brojPsa >= 1 && brojPsa <= psi.size()) {
                            Dog udomljeniPas = psi.remove(brojPsa - 1);

                            System.out.println(
                                    "Pas " + udomljeniPas.getIme()
                                            + " je uspješno udomljen."
                            );
                        } else {
                            System.out.println("Neispravan broj psa.");
                        }
                    }
                    break;
                case 4:
                    if (psi.isEmpty()) {
                        System.out.println("Nema unesenih pasa.");
                    }else{
                        String pretraga;
                        System.out.println("Unesite psa kojeg zelite da pretrazite:");
                        pretraga = input.nextLine();
                        boolean pronaden=false;
                        for(int i = 0; i < psi.size(); i++){
                            Dog pas = psi.get(i);

                            if(pretraga.equals(psi.get(i).getIme())){
                                System.out.println("\nPas broj " + pas.getID());
                                System.out.println("Ime: " + pas.getIme());
                                System.out.println("Dob: " + pas.getDob());
                                System.out.println("Pasmina: " + pas.getPasmina());
                            pronaden=true;
                            }

                        }
                        if(pronaden==false){
                            System.out.println("Pas kojeg ste unijeli ne postoji!!");
                        }

                    }
                    break;
                case 5:
                    System.out.println("Program je završen.");
                    break;

                default:
                    System.out.println("Nepoznata opcija.");
            }

        } while (unos != 0);

        input.close();
    }

    public static class Dog {
        private static int brojac=0;
        private int id;
        private String ime;
        private int dob;
        private String pasmina;

        public Dog() {
            brojac++;
            id= brojac;
        }

        public Dog(String ime, int dob, String pasmina) {
            this.ime = ime;
            this.dob = dob;
            this.pasmina = pasmina;
        }

        public String getIme() {
            return ime;
        }

        public void setIme(String ime) {
            this.ime = ime;
        }

        public int getDob() {
            return dob;
        }

        public void setDob(int dob) {
            this.dob = dob;
        }

        public String getPasmina() {
            return pasmina;
        }
        public int getID(){
            return id;
        }
        public void setPasmina(String pasmina) {
            this.pasmina = pasmina;
        }

        public void unosCuka(Scanner input) {
            System.out.print("Unesite ime cuke: ");
            this.ime = input.nextLine();
            System.out.print("Unesite dob cuke: ");
            this.dob = input.nextInt();

            input.nextLine();

            System.out.println("Unesite pasminu: ");
            System.out.println("1 - Labrador");
            System.out.println("2 - Njemački ovčar");
            System.out.println("3 - Pudla");
            System.out.println("4 - Haski");
            System.out.println("5 - Mjesanac");
            System.out.println("Vas izbor:");
            int izborPasmine=input.nextInt();
            input.nextLine();
            switch (izborPasmine) {
                case 1:
                    this.pasmina = "Labrador";
                    break;
                case 2:
                    this.pasmina="Njemacki ovcar";
                    break;
                case 3:
                    this.pasmina="Pudla";
                    break;
                    case 4:
                    this.pasmina="Haski";
                    break;
                    case 5:
                    this.pasmina="Mjesanac";
                    break;
                    default:
                        System.out.println("Neispravan broj pasa.");
                        break;
            }

        }
    }
}