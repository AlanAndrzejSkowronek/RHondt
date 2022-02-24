package RHondtOOP;

public class rHondtOOP {

    public static void main(String[] args) {
        String[] nombresPartidos = {"Partido A", "Partido B", "Partido C", "Partido D", "Partido E"};
        int[] votosObtenidos =  {120000, 100000, 40000, 5000, 2500} ;

        calcElec ce = new calcElec(nombresPartidos, votosObtenidos, 8);
        ce.printTabla();
        ce.printResultados();
    }
}
