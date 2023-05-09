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
}
