/*
 * ZADATAK 1 – Vizit karta
 * Programiranje u Javi – Auditorne vježbe, Sedmica 2
 *
 * Napišite program koji ispisuje vašu vizit kartu, na primjer:
 *
 *   ==============================
 *           VIZIT KARTA
 *   ==============================
 *   Ime:    Amar Hodžić
 *   Studij: Informatika
 *   Godina: 1.
 *   Moto:   "Kod je poezija."
 *   ==============================
 *
 * Uslovi:
 *   1) bar jednom koristite print (bez prelaska u novi red), a ne samo println
 *   2) za poravnanje koristite tabulator  \t
 *   3) moto mora biti pod navodnicima  ->  \"
 *
 * BONUS: ispišite cijelu karticu JEDNOM naredbom println (koristite \n).
 */
public class Zadatak1VizitKarta {

    public static void main(String[] args) {
        // TODO: ispišite naslov i ostale redove vizit karte
        System.out.println("==============================\n" +
                "\t\t VIZIT KARTA\n" +
                "==============================\n" +
                "Ime:    Amar Hodžić\n" +
                "Studij: Informatika\n" +
                "Godina: 1.\n" +
                "Moto:   \"Kod je poezija.\"\n" +
                "==============================");
    }
}
