import Struct.*;
public class Application
{

    static int menuPrincipale(int choixFonctionnalite){
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
        return choixFonctionnalite;
    }

    static void affihageAlbum(Album [] album){
        Ecran.afficherln("Voici la liste des albums :");
        Ecran.afficherln("Id Album | Nom Album");
        for (int i = 0; i < album.length; i++) {
            Ecran.afficherln(album[i].IDAlbum + " | " + album[i].NomAlbum);
        }
    }

    static void affichageEvenement(Evenement [] evenement){
        Ecran.afficherln("Voici la liste des évènements :");
        Ecran.afficherln(" ID Evenement | Libelle Evenement | Date Evenement");
        for (int i = 0; i < evenement.length; i++) {
            Ecran.afficherln(evenement[i].IDEvenement + " | " + evenement[i].LibelleEvenement + " | " + evenement[i].DateEvenement);
        }
    }

    static void affichageSimpleInd(SimpleInd[] simpleInd){
        Ecran.afficherln("Voici la liste des individus :");
        Ecran.afficherln("Id Individu | Nom Individu | Penom Individu");
        for (int i = 0; i < simpleInd.length; i++) {
            Ecran.afficherln(simpleInd[i].IDInd + " | " + simpleInd[i].NomInd + " | " + simpleInd[i].PrenomInd);
        }
    }

    static void affichageIndividu(Individu[] individu){
        Ecran.afficherln("Voici la liste des individus :");
        Ecran.afficherln("Id Individu | Id Pere | Id Mere | Nom Individu | Penom Individu");
        for (int i = 0; i < individu.length; i++) {
            Ecran.afficherln(individu[i].IDInd + " | " + individu[i].IDPere + " | " + individu[i].IDMere + " | " + individu[i].NomInd + " | " + individu[i].PrenomInd);
        }
    }

    static void affichagePhotos(Photo[] photo){
        Ecran.afficherln("Voici la liste des photos :");
        Ecran.afficherln("Id Photo | Id Album | Num Page");
        for (int i = 0; i < photo.length; i++) {
            Ecran.afficherln(photo[i].IDPhoto + " | " + photo[i].IDAlbum + " | " + photo[i].NumPage);
        }
    }

    static int fonctionnalite1(int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Insertion de photo #####*/");
        affihageAlbum(null); // mettre le tableau d'album
        Ecran.afficherln("Veuillez saisir l'id de l'album dans laquelle vous voulez insérer la photo(Saisir 0 pour revenir au menu principale)");
        int idAlbum = Clavier.saisirInt();
        if(idAlbum == 0){
            choixFonctionnalite = 0;
            return choixFonctionnalite;
        }
        Ecran.afficherln("Veuillez saisir le numéro de la page dans laquelle vous voulez insérer la photo");
        int page = Clavier.saisirInt();
        affichageEvenement(null); // mettre le tableau d'évènement
        Ecran.afficherln("Veuillez saisir l'id de l'évènement dans laquelle vous voulez insérer la photo(Saisir 0 pour revenir au menu principale)");
        int idEvenement = Clavier.saisirInt();
        if (idEvenement == 0){
            choixFonctionnalite = 0;
            return choixFonctionnalite;
        }
        int IdPhoto = bdd.addPhoto(idAlbum, page, idEvenement);
        if(IdPhoto != 1)
        {
            Ecran.afficherln("La photo a bien été insérée");
            Ecran.afficherln("Vous allez maintenant pouvoir indiquer qui se trouvait sur la photo en précisant leurs numéros dans la liste. Pour arrêter d'indiquer des personnes, tapez 0.");
            affichageSimpleInd(null); // mettre le tableau d'individu
            int idIndividu = Clavier.saisirInt();
            if (idIndividu == 0){
                choixFonctionnalite = 0;
                return choixFonctionnalite;
            }
            //bdd.addIndividu(idIndividu, IdPhoto);
    }
        else
        {
            Ecran.afficherln("La photo n'a pas été insérée");
            Ecran.afficherln("Veuillez vérifier que l'album, la page et l'évènement existent bien");
            Ecran.afficherln("Si vous souhaitez réesayer taper 1, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        return choixFonctionnalite;
    }


    static int fonctionnalite2 (int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Trouver les photos d'une personne #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez trouver les photos");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez trouver les photos");
        String prenomPersonne = Clavier.saisirString();
        //requette pour renvoyé tous les individus qui ont le même nom et prénom
        if (true) // si il y a des individus
        {
            affichageIndividu(null); // mettre le tableau d'individu
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez trouver les photos(Saisir 0 pour revenir au menu principale)");
            int idPersonne = Clavier.saisirInt();
            if (idPersonne == 0){
                choixFonctionnalite = 0;
                return choixFonctionnalite;
            }
            //requette pour renvoyé les albums et les pages de toutes les photos de la personne
            affichagePhotos(null); // mettre le tableau de photo
        }
        else
        {
            Ecran.afficherln("Il n'y a pas de personne avec ce nom et ce prénom");
            Ecran.afficherln("Si vous souhaitez réesayer taper 2, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        return choixFonctionnalite;
    }

    static int fonctionnalite3(int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Reconstituer toutes les photos d'une famille #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez reconstituer les photos de sa famille");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez reconstituer les photos de sa famille");
        String prenomPersonne = Clavier.saisirString();
        //requette pour renvoyé tous les individus qui ont le même nom et prénom
        if (true) // si il y a des individus
        {
            affichageIndividu(null); // mettre le tableau d'individu
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez reconstituer les photos de sa famille(Saisir 0 pour revenir au menu principale)");
            int idPersonne = Clavier.saisirInt();
            if (idPersonne == 0){
                choixFonctionnalite = 0;
                return choixFonctionnalite;
            }
            //requette pour renvoyé les photos de la famille
            //affichage des photos
        }
        else
        {
            Ecran.afficherln("Il n'y a pas de personne avec ce nom et ce prénom");
            Ecran.afficherln("Si vous souhaitez réesayer taper 3, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        return choixFonctionnalite;
    }

    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        int choixFonctionnalite = 0;
        do {

        
        switch (choixFonctionnalite) {
            case 0: 
                choixFonctionnalite = menuPrincipale(choixFonctionnalite);
                break;
            case 1:
                choixFonctionnalite = fonctionnalite1(choixFonctionnalite, bdd);
                break;
            case 2:
                choixFonctionnalite = fonctionnalite2(choixFonctionnalite, bdd);
                break;
            case 3:
                choixFonctionnalite = fonctionnalite3(choixFonctionnalite, bdd);
                break;
        }
    }
}
