package esqueletoRHondt;

public class RHondtEsqueleto {

    private static int esconsPerAssignar; // num diputados que nos faltan por asignar.
    private static int numPartits; // num partidos que tenemos.
    private static int[] votsPartits; // Votos de los partidos.
    private static String[] nomsPartits; // nombres de los partidos.
    private static final float percentatgeMinim  = 0.03F; // porcentaje por el cual hay que pasar los partidos.

    private static float[][] quoficientsHondt; // tabla con los calculos de los votos (/1, /2, /3, /4...)
    private static boolean[] teVotsMinims; // boolean de si x partido tiene los votos mínimos de x porcentaje.
    private static int[] esconsAssignats; // Numero final con los diputados asignados.

    public static int[] initCalculHondt(
            int escons,
            String[] partits,
            int[] votsAconseguits,
            float percentatgeEliminacionPartits
    ){
        
        esconsPerAssignar = escons;
        numPartits = votsAconseguits.length;
        nomsPartits = partits;
        votsPartits = votsAconseguits;
        quoficientsHondt = new float [numPartits][esconsPerAssignar];
        esconsAssignats = new int[numPartits];
        teVotsMinims = new boolean[numPartits];
        percentatgeEliminacionPartits = percentatgeMinim;
        
        determinarPartitsAmbVotsMinims();
        construirTaulaHondt();
        calculHondt();
        
        return esconsAssignats;
    }

    private static void imprimirResultats(){
        System.out.println("Escons assignats:");
        for (int i = 0; i < esconsAssignats.length; i++){
            if (teVotsMinims[i]){
                System.out.print(nomsPartits[i] + ": " + esconsAssignats[i] + " ");
            }
        }
    }

    private static void imprimirTaulaQuoficients(){
        System.out.println("Partits i el seus respectius vots:");
        for (int i = 0; i < numPartits; i++){
            System.out.print(nomsPartits[i] + ": " + votsPartits[i] + " " + teVotsMinims[i] + " ");
        }
        
        System.out.println();
        System.out.println("Taula de quoficients dels partits diferents:");
        for (int row = 0; row < quoficientsHondt.length; row++){
            if (teVotsMinims[row]){
                for(int col = 0; col < quoficientsHondt[row].length; col++){
                    System.out.print(quoficientsHondt[row][col] + " | ");
                }
                System.out.println();
            }
        }
    }

    private static void calculHondt(){
        float vMaximo;
        int pos;
        for (int i = 0; i < esconsPerAssignar; i++){
            vMaximo = 0;
            pos = 0;
            for (int j = 0; j < quoficientsHondt.length; j++){
                if (teVotsMinims[j]){
                    if (quoficientsHondt[j][esconsAssignats[j]] > vMaximo){
                        vMaximo = quoficientsHondt[j][esconsAssignats[j]];
                        pos = j;
                    }
                }
            }
            esconsAssignats[pos]++;
        }
    }

    private static void determinarPartitsAmbVotsMinims(){
        int totalDeVots = votsTotals();
        float barreraElectoral = totalDeVots * percentatgeMinim;
        
        for (int i = 0; i < votsPartits.length; i++){
            teVotsMinims[i] = (votsPartits[i] >= barreraElectoral);
        }
    }

    private static int votsTotals(){
        int total = 0;
        
        for (int i = 0; i < votsPartits.length; i++){
            total += votsPartits[i];
        }
        
        return total;
    }

    private static void construirTaulaHondt(){
        
        for (int row = 0; row < quoficientsHondt.length; row++){
            for(int col = 0; col < quoficientsHondt[row].length; col++){
                if (teVotsMinims[row] == true){
                    quoficientsHondt[row][col] = votsPartits[row] / (col + 1);
                } 
            }
        }
    }

    public static void main(String[] args) {
        // write your code here
        String[] nomsPartits = {"Partit A", "Partit B", "Partit C", "Partit D", "Partit E"};
        int[] votsAconseguits =  {120000, 100000, 40000, 5000, 2500} ;
        int [] resultats;
        
        resultats = RHondtEsqueleto.initCalculHondt( 8, nomsPartits, votsAconseguits, 3);
        imprimirTaulaQuoficients();
        imprimirResultats();
    }

}