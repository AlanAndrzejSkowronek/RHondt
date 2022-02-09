package MiRHondt;

import java.util.ArrayList;

public class RHondt {
    
    private ArrayList votosLimpios;
    private final int numeroDiputadosMaximo = 8;
    private int barreraElectoral = 0;
    private int valorMaximo;
    private int index;
    
    public int barreraElectoralDeVotos(int [] Votos){
     
        for (int i = 0; i < Votos.length; i++){
            barreraElectoral += Votos[i];
        }
        barreraElectoral = (barreraElectoral * 3) / 100;
        
        return barreraElectoral;
    }
    
    public ArrayList votosPasadoPorBarreraElectoral(int [] Votos){
        
        /*
          votosLimpios == Votos quitando los partidos que no superan la 
          barrera electoral correspondiente a esa cantidad total de votos
          entre todos los partidos.
        */
        votosLimpios = new ArrayList();
        int barreraElectoral = barreraElectoralDeVotos(Votos);
                
        for (int i = 0; i < Votos.length; i++){
            if (Votos[i] >= barreraElectoral){
                votosLimpios.add(Votos[i]);
            } 
        }
        return votosLimpios;
    }
    
    public int [][] tablaVotosLimpios(int[] Votos){
        
        // tablaVotos == Votos pasado a tabla para realizar la ley de Hondt.
        ArrayList votosLimpios = votosPasadoPorBarreraElectoral(Votos);
        int[][] tablaVotos = new int [votosLimpios.size()][8];
        
        for (int row = 0; row < votosLimpios.size(); row++){
            
            for (int col = 0; col < tablaVotos[row].length; col++){
                tablaVotos[row][col] = (int) votosLimpios.get(row) / (col + 1);
            }
        }
        return tablaVotos;
    }

    public int[] asignarALosPartidosDiputados(int[] Votos){
        
        int[][] tablaVotos = tablaVotosLimpios(Votos);
        int[] Diputados = new int[votosLimpios.size()];
        
        for (int i = 0; i < numeroDiputadosMaximo; i++){
            valorMaximo = tablaVotos[0][Diputados[0]];
            index = 0;
            for (int j = 0; j < tablaVotos.length; j++){
                if (tablaVotos[j][Diputados[j]] > valorMaximo){
                    valorMaximo = tablaVotos[j][Diputados[j]];
                    index = j;
                }
            }
            Diputados[index]++;
        }
        
        return Diputados; 
    }

}
