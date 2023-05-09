public class Application {
    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        bdd.addPhoto(1, 25, 1);

        int connexion = BD.ouvrirConnexion(Access.adresse, Access.bd, Access.login,Access.password);
        Ecran.afficherln("Connexion : " + connexion);
        int choixFonctionnalite = 0;
        do {

        
        switch (choixFonctionnalite) {
            case 0: 
                Ecran.afficherln("Bienvenue dans l'application de gestion de photos de famille");
                Ecran.afficherln("Veuillez choisir une fonctionnalité :");
                Ecran.afficherln("1. Insérer une photo d'une personne");
                Ecran.afficherln("2. Trouver les photos d'une personne");
                Ecran.afficherln("3. Reconstituer toutes les photos d'une famille");
                Ecran.afficherln("Tapez -1 pour quitter l'application");
                do {
                    Ecran.afficherln("Veuillez saisir un nombre entre 1 et 3");
                    choixFonctionnalite = Clavier.saisirInt();
                }while (choixFonctionnalite < -1 || choixFonctionnalite > 3);

                break;
            case 1:
                do{
                    Ecran.afficherln("Insérer une photo :");
                    Ecran.afficherln("Veuillez saisir l'id de l'album dans laquelle vous voulez insérer la photo");
                    int idAlbum = Clavier.saisirInt();
                    Ecran.afficherln("Veuillez saisir le numéro de la page dans laquelle vous voulez insérer la photo");
                    int page = Clavier.saisirInt();
                    Ecran.afficherln("Veuillez saisir l'id de l'évènement dans laquelle vous voulez insérer la photo");
                    int idEvenement = Clavier.saisirInt();
                    

                } while (bdd.addPhoto(idAlbum, page, idEvenement)==false;)


                break;
            case 2:
                
                    break;
            case 3:

                    break;
        }
        } while (choixFonctionnalite != -1);
    }
}
