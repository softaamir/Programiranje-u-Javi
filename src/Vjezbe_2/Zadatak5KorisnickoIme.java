/*
 * ZADATAK 5 – Generator korisničkog imena
 * Programiranje u Javi – Auditorne vježbe, Sedmica 2
 *
 * Od imena, prezimena i godine rođenja napravite korisničko ime:
 *   prvo slovo imena + prezime + zadnje dvije cifre godine, sve MALIM slovima
 *
 *   "Amila", "Tabak", 2006   ->   atabak06
 *
 * Koraci:
 *   1) prvo slovo imena:           ime.substring(0, 1)     (indeksi kreću od 0!)
 *   2) godinu pretvorite u tekst:  String godinaTekst = "" + godinaRodjenja;
 *      zadnje dvije cifre:         godinaTekst.substring(2)    -> "06"
 *      (zašto ne godinaRodjenja % 100?  probajte – dobit ćete 6, a ne 06)
 *   3) spojite dijelove i pretvorite u mala slova:  toLowerCase()
 *   4) ispišite korisničko ime i njegovu dužinu:    length()
 *
 * Očekivani ispis:
 *   Korisničko ime: atabak06
 *   Dužina: 8
 *
 * BONUS 1: ispišite inicijale u obliku  A.T.
 * BONUS 2 ("Tajni kod"): u riječi "JAVA" zamijenite prvo i zadnje slovo -> "AAVJ"
 *          (pomoć: charAt(0), charAt(3) i substring(1, 3))
 * BONUS 3: za prezime "Hodžić" napravite korisničko ime bez č, ć, š, ž, đ -> ahodzic06
 *          (pomoć: replace("ž", "z") ...)
 */
public class Zadatak5KorisnickoIme {

    public static void main(String[] args) {
        String ime = "Amila";
        String prezime = "Tabak";
        int godinaRodjenja = 2006;

        // TODO: napravite korisničko ime
        String prvoSlovo = ime.substring(0, 1);
        String godinaTekst = "" + godinaRodjenja;
        String zadnjeDvije = godinaTekst.substring(2);
        String korisnickoIme = (prvoSlovo + prezime + zadnjeDvije).toLowerCase();

        System.out.println("Korisničko ime: " + korisnickoIme);
        // TODO: ispišite dužinu korisničkog imena
        System.out.println("Dužina: " + korisnickoIme.length());

        // BONUS 1: inicijali u obliku A.T.
        String inicijali = ime.substring(0, 1).toUpperCase() + "."
                + prezime.substring(0, 1).toUpperCase() + ".";
        System.out.println("Inicijali: " + inicijali);

        // BONUS 2 ("Tajni kod"): zamjena prvog i zadnjeg slova u riječi JAVA
        String rijec = "JAVA";
        String tajniKod = rijec.charAt(3) + rijec.substring(1, 3) + rijec.charAt(0);
        System.out.println("Tajni kod: " + tajniKod);

        // BONUS 3: korisničko ime bez č, ć, š, ž, đ
        String prezime2 = "Hodžić";
        String korisnickoIme2 = (ime.substring(0, 1) + prezime2 + godinaTekst.substring(2)).toLowerCase();
        korisnickoIme2 = korisnickoIme2.replace("č", "c")
                .replace("ć", "c")
                .replace("š", "s")
                .replace("ž", "z")
                .replace("đ", "d");
        System.out.println("Korisničko ime (bez dijakritika): " + korisnickoIme2);
    }
}