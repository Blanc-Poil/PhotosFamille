import Struct.Evenement;

public class Application
{
    public static void main(String[] args)
    {
        DBmanager bdd = new DBmanager(Access.adresse, Access.bd, Access.login, Access.password);
        Evenement[] events = bdd.getEvenements();
        for (int i=0 ; i < events.length ; i++) {
            System.out.println(events[i]);
        }
    }
}
