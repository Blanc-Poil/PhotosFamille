import Struct.*;

public class DBmanager
{
    private int database;

    public DBmanager(String host, String dbname, String login, String password)
    {
        this.database = BD.ouvrirConnexion(host, dbname, login, password);
    }

    public boolean addPhoto(int album, int page, int event)
    {
        String sql = "INSERT INTO PHOTO (IDAlbum, NumPage, IDEvenement) VALUES (%d, %d, %d)";
        int result;
        sql = String.format(sql, album, page, event);
        result = BD.executerUpdate(this.database, sql);
        return (result != -1);
    }

    public Album[] getAlbums()
    {
        int query;
        int nbrAlbums;
        //count nbr of albums registered
        query = BD.executerSelect(this.database, "SELECT COUNT(*) AS nbr FROM ALBUM");
        BD.suivant(query);
        nbrAlbums = BD.attributInt(query, "nbr");
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
        return events;
    }

    public static String dateString(long timestamp)
    {
        int day = BD.jour(timestamp);
        int month = BD.mois(timestamp);
        int year = BD.annee(timestamp);
        return "" + day + '/' + month + '/' + year;
    }
}
