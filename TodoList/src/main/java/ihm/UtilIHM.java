package ihm;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

public class UtilIHM {

    private static Scanner scan = new Scanner(System.in);


    public static String inputText(String label) {
        System.out.printf("\t %s > ",label);
        String ret = scan.nextLine();
        System.out.print("\n");
        return ret;
    }

    public static int inputNumber(String label) throws Exception {

        String saisie = inputText(label);
        int retour ;

        try {
            retour = parseInt(saisie) ;
        } catch (Exception e) {
            throw new Exception("NaN");
        }

        return retour;
    }

    public static Long inputLong(String label) throws Exception {

        String saisie = inputText(label);
        Long retour ;

        try {
            retour = parseLong(saisie) ;
        } catch (Exception e) {
            throw new Exception("NaN");
        }

        return retour;
    }


    public static LocalDate inputDate(String label) {

        boolean valide = false;
        String input;
        LocalDate ret = null;

        while (!valide) {

            input = inputText(label);

            try {
                ret = LocalDate.parse(input); // format date attendu YYYY-MM-JJ

                valide = true;

            } catch ( DateTimeParseException e) {
                consoleError(e.getMessage());
            }

        }

        return ret;

    }

    public static Double inputPrix(String label) {

        boolean valide = false;
        String input;
        Double ret = null;

        while (!valide) {

            input = inputText(label);

            try {
                ret = Double.parseDouble(input);
                valide = true;

            } catch ( NumberFormatException | NullPointerException e) {
                consoleError(e.getMessage());
            }
        }

        return ret;
    }



    public static void H1(String titre) {
        System.out.printf("\n\n *** %s ***\n\n",titre.toUpperCase());
    }

    public static void consoleError(String error) {
        System.out.printf("\n\n !!! %s !!!\n",error.toUpperCase());
    }

    public static void consoleConfirm(String log) {
        System.out.printf("\n\t >> %s\n",log);
    }

    public static void consoleFail(String log) {
        System.out.printf("\n\t !! %s\n",log);
    }

    public static void consoleLi(String li) {
        System.out.printf("\t - %s\n",li);
    }


    public static boolean isCourrielFormat(String mail) {

        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern courrielPattern = Pattern.compile(regex);
        Matcher matcher = courrielPattern.matcher(mail);

        return matcher.matches();
    }

    public static boolean isNoNumberChain(String nom) {

        String regex = "^[^0-9]*$";

        Pattern monPattern = Pattern.compile(regex);
        Matcher matcher = monPattern.matcher(nom);

        return matcher.matches();
    }

    public static String capitalize(String noms) {

        StringBuilder retour = new StringBuilder();
        String[] tableNoms = noms.split(" ");

        for (String n:tableNoms) {
            if (!n.isEmpty()) {
                char initiale = Character.toUpperCase(n.charAt(0));
                String nomCapitalised = initiale + n.substring(1);
                retour.append(nomCapitalised + " ");
            }
        }

        return retour.toString().trim();
    }



    public static int menu(String[] options)  {
        return menu(options,"Choisir une option");
    }

    public static int menu(String[] options,String titre)  {

        System.out.printf("\n > %s :\n",titre);

        for (int i = 0 ; i < options.length ; i++ ) {
            System.out.printf("\n\t %d : %s",i+1,options[i]);
        }

        try {
            int ret = inputNumber("\n\n\t");

            if (ret < 1 || ret > options.length) {
                consoleFail("Choix innexistant");
                return 0;
            }
            return ret;
        } catch ( Exception e ) {
            consoleError("saisie invalide");
            return 0;
        }

    }


    public static <E extends Enum<E>> String[] parseEnumToStringTable(Class<E> monEnum){

        ArrayList<String> ret = new ArrayList<>();

        for (E type: monEnum.getEnumConstants()) {
            ret.add(type.name());
        }

        return ret.toArray(new String[0]);
    }



}
