import java.util.Random;
import java.util.Scanner;
import java.util.function.Supplier;

/*
 * =====================================================================
 *    A R E N A   -   igra na poteze u konzoli
 *    Programiranje u Javi - Auditorne vježbe, Sedmica 2 (završni zadatak)
 * =====================================================================
 *
 * Vaš heroj ulazi u arenu i bori se protiv tri čudovišta: Goblin -> Ork -> Zmaj.
 * Motor igre (meni, petlje, slučajni brojevi, crtanje) je već napisan.
 * VI pišete formule koje igra koristi - svaki TODO je samo JEDNA linija koda!
 *
 * KAKO RADITI:
 *   1) Pokrenite program (zelena strelica pored main) i pogledajte PROVJERU na vrhu ispisa:
 *      [--] znači da TODO još nije riješen, a [OK] da je riješen tačno.
 *   2) Rješavajte TODO 1 - TODO 9 redom (ZONA ZA STUDENTE, odmah ispod).
 *      U svakoj metodi promijenite SAMO liniju označenu sa  // <-- TODO
 *   3) Ponovo pokrenite program i provjerite da je TODO dobio [OK].
 *   4) Kad je sve [OK] - pobijedite Zmaja! Zatim probajte BONUS zadatke.
 *
 * Šta je metoda? Za sada je zamislite kao malu "mašinu": u zagradi su ULAZI
 * (npr. napad i odbrana), a iza riječi return je IZLAZ - rezultat koji motor igre koristi.
 * Metode detaljno učimo narednih sedmica.
 */
public class Arena {

    // =====================================================================
    //  ZONA ZA STUDENTE  -  ovdje radite (TODO 1 - TODO 9, pa BONUS 1 i 2)
    // =====================================================================

    // TODO 1 - Da li je lik živ?
    //   Lik je živ ako mu je zdravlje VEĆE od 0.
    //   Primjer: jeZiv(35) -> true      jeZiv(0) -> false      jeZiv(-4) -> false
    //   Koncept: relacijski operatori  >  <  >=  <=  ==  !=
    static boolean jeZiv(int zdravlje) {
        boolean ziv = zdravlje > 0;                        // <-- TODO 1: zamijenite true ispravnim izrazom
        return ziv;
    }

    // TODO 2 - Osnovna šteta udarca
    //   Šteta = napad napadača MINUS POLOVINA odbrane branioca.
    //   Primjer: osnovnaSteta(20, 10) -> 20 - 10 / 2 = 15
    //            osnovnaSteta(14, 7)  -> 14 - 7 / 2  = 14 - 3 = 11   (cjelobrojno dijeljenje!)
    //   Koncept: aritmetički operatori i prioritet (dijeljenje se računa prije oduzimanja)
    static int osnovnaSteta(int napad, int odbrana) {
        int steta = napad - odbrana / 2;                             // <-- TODO 2: zamijenite 0 formulom
        return steta;
    }

    // TODO 3 - Kritični udarac
    //   Kritični udarac nanosi 1.5 puta veću štetu. Rezultat mora biti cijeli broj (int).
    //   Primjer: kriticnaSteta(15) -> 22      (15 * 1.5 = 22.5 -> 22)
    //   Koncept: eksplicitna konverzija (cast) double -> int
    static int kriticnaSteta(int steta) {
        int kriticna = (int) (steta * 1.5);                      // <-- TODO 3
        return kriticna;
    }

    // TODO 4 - Zdravlje nakon udarca
    //   Novo zdravlje = zdravlje - šteta, ali NIKAD manje od 0.
    //   Primjer: zdravljeNakonUdarca(50, 18) -> 32      zdravljeNakonUdarca(10, 25) -> 0
    //   Koncept: Math.max(a, b) vraća veći od dva broja
    static int zdravljeNakonUdarca(int zdravlje, int steta) {
        int novoZdravlje = Math.max(0, zdravlje - steta);               // <-- TODO 4
        return novoZdravlje;
    }

    // TODO 5 - Napitak
    //   Napitak vraća 35% MAKSIMALNOG zdravlja, ali zdravlje ne smije preći maksimum.
    //   Primjer: zdravljeNakonNapitka(50, 120)  -> 50 + 42 = 92
    //            zdravljeNakonNapitka(100, 120) -> 120   (a ne 142!)
    //   Koncept: cjelobrojno dijeljenje (PAZITE: 35 / 100 = 0 !) i Math.min(a, b)
    static int zdravljeNakonNapitka(int zdravlje, int maxZdravlje) {
        int novoZdravlje = Math.min(maxZdravlje, zdravlje + maxZdravlje * 35 / 100);               // <-- TODO 5
        return novoZdravlje;
    }

    // TODO 6 - Kritično zdravlje
    //   Zdravlje je kritično ako je lik ŽIV  I  zdravlje je najviše četvrtina maksimuma.
    //   Primjer: (20, 100) -> true    (25, 100) -> true    (26, 100) -> false    (0, 100) -> false
    //   Koncept: logički operator && (i). Smijete koristiti i metodu jeZiv iz TODO 1!
    static boolean jeKriticno(int zdravlje, int maxZdravlje) {
        boolean kriticno = jeZiv(zdravlje) && zdravlje <= maxZdravlje / 4;   // <-- TODO 6
        return kriticno;
    }

    // TODO 7 - Lijepo ime heroja
    //   Prvo slovo veliko, sva ostala mala.
    //   Primjer: formatirajIme("aMAR") -> "Amar"      formatirajIme("selma") -> "Selma"
    //   Pomoć: ime.substring(0, 1) je prvo slovo, a ime.substring(1) je sve ostalo
    //   Koncept: String metode toUpperCase(), toLowerCase(), substring() i spajanje sa +
    static String formatirajIme(String ime) {
        String lijepoIme = ime.substring(0, 1).toUpperCase() + ime.substring(1).toLowerCase();   // <-- TODO 7
        return lijepoIme;
    }

    // TODO 8 - Nivo iz iskustva
    //   Heroj počinje na nivou 1, a svakih punih 100 XP donosi novi nivo.
    //   Primjer: 0 -> 1      99 -> 1      100 -> 2      250 -> 3
    //   Koncept: cjelobrojno dijeljenje
    static int nivoIzIskustva(int iskustvo) {
        int nivo = 1 + iskustvo / 100;             // <-- TODO 8
        return nivo;
    }

    // TODO 9 - Konačni bodovi
    //   bodovi = pobjede * 1000 + preostaloZdravlje * 10 + preostaliNapici * 100 - brojPoteza * 5
    //   Primjer: izracunajBodove(3, 45, 1, 30) -> 3000 + 450 + 100 - 150 = 3400
    //   Koncept: složeni aritmetički izraz
    static int izracunajBodove(int pobjede, int preostaloZdravlje, int preostaliNapici, int brojPoteza) {
        int bodovi = pobjede * 1000 + preostaloZdravlje * 10 + preostaliNapici * 100 - brojPoteza * 5;   // <-- TODO 9
        return bodovi;
    }

    // BONUS 1 - Koliko XP fali do sljedećeg nivoa?
    //   Primjer: 0 -> 100      30 -> 70      250 -> 50
    //   Koncept: operator % (ostatak pri dijeljenju)
    static int xpDoSljedecegNivoa(int iskustvo) {
        int fali = 100 - iskustvo % 100;           // <-- BONUS 1
        return fali;
    }

    // BONUS 2 - Šansa za kritični udarac raste sa nivoom
    //   Na nivou 1 šansa je 0.20 (20%), a svaki sljedeći nivo dodaje još 0.05.
    //   Primjer: sansaKriticnog(1) -> 0.20      sansaKriticnog(3) -> 0.30
    //   Koncept: izrazi sa double i int vrijednostima
    static double sansaKriticnog(int nivo) {
        double sansa = 0.20 + (nivo - 1) * 0.05;   // <-- BONUS 2
        return sansa;
    }

    // =====================================================================
    //  POSTAVKE IGRE  -  slobodno mijenjajte brojeve i balansirajte igru (BONUS 3)
    // =====================================================================
    static final int HEROJ_ZDRAVLJE = 100;
    static final int HEROJ_NAPAD = 20;
    static final int HEROJ_ODBRANA = 10;
    static final int BROJ_NAPITAKA = 3;
    static final double SANSA_PROMASAJA = 0.40;  // snažan udarac promaši u 40% slučajeva
    static final int MAX_RUNDI = 30;             // sudija prekida predugu borbu
    static final long SJEME = 0;                 // 0 = svaka igra je drugačija; npr. 42 = uvijek ista igra

    //                                        Goblin    Ork    Zmaj
    static final String[] CUDOVISTA      = { "Goblin", "Ork", "Zmaj" };
    static final int[]    CUD_ZDRAVLJE   = {     55,     95,    150 };
    static final int[]    CUD_NAPAD      = {     14,     20,     28 };
    static final int[]    CUD_ODBRANA    = {      6,     10,     14 };
    static final int[]    CUD_ISKUSTVO   = {    100,    150,    200 };

    // =====================================================================
    //  MOTOR IGRE  -  ne morate mijenjati ništa ispod ove linije :)
    //  (if-else, petlje, nizovi, Scanner, Random i metode učimo narednih sedmica)
    // =====================================================================

    static final Scanner ulaz = new Scanner(System.in);
    static final Random kocka = (SJEME == 0) ? new Random() : new Random(SJEME);

    public static void main(String[] args) {
        prikaziNaslov();
        provjeriZadatke();

        System.out.print("Kako se zove tvoj heroj? ");
        String unos = procitajLiniju();
        if (unos.isEmpty()) {
            unos = "heroj";
        }
        String ime;
        try {
            ime = formatirajIme(unos);
        } catch (RuntimeException e) {
            ime = unos;                                  // TODO 7 baca grešku -> koristimo ime kako je upisano
        }

        int nivo = 1;
        int iskustvo = 0;
        int maxZdravlje = HEROJ_ZDRAVLJE;
        int zdravlje = HEROJ_ZDRAVLJE;
        int napad = HEROJ_NAPAD;
        int odbrana = HEROJ_ODBRANA;
        int napici = BROJ_NAPITAKA;
        int pobjede = 0;
        int ukupnoPoteza = 0;
        boolean predaja = false;
        boolean prekid = false;

        prikaziKarticu(ime, nivo, iskustvo, zdravlje, maxZdravlje, napad, odbrana, napici);

        for (int i = 0; i < CUDOVISTA.length; i++) {
            String cIme = CUDOVISTA[i];
            int cMax = CUD_ZDRAVLJE[i];
            int cZdravlje = cMax;
            int cNapad = CUD_NAPAD[i];
            int cOdbrana = CUD_ODBRANA[i];

            System.out.println();
            System.out.println(linija('='));
            System.out.println("  BORBA " + (i + 1) + " / " + CUDOVISTA.length + ":  "
                    + ime + "  protiv  " + cIme.toUpperCase());
            System.out.println(linija('='));
            prikaziSliku(i);
            System.out.println("  " + cIme + ":  zdravlje " + cMax + ",  napad " + cNapad
                    + ",  odbrana " + cOdbrana);

            int runda = 1;
            while (jeZiv(zdravlje) && jeZiv(cZdravlje) && runda <= MAX_RUNDI) {
                prikaziStanje(runda, ime, nivo, zdravlje, maxZdravlje, napici, cIme, cZdravlje, cMax);
                String izbor = procitajLiniju();
                if (izbor.equals("0")) {
                    predaja = true;
                    break;
                }
                ukupnoPoteza++;

                // ---------- potez heroja ----------
                if (izbor.equals("1")) {
                    int steta = nasumicnaSteta(osnovnaSteta(napad, cOdbrana));
                    if (kocka.nextDouble() < sansaKriticnog(nivo)) {
                        steta = kriticnaSteta(steta);
                        System.out.println(">> KRITIČNI UDARAC! " + cIme + " prima " + steta + " štete!");
                    } else {
                        System.out.println(">> " + ime + " napada. " + cIme + " prima " + steta + " štete.");
                    }
                    cZdravlje = zdravljeNakonUdarca(cZdravlje, steta);
                } else if (izbor.equals("2")) {
                    if (kocka.nextDouble() < SANSA_PROMASAJA) {
                        System.out.println(">> Snažan udarac je PROMAŠIO!");
                    } else {
                        int steta = 2 * nasumicnaSteta(osnovnaSteta(napad, cOdbrana));
                        System.out.println(">> SNAŽAN UDARAC! " + cIme + " prima " + steta + " štete!");
                        cZdravlje = zdravljeNakonUdarca(cZdravlje, steta);
                    }
                } else if (izbor.equals("3")) {
                    if (napici > 0) {
                        int prije = zdravlje;
                        zdravlje = zdravljeNakonNapitka(zdravlje, maxZdravlje);
                        napici--;
                        System.out.println(">> Ispijaš napitak: +" + (zdravlje - prije) + " zdravlja.");
                    } else {
                        System.out.println(">> Nema više napitaka! Potez je izgubljen.");
                    }
                } else {
                    System.out.println(">> Nepoznata komanda \"" + izbor + "\" - potez je izgubljen.");
                }

                // ---------- potez čudovišta ----------
                if (jeZiv(cZdravlje)) {
                    int steta = nasumicnaSteta(osnovnaSteta(cNapad, odbrana));
                    if (kocka.nextDouble() < 0.10) {
                        steta = kriticnaSteta(steta);
                        System.out.println("<< " + cIme + " zadaje KRITIČNI udarac: " + steta + " štete!");
                    } else {
                        System.out.println("<< " + cIme + " napada. Primaš " + steta + " štete.");
                    }
                    zdravlje = zdravljeNakonUdarca(zdravlje, steta);
                }
                runda++;
            }

            if (predaja) {
                break;
            }
            if (!jeZiv(zdravlje)) {
                break;                                   // heroj je pao
            }
            if (jeZiv(cZdravlje)) {
                prekid = true;                           // isteklo je MAX_RUNDI rundi
                break;
            }

            // ---------- pobjeda nad čudovištem ----------
            pobjede++;
            iskustvo += CUD_ISKUSTVO[i];
            System.out.println();
            System.out.println("*** " + cIme + " je poražen!  +" + CUD_ISKUSTVO[i] + " XP ***");

            int noviNivo = nivoIzIskustva(iskustvo);
            if (noviNivo > nivo) {
                int razlika = noviNivo - nivo;
                nivo = noviNivo;
                maxZdravlje += 15 * razlika;
                napad += 3 * razlika;
                odbrana += 2 * razlika;
                System.out.println("*** NOVI NIVO " + nivo + "!  Max zdravlje +" + (15 * razlika)
                        + ", napad +" + (3 * razlika) + ", odbrana +" + (2 * razlika) + " ***");
            }

            if (i < CUDOVISTA.length - 1) {
                int prije = zdravlje;
                zdravlje = Math.min(maxZdravlje, zdravlje + maxZdravlje * 30 / 100);
                System.out.println("Odmor između borbi: +" + (zdravlje - prije) + " zdravlja.");
                System.out.println("XP: " + iskustvo + "   (do sljedećeg nivoa: "
                        + xpDoSljedecegNivoa(iskustvo) + " XP)");
                prikaziKarticu(ime, nivo, iskustvo, zdravlje, maxZdravlje, napad, odbrana, napici);
                System.out.print("Pritisni ENTER za sljedeću borbu...");
                procitajLiniju();
            }
        }

        prikaziKraj(ime, pobjede, zdravlje, napici, ukupnoPoteza, predaja, prekid);
    }

    // ----------------------------- pomoćne metode motora -----------------------------

    static int nasumicnaSteta(int steta) {
        if (steta <= 0) {
            return 0;                                    // TODO 2 još nije riješen
        }
        return Math.max(1, steta + kocka.nextInt(5) - 2);   // +-2 za malo slučajnosti
    }

    static String procitajLiniju() {
        if (!ulaz.hasNextLine()) {                       // nema više unosa -> predaja
            System.out.println();
            return "0";
        }
        return ulaz.nextLine().trim();
    }

    static String linija(char znak) {
        return String.valueOf(znak).repeat(66);
    }

    static String skrati(String tekst, int duzina) {
        return tekst.length() > duzina ? tekst.substring(0, duzina) : tekst;
    }

    static String traka(int zdravlje, int max) {
        int procenat = Math.max(0, Math.min(100, zdravlje * 100 / max));
        int puno = procenat / 5;                         // 20 polja, svako = 5%
        return "[" + "#".repeat(puno) + "-".repeat(20 - puno) + "]";
    }

    static void prikaziNaslov() {
        System.out.println();
        System.out.println("      _    ____  _____ _   _    _");
        System.out.println("     / \\  |  _ \\| ____| \\ | |  / \\");
        System.out.println("    / _ \\ | |_) |  _| |  \\| | / _ \\");
        System.out.println("   / ___ \\|  _ <| |___| |\\  |/ ___ \\");
        System.out.println("  /_/   \\_\\_| \\_\\_____|_| \\_/_/   \\_\\");
        System.out.println();
        System.out.println("  Igra na poteze  -  Programiranje u Javi, Sedmica 2");
        System.out.println();
    }

    static void prikaziKarticu(String ime, int nivo, int iskustvo, int zdravlje, int maxZdravlje,
                               int napad, int odbrana, int napici) {
        String okvir = "  +" + "-".repeat(48) + "+";
        System.out.println(okvir);
        System.out.printf("  | %-46s |%n", "HEROJ: " + skrati(ime, 18) + "   (nivo " + nivo + ", XP " + iskustvo + ")");
        System.out.printf("  | %-46s |%n", "Zdravlje: " + zdravlje + "/" + maxZdravlje + "    Napici: " + napici);
        System.out.printf("  | %-46s |%n", "Napad: " + napad + "    Odbrana: " + odbrana);
        System.out.println(okvir);
    }

    static final String[][] SLIKE = {
        { // Goblin
            "    \\\\           //",
            "     \\\\ .-----. //",
            "      \\( o   o )/",
            "       |   ^   |",
            "       | \\___/ |",
            "        '-----'"
        },
        { // Ork
            "      .---------.",
            "     /  \\     /  \\",
            "    |   (O) (O)   |",
            "    |      ^      |",
            "    |  |\\_____/|  |",
            "     \\___________/"
        },
        { // Zmaj
            "   /\\                 /\\",
            "  /  \\    .-----.    /  \\",
            " / /\\ \\  /  o o  \\  / /\\ \\",
            " \\/  \\ \\(  (___)  )/ /  \\/",
            "      \\_\\  \\vvv/  /_/",
            "          '-----'"
        }
    };

    static void prikaziSliku(int indeks) {
        if (indeks < SLIKE.length) {
            for (String red : SLIKE[indeks]) {
                System.out.println("        " + red);
            }
        } else {
            System.out.println("        ( ? ? )   <- novo čudovište nema sliku");
        }
    }

    static void prikaziStanje(int runda, String ime, int nivo, int zdravlje, int maxZdravlje, int napici,
                              String cIme, int cZdravlje, int cMax) {
        System.out.println();
        System.out.println("-------------------- RUNDA " + runda + " --------------------");
        System.out.printf("  %-10s %s %3d/%-3d   nivo %d%n", skrati(ime, 10), traka(zdravlje, maxZdravlje),
                zdravlje, maxZdravlje, nivo);
        System.out.printf("  %-10s %s %3d/%-3d%n", skrati(cIme, 10), traka(cZdravlje, cMax), cZdravlje, cMax);
        if (jeKriticno(zdravlje, maxZdravlje)) {
            System.out.println("  !!! KRITIČNO ZDRAVLJE - vrijeme je za napitak !!!");
        }
        int sansa = (int) Math.round(sansaKriticnog(nivo) * 100);
        System.out.println("  1) Napad          sigurno, šansa za kritični udarac " + sansa + "%");
        int promasaj = (int) Math.round(SANSA_PROMASAJA * 100);
        System.out.println("  2) Snažan udarac  dupla šteta, ali " + promasaj + "% šansa za promašaj");
        System.out.println("  3) Napitak        +35% zdravlja (preostalo: " + napici + ")");
        System.out.println("  0) Predaj se");
        System.out.print("Tvoj potez: ");
    }

    static void prikaziKraj(String ime, int pobjede, int zdravlje, int napici, int potezi,
                            boolean predaja, boolean prekid) {
        System.out.println();
        System.out.println(linija('='));
        if (prekid) {
            System.out.println("  Borba je trajala predugo - sudija je prekinuo meč!");
            System.out.println("  Savjet: provjerite TODO 1, 2 i 4 (da li udarci smanjuju zdravlje?).");
        } else if (predaja) {
            System.out.println("  Heroj " + ime + " je napustio arenu. Arena te čeka sljedeći put!");
        } else if (pobjede == CUDOVISTA.length) {
            System.out.println("  POBJEDA!  " + ime + " je novi šampion arene!");
        } else {
            System.out.println("  Tvoj heroj je pao u borbi. Pokušaj ponovo - i čuvaj napitke za Zmaja!");
        }
        boolean pobjeda = pobjede == CUDOVISTA.length;   // zdravlje i napici donose bodove samo uz pobjedu
        int bodovi = Math.max(0, izracunajBodove(pobjede, pobjeda ? zdravlje : 0, pobjeda ? napici : 0, potezi));
        System.out.println("  Pobjede: " + pobjede + " / " + CUDOVISTA.length + "     Potezi: " + potezi
                + "     Preostali napici: " + napici);
        System.out.println("  BODOVI: " + bodovi + "     RANG: " + rang(bodovi));
        System.out.println(linija('='));
    }

    static String rang(int bodovi) {
        if (bodovi >= 3500) {
            return "LEGENDA ARENE";
        } else if (bodovi >= 3250) {
            return "Šampion";
        } else if (bodovi >= 2800) {
            return "Gladijator";
        } else if (bodovi >= 1800) {
            return "Veteran";
        } else if (bodovi >= 900) {
            return "Borac";
        }
        return "Početnik";
    }

    // ----------------------------- automatska provjera TODO zadataka -----------------------------

    static int rijeseno = 0;
    static int rijesenoBonus = 0;
    static String sljedeciKorak = null;

    static void provjeriZadatke() {
        System.out.println("================== PROVJERA VAŠIH ZADATAKA ===================");
        provjeri("TODO 1", "jeZiv", false,
                "Koristite relacijski operator: lik je živ kad je zdravlje VEĆE od 0.",
                () -> prva(ocekuj("jeZiv(35)", jeZiv(35), true),
                           ocekuj("jeZiv(0)", jeZiv(0), false),
                           ocekuj("jeZiv(-4)", jeZiv(-4), false)));
        provjeri("TODO 2", "osnovnaSteta", false,
                "Dijeljenje ide prije oduzimanja, a 7 / 2 = 3 (cjelobrojno) - bez zagrada i double-a.",
                () -> prva(ocekuj("osnovnaSteta(20, 10)", osnovnaSteta(20, 10), 15),
                           ocekuj("osnovnaSteta(14, 7)", osnovnaSteta(14, 7), 11)));
        provjeri("TODO 3", "kriticnaSteta", false,
                "Pomnožite sa 1.5 i pretvorite u int; (int) mora obuhvatiti CIJELI proizvod.",
                () -> prva(ocekuj("kriticnaSteta(15)", kriticnaSteta(15), 22),
                           ocekuj("kriticnaSteta(10)", kriticnaSteta(10), 15)));
        provjeri("TODO 4", "zdravljeNakonUdarca", false,
                "Izračunajte zdravlje - steta, a Math.max(0, ...) neka pazi da ne ode ispod nule.",
                () -> prva(ocekuj("zdravljeNakonUdarca(50, 18)", zdravljeNakonUdarca(50, 18), 32),
                           ocekuj("zdravljeNakonUdarca(10, 25)", zdravljeNakonUdarca(10, 25), 0)));
        provjeri("TODO 5", "zdravljeNakonNapitka", false,
                "Prvo maxZdravlje * 35, pa / 100 (jer 35 / 100 = 0!), a Math.min čuva maksimum.",
                () -> prva(ocekuj("zdravljeNakonNapitka(50, 120)", zdravljeNakonNapitka(50, 120), 92),
                           ocekuj("zdravljeNakonNapitka(100, 120)", zdravljeNakonNapitka(100, 120), 120)));
        provjeri("TODO 6", "jeKriticno", false,
                "Spojite dva uslova sa &&: lik je živ I zdravlje <= maxZdravlje / 4.",
                () -> prva(ocekuj("jeKriticno(20, 100)", jeKriticno(20, 100), true),
                           ocekuj("jeKriticno(25, 100)", jeKriticno(25, 100), true),
                           ocekuj("jeKriticno(26, 100)", jeKriticno(26, 100), false),
                           ocekuj("jeKriticno(0, 100)", jeKriticno(0, 100), false)));
        provjeri("TODO 7", "formatirajIme", false,
                "Prvo slovo: substring(0, 1).toUpperCase(), ostatak: substring(1).toLowerCase().",
                () -> prva(ocekuj("formatirajIme(\"aMAR\")", formatirajIme("aMAR"), "Amar"),
                           ocekuj("formatirajIme(\"selma\")", formatirajIme("selma"), "Selma")));
        provjeri("TODO 8", "nivoIzIskustva", false,
                "Koliko puta 100 stane u iskustvo? Dodajte 1 jer se počinje od nivoa 1.",
                () -> prva(ocekuj("nivoIzIskustva(0)", nivoIzIskustva(0), 1),
                           ocekuj("nivoIzIskustva(99)", nivoIzIskustva(99), 1),
                           ocekuj("nivoIzIskustva(100)", nivoIzIskustva(100), 2),
                           ocekuj("nivoIzIskustva(250)", nivoIzIskustva(250), 3)));
        provjeri("TODO 9", "izracunajBodove", false,
                "Prepišite formulu iz komentara u Java izraz.",
                () -> prva(ocekuj("izracunajBodove(3, 45, 1, 30)", izracunajBodove(3, 45, 1, 30), 3400),
                           ocekuj("izracunajBodove(1, 0, 0, 12)", izracunajBodove(1, 0, 0, 12), 940)));
        provjeri("BONUS 1", "xpDoSljedecegNivoa", true,
                "Ostatak pri dijeljenju sa 100 kaže koliko XP već imate u trenutnom nivou.",
                () -> prva(ocekuj("xpDoSljedecegNivoa(0)", xpDoSljedecegNivoa(0), 100),
                           ocekuj("xpDoSljedecegNivoa(30)", xpDoSljedecegNivoa(30), 70),
                           ocekuj("xpDoSljedecegNivoa(250)", xpDoSljedecegNivoa(250), 50)));
        provjeri("BONUS 2", "sansaKriticnog", true,
                "Na 0.20 dodajte 0.05 za svaki nivo iznad prvog.",
                () -> prva(ocekuj("sansaKriticnog(1)", sansaKriticnog(1), 0.20),
                           ocekuj("sansaKriticnog(3)", sansaKriticnog(3), 0.30)));

        System.out.println("--------------------------------------------------------------");
        System.out.println(" Riješeno: " + rijeseno + " / 9      Bonus: " + rijesenoBonus + " / 2");
        if (sljedeciKorak != null) {
            System.out.println(" Sljedeći korak -> " + sljedeciKorak);
        }
        if (rijeseno == 9) {
            System.out.println(" Bravo! Svi TODO zadaci su riješeni - igra je spremna. Pobijedite Zmaja!");
        }
        System.out.println("==============================================================");
        System.out.println();
    }

    static void provjeri(String oznaka, String metoda, boolean bonus, String savjet, Supplier<String> test) {
        String greska;
        try {
            greska = test.get();
        } catch (RuntimeException e) {
            greska = metoda + "(...) -> program je pukao: " + e.getClass().getSimpleName();
        }
        if (greska == null) {
            System.out.printf(" [OK] %-8s %s%n", oznaka, metoda);
            if (bonus) {
                rijesenoBonus++;
            } else {
                rijeseno++;
            }
        } else {
            System.out.printf(" [--] %-8s %s%n", oznaka, greska);
            if (sljedeciKorak == null) {
                sljedeciKorak = oznaka + " (" + metoda + ")\n    Savjet: " + savjet;
            }
        }
    }

    static String prva(String... greske) {
        for (String g : greske) {
            if (g != null) {
                return g;
            }
        }
        return null;
    }

    static String ocekuj(String poziv, int dobijeno, int ocekivano) {
        return dobijeno == ocekivano ? null : poziv + " -> treba " + ocekivano + ", a vraća " + dobijeno;
    }

    static String ocekuj(String poziv, boolean dobijeno, boolean ocekivano) {
        return dobijeno == ocekivano ? null : poziv + " -> treba " + ocekivano + ", a vraća " + dobijeno;
    }

    static String ocekuj(String poziv, double dobijeno, double ocekivano) {
        return Math.abs(dobijeno - ocekivano) < 1e-9 ? null
                : poziv + " -> treba " + ocekivano + ", a vraća " + dobijeno;
    }

    static String ocekuj(String poziv, String dobijeno, String ocekivano) {
        return ocekivano.equals(dobijeno) ? null
                : poziv + " -> treba \"" + ocekivano + "\", a vraća \"" + dobijeno + "\"";
    }
}
