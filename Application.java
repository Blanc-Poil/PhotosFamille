public class Application {
    public static String adresse = "localhost";
    public static String bd = "photosfamille";
    public static String login = "root";
    public static String password = "";

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