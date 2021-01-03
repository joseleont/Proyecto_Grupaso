package pe.edu.pucp.proyecto_grupaso.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Dispositivo {
    private String tipo;
    private String marca;
    private String foto;
    private String caracteristicas;
    private String incluye;
    private int stock;

    public Dispositivo() {
    }

    public Dispositivo(String tipo, String marca, String foto, String caracteristicas, String incluye, int stock) {
        this.tipo = tipo;
        this.marca = marca;
        this.foto = foto;
        this.caracteristicas = caracteristicas;
        this.incluye = incluye;
        this.stock = stock;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("tipo", tipo);
        map.put("marca", marca);
        map.put("foto", foto);
        map.put("caracteristicas", caracteristicas);
        map.put("incluye", incluye);
        map.put("stock", stock);
        return map;


    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String getIncluye() {
        return incluye;
    }

    public void setIncluye(String incluye) {
        this.incluye = incluye;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
