/*
 * ZADATAK 2 – Kartica lika za igru
 * Programiranje u Javi – Auditorne vježbe, Sedmica 2
 *
 * Napravite varijable za lik iz igre. Za svaku izaberite ODGOVARAJUĆI tip!
 *   - ime lika                          npr. Aria
 *   - klasa lika kao JEDAN znak         npr. 'M' (mag), 'R' (ratnik), 'S' (strijelac)
 *   - nivo                              npr. 7
 *   - zdravlje                          npr. 120
 *   - preciznost                        npr. 0.85
 *   - da li lik ima štit                da / ne
 *   - ukupno iskustvo (XP)              3 500 000 000   <- pažnja: prevelik broj za int!
 *   - konstanta MAX_NIVO = 50           (koristite final)
 *
 * Zatim ispišite karticu, na primjer:
 *
 *   *** Aria (M) ***
 *   Nivo: 7 / 50
 *   Zdravlje: 120
 *   Preciznost: 0.85
 *   Ima štit: false
 *   Iskustvo: 3500000000 XP
 *
 * EKSPERIMENT: pokušajte u int varijablu upisati 3.5, a u char "M" (dvostruki navodnici).
 *              Pročitajte poruku o grešci – šta vam IntelliJ kaže?
 */
public class Zadatak2KarticaLika {

    public static void main(String[] args) {
        // TODO 1: deklarišite i inicijalizirajte varijable
        String Ime = "Aria";
        char Klasa = 'M';
        int Nivo = 7;
        int Zdravlje = 120;
        double preciznost = 0.85;
        boolean ima_štit = false;
        long xp = 3500000000L;
        final int MAX_NIVO = 50;

        System.out.println(" *   *** " + Ime + "("+Klasa+") ***\n" +
                        " *   Nivo: " + Nivo + "\n" +
                        " *   Zdravlje: " + Zdravlje + "\n" +
                        " *   Preciznost: " + preciznost + "\n" +
                        " *   Ima štit: "+ima_štit +  "\n" +
                        " *   Iskustvo: "+ xp  +" XP");



        // TODO 2: ispišite karticu lika

    }
}
