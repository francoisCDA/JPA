package model;

public enum Priorite {

    URGENT,
    SECONDAIRE,
    OPTIONNEL,
    OUBLIABLE;

    public static String[] getPriority() {
        return new String[]{URGENT.name(),SECONDAIRE.name(),OPTIONNEL.name(), OUBLIABLE.name()};
    }

    public static Priorite getPriority(int p){
        Priorite ret;
        switch (p){
            case 1 -> ret = Priorite.URGENT;
            case 2 -> ret = Priorite.SECONDAIRE;
            case 3 -> ret = Priorite.OPTIONNEL;
            default -> ret = Priorite.OUBLIABLE;

        }
        return ret;
    }
}
