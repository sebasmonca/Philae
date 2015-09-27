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
@Table(name = "material_molde")
@NamedQueries({
    @NamedQuery(name = "MaterialMolde.findAll", query = "SELECT m FROM MaterialMolde m"),
    @NamedQuery(name = "MaterialMolde.findByIdMaterialMolde", query = "SELECT m FROM MaterialMolde m WHERE m.idMaterialMolde = :idMaterialMolde"),
    @NamedQuery(name = "MaterialMolde.findByDescripcionmolde", query = "SELECT m FROM MaterialMolde m WHERE m.descripcionmolde = :descripcionmolde")})
public class MaterialMolde implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMaterial_Molde")
    private Integer idMaterialMolde;
    @Basic(optional = false)
    @Column(name = "Descripcion_molde")
    private String descripcionmolde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaterialMolde", fetch = FetchType.EAGER)
    private List<Molde> moldeList;

    public MaterialMolde() {
    }

    public MaterialMolde(Integer idMaterialMolde) {
        this.idMaterialMolde = idMaterialMolde;
    }

    public MaterialMolde(Integer idMaterialMolde, String descripcionmolde) {
        this.idMaterialMolde = idMaterialMolde;
        this.descripcionmolde = descripcionmolde;
    }

    public Integer getIdMaterialMolde() {
        return idMaterialMolde;
    }

    public void setIdMaterialMolde(Integer idMaterialMolde) {
        this.idMaterialMolde = idMaterialMolde;
    }

    public String getDescripcionmolde() {
        return descripcionmolde;
    }

    public void setDescripcionmolde(String descripcionmolde) {
        this.descripcionmolde = descripcionmolde;
    }

    public List<Molde> getMoldeList() {
        return moldeList;
    }

    public void setMoldeList(List<Molde> moldeList) {
        this.moldeList = moldeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaterialMolde != null ? idMaterialMolde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MaterialMolde)) {
            return false;
        }
        MaterialMolde other = (MaterialMolde) object;
        if ((this.idMaterialMolde == null && other.idMaterialMolde != null) || (this.idMaterialMolde != null && !this.idMaterialMolde.equals(other.idMaterialMolde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.MaterialMolde[ idMaterialMolde=" + idMaterialMolde + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.moldeList = new ArrayList<>(this.moldeList);
    }
    
}
