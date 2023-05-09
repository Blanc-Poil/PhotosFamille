public class DBmanager
{
    private int database;

    public DBmanager(String host, String dbname, String login, String password)
    {
        Ecran.afficher(host);
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
}
