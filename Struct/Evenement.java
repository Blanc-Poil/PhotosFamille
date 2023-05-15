package Struct;

public class Evenement
{
    public int IDEvenement;
    public String LibelleEvenement;
    public String DateEvenement;

    public static void print(Evenement[] event)
    {
        System.out.println('\n');
        if (event.length == 0) return;
        final String headerIDevent = "ID événement";
        final String headerLabel = "Libellé événement";
        final String headerDate = "Date événement";
        //get max for all attributs
        int maxIDevent = headerIDevent.length();
        int maxLabel = headerLabel.length();
        int maxDate = headerDate.length();
        int numLength;
        for (int i=0 ; i < event.length ; i++) {
            numLength = (int)Math.log10(event[i].IDEvenement) + 1;
            if (numLength > maxIDevent) maxIDevent = numLength;
            if (event[i].LibelleEvenement.length() > maxLabel) maxLabel = event[i].LibelleEvenement.length();
            if (event[i].DateEvenement.length() > maxDate) maxDate = event[i].DateEvenement.length();
        }
        //print header
        System.out.print(String.format("%-" + maxIDevent + "s   ", headerIDevent));
        System.out.print(String.format("%-" + maxLabel + "s   ", headerLabel));
        System.out.println(String.format("%-" + maxDate + "s", headerDate));
        System.out.println("-".repeat(maxIDevent) + "   " + "-".repeat(maxLabel) + "   " + "-".repeat(maxDate));
        //print all table
        for (int i=0 ; i < event.length ; i++) {
            System.out.print(String.format("%-" + maxIDevent + "d   ", event[i].IDEvenement));
            System.out.print(String.format("%-" + maxLabel + "s   ", event[i].LibelleEvenement));
            System.out.println(String.format("%-" + maxDate + "s", event[i].DateEvenement));
        }
        System.out.println('\n');
    }
}
