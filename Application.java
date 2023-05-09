public class Application {
    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        bdd.addPhoto(1, 25, 1);
    }
}