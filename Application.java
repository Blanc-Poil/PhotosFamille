public class Application
{
    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        boolean result = bdd.addPhoto(1, 25, 1);
        Ecran.afficher(result);
    }
}
