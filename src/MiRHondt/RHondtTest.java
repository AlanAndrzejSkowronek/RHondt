package MiRHondt;


public class RHondtTest {

    public static void main(String[] args) {
        RHondt test = new RHondt();
        PrintRHondt enseñar = new PrintRHondt();
        
        int [] Votos = {120000, 100000, 40000, 5000, 2500};
        char partido = 'A';
      
        enseñar.printDeVotos(Votos);
        System.out.println();
        
        System.out.println("Barrera Electoral de estos votos: " + test.barreraElectoralDeVotos(Votos));
        System.out.println();
        
        System.out.println("Votos restantes después de pasar la Barrera Electoral:");
        System.out.print("Partidos ");
        for (int i = 0; i < test.votosPasadoPorBarreraElectoral(Votos).size(); i++){
            System.out.print("\"" + partido + "\"" + "      ");
            partido++;
        }
        System.out.println();
        System.out.println("         " + test.votosPasadoPorBarreraElectoral(Votos) + ".");
        System.out.println();
        
        enseñar.printDeTablaVotos(Votos);
        System.out.println();
        
        enseñar.printDiputadosResultado(Votos);
    } 
}
