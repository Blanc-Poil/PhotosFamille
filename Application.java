import Struct.*;

public class Application
{

    public static void clearScreen() {  
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    } 

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
        clearScreen();
        return choixFonctionnalite;
    }

    static int fonctionnalite1(int choixFonctionnalite, DBmanager bdd){
        clearScreen();
        Ecran.afficherln("/*##### Insertion de photo #####*/");
        Album.print(bdd.getAlbums()); // mettre le tableau d'album
        Ecran.afficherln("Veuillez saisir l'id de l'album dans laquelle vous voulez insérer la photo(Saisir 0 pour revenir au menu principale)");
        int idAlbum = Clavier.saisirInt();
        if(idAlbum == 0){
            clearScreen();
            choixFonctionnalite = 0;
            return choixFonctionnalite;
        }
        clearScreen();
        Ecran.afficherln("Veuillez saisir le numéro de la page dans laquelle vous voulez insérer la photo");
        int page = Clavier.saisirInt();
        Evenement.print(bdd.getEvenements());
        Ecran.afficherln("Veuillez saisir l'id de l'évènement dans laquelle vous voulez insérer la photo(Saisir 0 pour revenir au menu principale)");
        int idEvenement = Clavier.saisirInt();
        if (idEvenement == 0){
            clearScreen();
            choixFonctionnalite = 0;
            return choixFonctionnalite;
        }
        int IdPhoto = bdd.addPhoto(idAlbum, page, idEvenement);
        if(IdPhoto != -1)
        {
            clearScreen();
            Ecran.afficherln("La photo a bien été insérée");
            int idIndividu;
            do{        
            Ecran.afficherln("Vous allez maintenant pouvoir indiquer qui se trouvait sur la photo en précisant leurs numéros dans la liste. Pour arrêter d'indiquer des personnes, tapez 0.");
            SimpleInd.print(bdd.getSimpleInds()); // mettre le tableau d'individu
            idIndividu = Clavier.saisirInt();
            
            if (idIndividu !=0){
                clearScreen();
                if (bdd.addApparition(IdPhoto, idIndividu)){
                    Ecran.afficherln("L'individu a bien été ajouté");

                }
                else{
                    clearScreen();
                    Ecran.afficherln("L'individu n'a pas été ajouté");
            
                }
            }
           }while (idIndividu != 0);
           choixFonctionnalite = 0;
        }
        else
        {
            clearScreen();
            Ecran.afficherln("La photo n'a pas été insérée");
            Ecran.afficherln("Veuillez vérifier que l'album, la page et l'évènement existent bien");
            Ecran.afficherln("Si vous souhaitez réesayer taper 1, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();

        }
        clearScreen();
        return choixFonctionnalite;
    }


    static int fonctionnalite2 (int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Trouver les photos d'une personne #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez trouver les photos");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez trouver les photos");
        String prenomPersonne = Clavier.saisirString();
        Individu[] resultat =bdd.getIndividuByName(nomPersonne, prenomPersonne);
        if (resultat.length!=0) // s'il y a des individus
        {
            clearScreen();
            Individu.print(resultat); 
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez trouver les photos(Saisir 0 pour revenir au menu principale)");
            int idPersonne = Clavier.saisirInt();
            clearScreen();
            if (idPersonne == 0){
                choixFonctionnalite = 0;
                return choixFonctionnalite;
            }
            Apparition.print(bdd.getApparitions(idPersonne));
            choixFonctionnalite = 0;
        }
        else
        {
            Ecran.afficherln("Il n'y a pas de personne avec ce nom et ce prénom");
            Ecran.afficherln("Si vous souhaitez réesayer taper 2, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        Ecran.afficher("Pressez <Entrer> pour quitter");
        Clavier.saisirString();
        clearScreen();
        return choixFonctionnalite;
    }

    static int fonctionnalite3(int choixFonctionnalite, DBmanager bdd){
        Ecran.afficherln("/*##### Reconstituer toutes les photos d'une famille #####*/");
        Ecran.afficherln("Veuillez saisir le nom de la personne dont vous voulez reconstituer les photos de sa famille");
        String nomPersonne = Clavier.saisirString();
        Ecran.afficherln("Veuillez saisir le prénom de la personne dont vous voulez reconstituer les photos de sa famille");
        String prenomPersonne = Clavier.saisirString();
        Individu[] resultat = bdd.getIndividuByName(nomPersonne, prenomPersonne);
        if (resultat.length!=0) // s'il y a des individus
        {   
            clearScreen();
            Individu.print(resultat); 
            Ecran.afficherln("Veuillez saisir l'id de la personne dont vous voulez reconstituer les photos de sa famille(Saisir 0 pour revenir au menu principale)");
            int idPersonne = Clavier.saisirInt();
            if (idPersonne == 0){
                clearScreen();
                choixFonctionnalite = 0;
                return choixFonctionnalite;
            }
            clearScreen();
            Photo.print(bdd.getFamilyPictures(idPersonne));
            choixFonctionnalite = 0;
        }
        else
        {
            Ecran.afficherln("Il n'y a pas de personne avec ce nom et ce prénom");
            Ecran.afficherln("Si vous souhaitez réesayer taper 3, sinon taper 0");
            choixFonctionnalite= Clavier.saisirInt();
        }
        Ecran.afficher("Pressez <Entrer> pour quitter");
        Clavier.saisirString();
        clearScreen();
        
        return choixFonctionnalite;
    }

    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        clearScreen();
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
        } while (choixFonctionnalite != -1);

        clearScreen();
    }
}
