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
            Ecran.afficherln(album[i].IdAlbum + " | " + album[i].NomAlbum);
        }
    }

    static void affichageEvenement(Evenement [] evenement){
        Ecran.afficherln("Voici la liste des évènements :");
        Ecran.afficherln(" ID Evenement | Libelle Evenement | Date Evenement");
        for (int i = 0; i < evenement.length; i++) {
            Ecran.afficherln(evenement[i].IdEvenement + " | " + evenement[i].LibelleEvenement + " | " + evenement[i].DateEvenement);
        }
    }

    static void affichageIndividu(Individu[] individu){
        Ecran.afficherln("Voici la liste des individus :");
        Ecran.afficherln("Id Individu | Id Pere | Id Mere | Nom Individu | Penom Individu");
        for (int i = 0; i < individu.length; i++) {
            Ecran.afficherln(individu[i].IdInd + " | " + individu[i].IdPere + " | " + individu[i].IdMere + " | " + individu[i].nomInd + " | " + individu[i].PrenomInd);
        }
    }

    static int fonctionnalite1(int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Insertion de photo #####*/");
        affihageAlbum(null); // mettre le tableau d'album
        Ecran.afficherln("Veuillez saisir l'id de l'album dans laquelle vous voulez insérer la photo");
        int idAlbum = Clavier.saisirInt();
        Ecran.afficherln("Veuillez saisir le numéro de la page dans laquelle vous voulez insérer la photo");
        int page = Clavier.saisirInt();
        affichageEvenement(null); // mettre le tableau d'évènement
        Ecran.afficherln("Veuillez saisir l'id de l'évènement dans laquelle vous voulez insérer la photo");
        int idEvenement = Clavier.saisirInt();
        
        if(bdd.addPhoto(idAlbum, page, idEvenement))
        {
            Ecran.afficherln("La photo a bien été insérée");
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

    static int fonctionnalite2 (int choixFonctionnalite){
        Ecran.afficherln("/*##### Trouver les photos d'une personne #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez trouver les photos");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez trouver les photos");
        String prenomPersonne = Clavier.saisirString();
        //requette pour renvoyé tous les individus qui ont le même nom et prénom
        if (true) // si il y a des individus
        {
            affichageIndividu(null); // mettre le tableau d'individu
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez trouver les photos");
            int idPersonne = Clavier.saisirInt();
            //requette pour renvoyé les albums et les pages de toutes les photos de la personne
            //affichage des albums et des pages
        }
        else
        {
            Ecran.afficherln("Il n'y a pas de personne avec ce nom et ce prénom");
            Ecran.afficherln("Si vous souhaitez réesayer taper 2, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        return choixFonctionnalite;
    }

    static int fonctionnalite3(int choixFonctionnalite){
        Ecran.afficherln("/*##### Reconstituer toutes les photos d'une famille #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez reconstituer les photos de sa famille");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez reconstituer les photos de sa famille");
        String prenomPersonne = Clavier.saisirString();
        //requette pour renvoyé tous les individus qui ont le même nom et prénom
        if (true) // si il y a des individus
        {
            affichageIndividu(null); // mettre le tableau d'individu
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez reconstituer les photos de sa famille");
            int idPersonne = Clavier.saisirInt();
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
                choixFonctionnalite = fonctionnalite2(choixFonctionnalite);
                break;
            case 3:
                choixFonctionnalite = fonctionnalite3(choixFonctionnalite);
                break;
        }
        } while (choixFonctionnalite != -1);
    }
}
