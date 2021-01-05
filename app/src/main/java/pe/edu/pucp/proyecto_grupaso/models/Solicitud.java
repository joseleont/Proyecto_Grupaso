package pe.edu.pucp.proyecto_grupaso.models;

public class Solicitud {
    private String gps;
    private boolean mandarCorreo;
    private String motivo;
    private String direccion;
    private String uidUsuario;
    private String correoUsuario;
    private String uidDispositivo;
    private String estado;

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public void setCorreoUsuario(String correoUsuario) {
        this.correoUsuario = correoUsuario;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getUidDispositivo() {
        return uidDispositivo;
    }

    public void setUidDispositivo(String uidDispositivo) {
        this.uidDispositivo = uidDispositivo;
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public boolean isMandarCorreo() {
        return mandarCorreo;
    }

    public void setMandarCorreo(boolean mandarCorreo) {
        this.mandarCorreo = mandarCorreo;
    }

    public String getUidUsuario() {
        return uidUsuario;
    }

    public void setUidUsuario(String uidUsuario) {
        this.uidUsuario = uidUsuario;
    }


}

