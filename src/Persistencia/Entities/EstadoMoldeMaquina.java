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
@Table(name = "estado_molde_maquina")
@NamedQueries({
    @NamedQuery(name = "EstadoMoldeMaquina.findAll", query = "SELECT e FROM EstadoMoldeMaquina e"),
    @NamedQuery(name = "EstadoMoldeMaquina.findByIdEstadoMoldeMaquina", query = "SELECT e FROM EstadoMoldeMaquina e WHERE e.idEstadoMoldeMaquina = :idEstadoMoldeMaquina"),
    @NamedQuery(name = "EstadoMoldeMaquina.findByDescripcionmolde", query = "SELECT e FROM EstadoMoldeMaquina e WHERE e.descripcionmolde = :descripcionmolde")})
public class EstadoMoldeMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idEstado_Molde_Maquina")
    private Integer idEstadoMoldeMaquina;
    @Basic(optional = false)
    @Column(name = "Descripcion_molde")
    private String descripcionmolde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoMoldeMaquina", fetch = FetchType.EAGER)
    private List<Maquina> maquinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoMoldeMaquina", fetch = FetchType.EAGER)
    private List<Molde> moldeList;

    public EstadoMoldeMaquina() {
    }

    public EstadoMoldeMaquina(Integer idEstadoMoldeMaquina) {
        this.idEstadoMoldeMaquina = idEstadoMoldeMaquina;
    }

    public EstadoMoldeMaquina(Integer idEstadoMoldeMaquina, String descripcionmolde) {
        this.idEstadoMoldeMaquina = idEstadoMoldeMaquina;
        this.descripcionmolde = descripcionmolde;
    }

    public Integer getIdEstadoMoldeMaquina() {
        return idEstadoMoldeMaquina;
    }

    public void setIdEstadoMoldeMaquina(Integer idEstadoMoldeMaquina) {
        this.idEstadoMoldeMaquina = idEstadoMoldeMaquina;
    }

    public String getDescripcionmolde() {
        return descripcionmolde;
    }

    public void setDescripcionmolde(String descripcionmolde) {
        this.descripcionmolde = descripcionmolde;
    }

    public List<Maquina> getMaquinaList() {
        return maquinaList;
    }

    public void setMaquinaList(List<Maquina> maquinaList) {
        this.maquinaList = maquinaList;
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
        hash += (idEstadoMoldeMaquina != null ? idEstadoMoldeMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstadoMoldeMaquina)) {
            return false;
        }
        EstadoMoldeMaquina other = (EstadoMoldeMaquina) object;
        if ((this.idEstadoMoldeMaquina == null && other.idEstadoMoldeMaquina != null) || (this.idEstadoMoldeMaquina != null && !this.idEstadoMoldeMaquina.equals(other.idEstadoMoldeMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.EstadoMoldeMaquina[ idEstadoMoldeMaquina=" + idEstadoMoldeMaquina + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.maquinaList = new ArrayList<>(this.maquinaList);
        this.moldeList = new ArrayList<>(this.moldeList);
    }
    
}
