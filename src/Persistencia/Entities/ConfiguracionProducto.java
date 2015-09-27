/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;

/**
 *
 * @author Sebas
 */
@Entity
@Table(name = "configuracion_producto")
@NamedQueries({
    @NamedQuery(name = "ConfiguracionProducto.findAll", query = "SELECT c FROM ConfiguracionProducto c"),
    @NamedQuery(name = "ConfiguracionProducto.findByIdConfiguracionProducto", query = "SELECT c FROM ConfiguracionProducto c WHERE c.idConfiguracionProducto = :idConfiguracionProducto"),
    @NamedQuery(name = "ConfiguracionProducto.findByNombreproducto", query = "SELECT c FROM ConfiguracionProducto c WHERE c.nombreproducto = :nombreproducto"),
    @NamedQuery(name = "ConfiguracionProducto.findByFichatecnicaproducto", query = "SELECT c FROM ConfiguracionProducto c WHERE c.fichatecnicaproducto = :fichatecnicaproducto"),
    @NamedQuery(name = "ConfiguracionProducto.findByParametrizacionproducto", query = "SELECT c FROM ConfiguracionProducto c WHERE c.parametrizacionproducto = :parametrizacionproducto")})
public class ConfiguracionProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idConfiguracion_Producto")
    private Integer idConfiguracionProducto;
    @Basic(optional = false)
    @Column(name = "Nombre_producto")
    private String nombreproducto;
    @Lob
    @Column(name = "Observaciones_configuracion_producto")
    private String observacionesconfiguracionproducto;
    @Basic(optional = false)
    @Column(name = "Ficha_tecnica_producto")
    private String fichatecnicaproducto;
    @Basic(optional = false)
    @Column(name = "Parametrizacion_producto")
    private String parametrizacionproducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idConfiguracionProducto", fetch = FetchType.EAGER)
    private List<OrdenProduccion> ordenProduccionList;

    public ConfiguracionProducto() {
    }

    public ConfiguracionProducto(Integer idConfiguracionProducto) {
        this.idConfiguracionProducto = idConfiguracionProducto;
    }

    public ConfiguracionProducto(Integer idConfiguracionProducto, String nombreproducto, String fichatecnicaproducto, String parametrizacionproducto) {
        this.idConfiguracionProducto = idConfiguracionProducto;
        this.nombreproducto = nombreproducto;
        this.fichatecnicaproducto = fichatecnicaproducto;
        this.parametrizacionproducto = parametrizacionproducto;
    }

    public Integer getIdConfiguracionProducto() {
        return idConfiguracionProducto;
    }

    public void setIdConfiguracionProducto(Integer idConfiguracionProducto) {
        this.idConfiguracionProducto = idConfiguracionProducto;
    }

    public String getNombreproducto() {
        return nombreproducto;
    }

    public void setNombreproducto(String nombreproducto) {
        this.nombreproducto = nombreproducto;
    }

    public String getObservacionesconfiguracionproducto() {
        return observacionesconfiguracionproducto;
    }

    public void setObservacionesconfiguracionproducto(String observacionesconfiguracionproducto) {
        this.observacionesconfiguracionproducto = observacionesconfiguracionproducto;
    }

    public String getFichatecnicaproducto() {
        return fichatecnicaproducto;
    }

    public void setFichatecnicaproducto(String fichatecnicaproducto) {
        this.fichatecnicaproducto = fichatecnicaproducto;
    }

    public String getParametrizacionproducto() {
        return parametrizacionproducto;
    }

    public void setParametrizacionproducto(String parametrizacionproducto) {
        this.parametrizacionproducto = parametrizacionproducto;
    }

    public List<OrdenProduccion> getOrdenProduccionList() {
        return ordenProduccionList;
    }

    public void setOrdenProduccionList(List<OrdenProduccion> ordenProduccionList) {
        this.ordenProduccionList = ordenProduccionList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idConfiguracionProducto != null ? idConfiguracionProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConfiguracionProducto)) {
            return false;
        }
        ConfiguracionProducto other = (ConfiguracionProducto) object;
        if ((this.idConfiguracionProducto == null && other.idConfiguracionProducto != null) || (this.idConfiguracionProducto != null && !this.idConfiguracionProducto.equals(other.idConfiguracionProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.ConfiguracionProducto[ idConfiguracionProducto=" + idConfiguracionProducto + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.ordenProduccionList = new ArrayList<>(this.ordenProduccionList);
    }
    
}
