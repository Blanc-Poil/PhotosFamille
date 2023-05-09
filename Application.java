public class Application {

    public static void main(String[] args) {
        int connexion = BD.ouvrirConnexion(Access.adresse, Access.bd, Access.login,Access.password);

        int choixFonctionnalite = 0;
        do {

        
        switch (choixFonctionnalite) {
            case 0: 
                Ecran.afficherln("Bienvenue dans l'application de gestion de photos de famille");
                Ecran.afficherln("Veuillez choisir une fonctionnalité :");
                Ecran.afficherln("1. Insérer une photo d'une personne");
                Ecran.afficherln("2. Récuperer les photos d'une personne");
                Ecran.afficherln("3. Récuperer toutes les photos d'une famille");
                Ecran.afficherln("Tapez -1 Quitter l'application");
                while (choixFonctionnalite < -1 || choixFonctionnalite > 3) {
                    Ecran.afficherln("Veuillez saisir un nombre entre 1 et 3");
                    choixFonctionnalite = Clavier.saisirInt();
                }




                break;
            case 1:

                break;
            case 2:
                
                    break;
            case 3:

                    break;
        }
        } while (choixFonctionnalite != -1);
    }
}
