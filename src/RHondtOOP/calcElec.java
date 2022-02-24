package RHondtOOP;

public class calcElec {
    private final Partido[] partidos;
    private final int esconsPorAsignar;
    private final int numPartidos;
    private final float[][] tablaQuoficientes;
    private final float porcentajeMinimo = 0.03F;

    public calcElec(String[] partidos, int[] votos, int esconsPorAsignar) {
        this.numPartidos = partidos.length;
        this.partidos = new Partido[this.numPartidos];
        
        this.esconsPorAsignar = esconsPorAsignar;
        this.tablaQuoficientes = new float[this.numPartidos][this.esconsPorAsignar];
        
        setPartidosVotos(partidos, votos);   
        determinarPartidosVotosMin();
        construirTablaQuoficientes();
        calcHondt();
    }
    
    private void setPartidosVotos (String[] nomPartidos, int[] votos){
        for (int i = 0; i < partidos.length; i++){
            this.partidos[i] = new Partido(nomPartidos[i], votos[i]);
        }
    }
    
    private int votosTotales(){
        int total = 0;
        
        for (int i = 0; i < partidos.length; i++){
            total += partidos[i].getVotos();
        }
        
        return total;
    }
    
    private void determinarPartidosVotosMin(){
        int totalVotos = votosTotales();
        float barreraElec = totalVotos * porcentajeMinimo;
        
        for (int i = 0; i < partidos.length; i++){
            if (partidos[i].getVotos() < barreraElec){
                partidos[i].setPart(false);
            }
        }
    }
    
    private void construirTablaQuoficientes(){
        
        for (int row = 0; row < tablaQuoficientes.length; row++){
            for(int col = 0; col < tablaQuoficientes[row].length; col++){
                if (partidos[row].getPart()){
                    tablaQuoficientes[row][col] = partidos[row].getVotos() / (col + 1);
                } 
            }
        }
    }

    private void calcHondt(){
        float vMaximo;
        int pos;
        for (int i = 0; i < esconsPorAsignar; i++){
            vMaximo = 0;
            pos = 0;
            for (int j = 0; j < tablaQuoficientes.length; j++){
                if (partidos[j].getPart()){
                    if (tablaQuoficientes[j][partidos[j].getEscons()] > vMaximo){
                        vMaximo = tablaQuoficientes[j][partidos[j].getEscons()];
                        pos = j;
                    }
                }
            }
            partidos[pos].setEscons(1);
            // System.out.println(partidos[pos].getNombre() + ": " + partidos[pos].getEscons());
        }
    }

    public void printResultados(){
        System.out.println();
        System.out.println("Escons asignados:");
        for (int i = 0; i < partidos.length; i++){
            if (partidos[i].getPart()){
                System.out.print(partidos[i].getNombre() + ": " + partidos[i].getEscons() + " ");
            }
        }
    }

    public void printTabla(){
        System.out.println("Partidos con sus respectivos votos y si participan:");
        for (int i = 0; i < numPartidos; i++){
            partidos[i].printP();
            System.out.println("Participación?: " + partidos[i].getPart());
            System.out.println();
        }
        System.out.println("Tabla de quoficientes:");
        for (int row = 0; row < tablaQuoficientes.length; row++){
            if (partidos[row].getPart()){
                System.out.print(partidos[row].getNombre() + ":    ");
                for(int col = 0; col < tablaQuoficientes[row].length; col++){
                    System.out.print(tablaQuoficientes[row][col] + "   |   ");
                }
                System.out.println();
            }
        }
    }
}
