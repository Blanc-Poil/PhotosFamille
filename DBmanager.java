import Struct.*;

public class DBmanager
{
    private int database;

    public DBmanager(String host, String dbname, String login, String password)
    {
        this.database = BD.ouvrirConnexion(host, dbname, login, password);
    }

    public Album[] getAlbums()
    {
        int query;
        int nbrAlbums;
        //count nbr of albums registered
        query = BD.executerSelect(this.database, "SELECT COUNT(*) AS nbr FROM ALBUM");
        BD.suivant(query);
        nbrAlbums = BD.attributInt(query, "nbr");
        BD.fermerResultat(query);
        //completed the albums array
        Album[] albums = new Album[nbrAlbums];
        query = BD.executerSelect(this.database, "SELECT * FROM ALBUM");
        int i = 0;
        while (BD.suivant(query)) {
            albums[i] = new Album();
            albums[i].IDAlbum = BD.attributInt(query, "IDAlbum");
            albums[i].NomAlbum = BD.attributString(query, "NomAlbum");
            i++;
        }
        BD.fermerResultat(query);
        return albums;
    }

    public Evenement[] getEvenements()
    {
        int query;
        int nbrEvent;
        //count nbr of events registered
        query = BD.executerSelect(this.database, "SELECT COUNT(*) AS nbr FROM EVENEMENT");
        BD.suivant(query);
        nbrEvent = BD.attributInt(query, "nbr");
        BD.fermerResultat(query);
        //completed the events array
        Evenement[] events = new Evenement[nbrEvent];
        query = BD.executerSelect(this.database, "SELECT * FROM EVENEMENT");
        int i = 0;
        while (BD.suivant(query)) {
            events[i] = new Evenement();
            events[i].IDEvenement = BD.attributInt(query, "IDEvenement");
            events[i].LibelleEvenement = BD.attributString(query, "LibelleEvenement");
            events[i].DateEvenement = dateString(BD.attributLong(query, "DateEvenement"));
            i++;
        }
        BD.fermerResultat(query);
        return events;
    }

    public SimpleInd[] getSimpleInds()
    {
        int query;
        int nbrInds;
        //count nbr of individus registered
        query = BD.executerSelect(this.database, "SELECT COUNT(*) AS nbr FROM INDIVIDU");
        BD.suivant(query);
        nbrInds = BD.attributInt(query, "nbr");
        BD.fermerResultat(query);
        //completed the individus array
        SimpleInd[] inds = new SimpleInd[nbrInds];
        query = BD.executerSelect(this.database, "SELECT IDInd, NomInd, PrenomInd FROM INDIVIDU");
        int i = 0;
        while (BD.suivant(query)) {
            inds[i] = new SimpleInd();
            inds[i].IDInd = BD.attributInt(query, "IDInd");
            inds[i].NomInd = BD.attributString(query, "NomInd");
            inds[i].PrenomInd = BD.attributString(query, "PrenomInd");
            i++;
        }
        BD.fermerResultat(query);
        return inds;
    }

    public int addPhoto(int album, int page, int event)
    {
        String sql = "INSERT INTO PHOTO (IDAlbum, NumPage, IDEvenement) VALUES (%d, %d, %d)";
        int result;
        sql = String.format(sql, album, page, event);
        System.out.println(sql);
        result = BD.executerUpdate(this.database, sql);
        return result;
    }

    public boolean addApparition(int photoID, int indID)
    {
        int query;
        String sql = "INSERT INTO APPARAIT (IDPhoto, IDInd) VALUES (%d, %d)";
        sql = String.format(sql, photoID, indID);
        query = BD.executerUpdate(this.database, sql);
        return (query != -1);
    }

    public static String dateString(long timestamp)
    {
        int day = BD.jour(timestamp);
        int month = BD.mois(timestamp);
        int year = BD.annee(timestamp);
        return "" + day + '/' + month + '/' + year;
    }

    /*##### partie 2 #####*/

    public Individu[] getIndividuByName(String nom, String prenom)
    {
        String sql = """
            SELECT i.IDInd, i.NomInd, i.PrenomInd, CONCAT(p.NomInd, ' ', p.PrenomInd) AS NomPere, CONCAT(m.NomInd, ' ', m.PrenomInd) AS NomMere
            FROM INDIVIDU AS i
            LEFT JOIN INDIVIDU AS p ON i.IDPere = p.IDInd
            LEFT JOIN INDIVIDU AS m ON i.IDMere = m.IDInd
            WHERE INSTR(i.NomInd, '%s') > 0 AND INSTR(i.PrenomInd, '%s') > 0
                """;
        sql = String.format(sql, nom, prenom);
        int query = BD.executerSelect(this.database, sql);
        //count nbr of results
        int nbrInds = 0;
        while (BD.suivant(query)) nbrInds++;
        BD.reinitialiser(query);
        //fill the individus list
        Individu[] inds = new Individu[nbrInds];
        for (int i=0 ; i < nbrInds ; i++) {
            BD.suivant(query);
            inds[i] = new Individu();
            inds[i].IDInd = BD.attributInt(query, "IDInd");
            inds[i].NomInd = BD.attributString(query, "NomInd");
            inds[i].PrenomInd = BD.attributString(query, "PrenomInd");
            inds[i].NomPere = BD.attributString(query, "NomPere");
            inds[i].NomMere = BD.attributString(query, "NomMere");
        }
        BD.fermerResultat(query);
        return inds;
    }

    public Apparition[] getApparitions(int id) //problème ici
    {
        String sql = """
            SELECT NomAlbum, NumPage
            FROM INDIVIDU AS
            NATURAL JOIN APPARAIT
            NATURAL JOIN PHOTO
            NATURAL JOIN ALBUM
            WHERE IDInd = %d
                """;
        sql = String.format(sql, id);
        int query = BD.executerSelect(this.database, sql);
        //count nbr of results
        int nbrApp = 0;
        while (BD.suivant(query)) nbrApp++;
        BD.reinitialiser(query);
        //fill the apparitions list
        Apparition[] apps = new Apparition[nbrApp];
        for (int i=0 ; i < nbrApp ; i++) {
            BD.suivant(query);
            apps[i].NomAlbum = BD.attributString(query, "NomAlbum");
            apps[i].NumPage = BD.attributInt(query, "NumPage");
        }
        BD.fermerResultat(query);
        return apps;
    }

    public Photo[] getFamilyPictures(int id)
    {
        String sql = """
            SELECT NomAlbum, NumPage, LibelleEvenement
            FROM (
                SELECT DISTINCT i1.IDInd, i1.PrenomInd
                FROM INDIVIDU i1, INDIVIDU i2
                WHERE (i1.IDInd = i2.IDPere || i1.IDInd = i2.IDMere || i1.IDInd = i2.IDInd)
                AND i2.IDInd IN (
                    SELECT i1.IDInd
                    FROM INDIVIDU i1, INDIVIDU i2
                    WHERE i2.IDInd = %d AND (i1.IDInd = i2.IDPere || i1.IDInd = i2.IDMere || i1.IDInd = i2.IDInd)
                )
            ) f
            NATURAL JOIN APPARAIT
            NATURAL JOIN PHOTO
            NATURAL JOIN EVENEMENT
            NATURAL JOIN ALBUM
                """;
        sql = String.format(sql, id);
        int query = BD.executerSelect(this.database, sql);
        //count nbr of results
        int nbrPics = 0;
        while (BD.suivant(query)) nbrPics++;
        BD.reinitialiser(query);
        //fill the pictures list
        Photo[] pics = new Photo[nbrPics];
        for (int i=0 ; i < nbrPics ; i++) {
            BD.suivant(query);
            pics[i] = new Photo();
            pics[i].NomAlbum = BD.attributString(query, "NomAlbum");
            pics[i].NumPage = BD.attributInt(query, "NumPage");
            pics[i].LibelleEvenement = BD.attributString(query, "LibelleEvenement");
        }
        BD.fermerResultat(query);
        return pics;
    }
}
