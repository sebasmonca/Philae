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
@Table(name = "color_producto")
@NamedQueries({
    @NamedQuery(name = "ColorProducto.findAll", query = "SELECT c FROM ColorProducto c"),
    @NamedQuery(name = "ColorProducto.findByIdColorProducto", query = "SELECT c FROM ColorProducto c WHERE c.idColorProducto = :idColorProducto"),
    @NamedQuery(name = "ColorProducto.findByDescripcion", query = "SELECT c FROM ColorProducto c WHERE c.descripcion = :descripcion")})
public class ColorProducto implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idColor_Producto")
    private Integer idColorProducto;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idColorProducto", fetch = FetchType.EAGER)
    private List<OrdenProduccion> ordenProduccionList;

    public ColorProducto() {
    }

    public ColorProducto(Integer idColorProducto) {
        this.idColorProducto = idColorProducto;
    }

    public ColorProducto(Integer idColorProducto, String descripcion) {
        this.idColorProducto = idColorProducto;
        this.descripcion = descripcion;
    }

    public Integer getIdColorProducto() {
        return idColorProducto;
    }

    public void setIdColorProducto(Integer idColorProducto) {
        this.idColorProducto = idColorProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        hash += (idColorProducto != null ? idColorProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ColorProducto)) {
            return false;
        }
        ColorProducto other = (ColorProducto) object;
        if ((this.idColorProducto == null && other.idColorProducto != null) || (this.idColorProducto != null && !this.idColorProducto.equals(other.idColorProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.ColorProducto[ idColorProducto=" + idColorProducto + " ]";
    }

    @PostLoad
    public void fixIt() {
        this.ordenProduccionList = new ArrayList<>(this.ordenProduccionList);
    }

}
