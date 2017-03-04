package gmd.plantilla.androidapp.domain.model;

import com.gmdinnovacion.beneficiosgmd.disfruta.utiles.Constante;

/**
 * Created by avermes on 13/2/2017.
 */

public class BeneficioLista {

    Integer idBeneficio;
    String nomBeneficio;
    String imgBeneficio;
    Double puntBeneficio;
    Integer porcDescuento;
    boolean inFavorito;
    Integer idFavorito;
    Integer idEje;
    String nomEje;
    String iconEje;
    String iconSEje;
    Integer idLocal;
    String inAbierto;
    Double numLongitud;
    Double numLatitud;
    Double numDistancia;
    String nomDistrito;

    public Integer getIdBeneficio() {
        return idBeneficio;
    }

    public void setIdBeneficio(Integer idBeneficio) {
        this.idBeneficio = idBeneficio;
    }

    public String getNomBeneficio() {
        return nomBeneficio;
    }

    public void setNomBeneficio(String nomBeneficio) {
        this.nomBeneficio = nomBeneficio;
    }

    public String getImgBeneficio() {
        return imgBeneficio;
    }

    public void setImgBeneficio(String imgBeneficio) {
        this.imgBeneficio = imgBeneficio;
    }

    public Double getPuntBeneficio() {
        return puntBeneficio;
    }

    public void setPuntBeneficio(Double puntBeneficio) {
        this.puntBeneficio = puntBeneficio;
    }

    public Integer getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(Integer porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    public boolean isInFavorito() {
        return inFavorito;
    }

    public void setInFavorito(boolean inFavorito) {
        this.inFavorito = inFavorito;
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Integer getIdEje() {
        return idEje;
    }

    public void setIdEje(Integer idEje) {
        this.idEje = idEje;
    }

    public String getNomEje() {
        return nomEje;
    }

    public void setNomEje(String nomEje) {
        this.nomEje = nomEje;
    }

    public String getIconEje() {
        return iconEje;
    }

    public void setIconEje(String iconEje) {
        this.iconEje = iconEje;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public String getInAbierto() {
        return inAbierto;
    }

    public void setInAbierto(String inAbierto) {
        this.inAbierto = inAbierto;
    }

    public Double getNumLongitud() {
        return numLongitud;
    }

    public void setNumLongitud(Double numLongitud) {
        this.numLongitud = numLongitud;
    }

    public Double getNumLatitud() {
        return numLatitud;
    }

    public void setNumLatitud(Double numLatitud) {
        this.numLatitud = numLatitud;
    }

    public Double getNumDistancia() {
        return numDistancia;
    }

    public void setNumDistancia(Double numDistancia) {
        this.numDistancia = numDistancia;
    }

    public String getNomDistrito() {
        return nomDistrito;
    }

    public void setNomDistrito(String nomDistrito) {
        this.nomDistrito = nomDistrito;
    }

    public String getIconSEje() {
        return iconSEje;
    }

    public void setIconSEje(String iconSEje) {
        this.iconSEje = iconSEje;
    }
}
