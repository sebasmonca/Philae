/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebas
 */
@Entity
@Table(name = "maquina")
@NamedQueries({
    @NamedQuery(name = "Maquina.findAll", query = "SELECT m FROM Maquina m"),
    @NamedQuery(name = "Maquina.findByIdMaquina", query = "SELECT m FROM Maquina m WHERE m.idMaquina = :idMaquina"),
    @NamedQuery(name = "Maquina.findByNombremaquina", query = "SELECT m FROM Maquina m WHERE m.nombremaquina = :nombremaquina"),
    @NamedQuery(name = "Maquina.findByMarcamaquina", query = "SELECT m FROM Maquina m WHERE m.marcamaquina = :marcamaquina"),
    @NamedQuery(name = "Maquina.findByModelomaquina", query = "SELECT m FROM Maquina m WHERE m.modelomaquina = :modelomaquina"),
    @NamedQuery(name = "Maquina.findByFechaingreso", query = "SELECT m FROM Maquina m WHERE m.fechaingreso = :fechaingreso")})
public class Maquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMaquina")
    private Integer idMaquina;
    @Basic(optional = false)
    @Column(name = "Nombre_maquina")
    private String nombremaquina;
    @Column(name = "Marca_maquina")
    private String marcamaquina;
    @Column(name = "Modelo_maquina")
    private String modelomaquina;
    @Basic(optional = false)
    @Column(name = "Fecha_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fechaingreso;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMaquina", fetch = FetchType.EAGER)
    private List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaList;
    @JoinColumn(name = "idEstado_Molde_Maquina", referencedColumnName = "idEstado_Molde_Maquina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private EstadoMoldeMaquina idEstadoMoldeMaquina;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "maquinaidMaquina", fetch = FetchType.EAGER)
    private List<ProgramacionMaquina> programacionMaquinaList;

    public Maquina() {
    }

    public Maquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Maquina(Integer idMaquina, String nombremaquina, Date fechaingreso) {
        this.idMaquina = idMaquina;
        this.nombremaquina = nombremaquina;
        this.fechaingreso = fechaingreso;
    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getNombremaquina() {
        return nombremaquina;
    }

    public void setNombremaquina(String nombremaquina) {
        this.nombremaquina = nombremaquina;
    }

    public String getMarcamaquina() {
        return marcamaquina;
    }

    public void setMarcamaquina(String marcamaquina) {
        this.marcamaquina = marcamaquina;
    }

    public String getModelomaquina() {
        return modelomaquina;
    }

    public void setModelomaquina(String modelomaquina) {
        this.modelomaquina = modelomaquina;
    }

    public Date getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(Date fechaingreso) {
        this.fechaingreso = fechaingreso;
    }

    public List<MantenimientoMoldeMaquina> getMantenimientoMoldeMaquinaList() {
        return mantenimientoMoldeMaquinaList;
    }

    public void setMantenimientoMoldeMaquinaList(List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaList) {
        this.mantenimientoMoldeMaquinaList = mantenimientoMoldeMaquinaList;
    }

    public EstadoMoldeMaquina getIdEstadoMoldeMaquina() {
        return idEstadoMoldeMaquina;
    }

    public void setIdEstadoMoldeMaquina(EstadoMoldeMaquina idEstadoMoldeMaquina) {
        this.idEstadoMoldeMaquina = idEstadoMoldeMaquina;
    }

    public List<ProgramacionMaquina> getProgramacionMaquinaList() {
        return programacionMaquinaList;
    }

    public void setProgramacionMaquinaList(List<ProgramacionMaquina> programacionMaquinaList) {
        this.programacionMaquinaList = programacionMaquinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMaquina != null ? idMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Maquina)) {
            return false;
        }
        Maquina other = (Maquina) object;
        if ((this.idMaquina == null && other.idMaquina != null) || (this.idMaquina != null && !this.idMaquina.equals(other.idMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.Maquina[ idMaquina=" + idMaquina + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.mantenimientoMoldeMaquinaList = new ArrayList<>(this.mantenimientoMoldeMaquinaList);
        this.programacionMaquinaList = new ArrayList<>(this.programacionMaquinaList);
    }
    
}
