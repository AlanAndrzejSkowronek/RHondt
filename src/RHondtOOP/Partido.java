package RHondtOOP;

public class Partido { 
    private String nombre;
    private int votos;
    private boolean participacion = true;
    private int esconsPorPartido = 0;

    public Partido(String nombreP, int votosP) {
        this.nombre = nombreP;
        this.votos = votosP;
    }
    
    public void printP(){
        System.out.println(this.nombre);
        System.out.println(this.votos);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreP) {
        this.nombre = nombreP;
    }

    public int getVotos(){
        return votos;
    }

    public void setVotos(int votosP) {
        this.votos = votosP;
    }

    public boolean getPart(){
        return participacion;
    }

    public void setPart(boolean ParticipacionP){
        this.participacion = ParticipacionP;
    }

    public int getEscons(){
        return esconsPorPartido;
    }
    
    public void setEscons(int numEscons){
        this.esconsPorPartido += numEscons;
    }
}
