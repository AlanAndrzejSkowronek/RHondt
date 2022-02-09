package MiRHondt;

public class PrintRHondt extends RHondt {
    
    private char partido;
    
    public void printDeVotos(int [] Votos){
        
        partido = 'A';
        System.out.println("Votos de los partidos:");
        System.out.print(" | ");
        for (int i = 0; i < Votos.length; i++){
            System.out.print("Partido " + "\"" + partido + "\"" + ": ");
            System.out.print(Votos[i]);
            System.out.print(" | ");
            partido++;
        }
        System.out.println();
    }
    
    public void printDeTablaVotos(int[] Votos){
        
        int[][] tablaVotos = tablaVotosLimpios(Votos);
        partido = 'A';
        System.out.println("Tabla pre-asignación de diputados a los partidos:");
        for (int row = 0; row < tablaVotos.length; row++){
            System.out.print("Partido " + partido);
            for (int col = 0; col < tablaVotos[row].length; col++){
                System.out.print(" | ");
                System.out.print(tablaVotos[row][col]);
            }
            System.out.println(" | ");
            partido++;
        }
    }
    
    public void printDiputadosResultado(int[] Votos){
        
        int[] Diputados = asignarALosPartidosDiputados(Votos);
        partido = 'A';
        System.out.println("Número de diputados que habrá por cada partido:");
        for (int i = 0; i < Diputados.length; i++){
            System.out.print("Partido " + "\"" + partido + "\"" + ": ");
            partido++;
            System.out.print(Diputados[i] + " ");
        }
        System.out.println();
    }
}
