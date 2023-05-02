public class Application {
    public static String adresse = "sql7.freemysqlhosting.net";
    public static String bd = "sql7615556";
    public static String login = "sql7615556";
    public static String password = "wPHQyG7cx7";

    public static void main(String[] args) {
        int connexion = BD.ouvrirConnexion(adresse, bd, login,password);
        // cr´eation de la requ^ete
        String sql = "SELECT PATIENT.Nom, MEDECIN.Nom, NumCons FROM CONSULTATION, MEDECIN, PATIENT WHERE"
        +" CONSULTATION.Medecin = MEDECIN.Matricule AND CONSULTATION.Patient = NumSecu";
        // envoi de la requ^ete
        int res = BD.executerSelect(connexion, sql);
        // parcours du r´esultat (ligne par ligne)
        while (BD.suivant(res)) {
            int numCons = BD.attributInt(res,"NumCons");
            String nomMedecin = BD.attributString(res,"MEDECIN.Nom");
            String nomPatient = BD.attributString(res,"PATIENT.Nom");
            System.out.println(""+numCons+ ": "+ nomPatient +" ("+nomMedecin+")");
        }
        BD.fermerResultat(res);
        BD.fermerConnexion(connexion);
    }
}