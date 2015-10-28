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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.Table;

/**
 *
 * @author yuri
 */
@Entity
@Table(name = "Tipo_Material_Producto")
@NamedQueries({
    @NamedQuery(name = "TipoMaterialProducto.findAll", query = "SELECT t FROM TipoMaterialProducto t")})
public class TipoMaterialProducto implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTipo_Material_Producto")
    private Integer idTipoMaterialProducto;
    @Basic(optional = false)
    @Column(name = "Descripcion")
    private String descripcion;
    @ManyToMany(mappedBy = "tipoMaterialProductoList", fetch = FetchType.LAZY)
    private List<OrdenProduccion> ordenProduccionList;

    public TipoMaterialProducto() {
    }

    public TipoMaterialProducto(Integer idTipoMaterialProducto) {
        this.idTipoMaterialProducto = idTipoMaterialProducto;
    }

    public TipoMaterialProducto(Integer idTipoMaterialProducto, String descripcion) {
        this.idTipoMaterialProducto = idTipoMaterialProducto;
        this.descripcion = descripcion;
    }

    public Integer getIdTipoMaterialProducto() {
        return idTipoMaterialProducto;
    }

    public void setIdTipoMaterialProducto(Integer idTipoMaterialProducto) {
        this.idTipoMaterialProducto = idTipoMaterialProducto;
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
        hash += (idTipoMaterialProducto != null ? idTipoMaterialProducto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoMaterialProducto)) {
            return false;
        }
        TipoMaterialProducto other = (TipoMaterialProducto) object;
        if ((this.idTipoMaterialProducto == null && other.idTipoMaterialProducto != null) || (this.idTipoMaterialProducto != null && !this.idTipoMaterialProducto.equals(other.idTipoMaterialProducto))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.TipoMaterialProducto[ idTipoMaterialProducto=" + idTipoMaterialProducto + " ]";
    }
     @PostLoad
    public void fixIt() {
        this.ordenProduccionList = new ArrayList<>(this.ordenProduccionList);
    }
}
