/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Sebas
 */
@Entity
@Table(name = "mantenimiento_molde_maquina")
@NamedQueries({
    @NamedQuery(name = "MantenimientoMoldeMaquina.findAll", query = "SELECT m FROM MantenimientoMoldeMaquina m"),
    @NamedQuery(name = "MantenimientoMoldeMaquina.findByIdMantenimientoMoldeMaquina", query = "SELECT m FROM MantenimientoMoldeMaquina m WHERE m.idMantenimientoMoldeMaquina = :idMantenimientoMoldeMaquina"),
    @NamedQuery(name = "MantenimientoMoldeMaquina.findByTipomantenimiento", query = "SELECT m FROM MantenimientoMoldeMaquina m WHERE m.tipomantenimiento = :tipomantenimiento"),
    @NamedQuery(name = "MantenimientoMoldeMaquina.findByFechamantenimiento", query = "SELECT m FROM MantenimientoMoldeMaquina m WHERE m.fechamantenimiento = :fechamantenimiento"),
    @NamedQuery(name = "MantenimientoMoldeMaquina.findByFechaproximamantenimiento", query = "SELECT m FROM MantenimientoMoldeMaquina m WHERE m.fechaproximamantenimiento = :fechaproximamantenimiento"),
    @NamedQuery(name = "MantenimientoMoldeMaquina.findByEstadomantenimiento", query = "SELECT m FROM MantenimientoMoldeMaquina m WHERE m.estadomantenimiento = :estadomantenimiento")})
public class MantenimientoMoldeMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMantenimiento_Molde_Maquina")
    private Integer idMantenimientoMoldeMaquina;
    @Basic(optional = false)
    @Column(name = "Tipo_mantenimiento")
    private String tipomantenimiento;
    @Column(name = "Fecha_mantenimiento")
    @Temporal(TemporalType.DATE)
    private Date fechamantenimiento;
    @Basic(optional = false)
    @Column(name = "Fecha_proxima_mantenimiento")
    private String fechaproximamantenimiento;
    @Basic(optional = false)
    @Column(name = "Estado_mantenimiento")
    private String estadomantenimiento;
    @Lob
    @Column(name = "Observaciones_mantenimiento")
    private String observacionesmantenimiento;
    @JoinColumn(name = "idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Maquina idMaquina;
    @JoinColumn(name = "idMolde", referencedColumnName = "idMolde")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Molde idMolde;

    public MantenimientoMoldeMaquina() {
    }

    public MantenimientoMoldeMaquina(Integer idMantenimientoMoldeMaquina) {
        this.idMantenimientoMoldeMaquina = idMantenimientoMoldeMaquina;
    }

    public MantenimientoMoldeMaquina(Integer idMantenimientoMoldeMaquina, String tipomantenimiento, String fechaproximamantenimiento, String estadomantenimiento) {
        this.idMantenimientoMoldeMaquina = idMantenimientoMoldeMaquina;
        this.tipomantenimiento = tipomantenimiento;
        this.fechaproximamantenimiento = fechaproximamantenimiento;
        this.estadomantenimiento = estadomantenimiento;
    }

    public Integer getIdMantenimientoMoldeMaquina() {
        return idMantenimientoMoldeMaquina;
    }

    public void setIdMantenimientoMoldeMaquina(Integer idMantenimientoMoldeMaquina) {
        this.idMantenimientoMoldeMaquina = idMantenimientoMoldeMaquina;
    }

    public String getTipomantenimiento() {
        return tipomantenimiento;
    }

    public void setTipomantenimiento(String tipomantenimiento) {
        this.tipomantenimiento = tipomantenimiento;
    }

    public Date getFechamantenimiento() {
        return fechamantenimiento;
    }

    public void setFechamantenimiento(Date fechamantenimiento) {
        this.fechamantenimiento = fechamantenimiento;
    }

    public String getFechaproximamantenimiento() {
        return fechaproximamantenimiento;
    }

    public void setFechaproximamantenimiento(String fechaproximamantenimiento) {
        this.fechaproximamantenimiento = fechaproximamantenimiento;
    }

    public String getEstadomantenimiento() {
        return estadomantenimiento;
    }

    public void setEstadomantenimiento(String estadomantenimiento) {
        this.estadomantenimiento = estadomantenimiento;
    }

    public String getObservacionesmantenimiento() {
        return observacionesmantenimiento;
    }

    public void setObservacionesmantenimiento(String observacionesmantenimiento) {
        this.observacionesmantenimiento = observacionesmantenimiento;
    }

    public Maquina getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Maquina idMaquina) {
        this.idMaquina = idMaquina;
    }

    public Molde getIdMolde() {
        return idMolde;
    }

    public void setIdMolde(Molde idMolde) {
        this.idMolde = idMolde;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMantenimientoMoldeMaquina != null ? idMantenimientoMoldeMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MantenimientoMoldeMaquina)) {
            return false;
        }
        MantenimientoMoldeMaquina other = (MantenimientoMoldeMaquina) object;
        if ((this.idMantenimientoMoldeMaquina == null && other.idMantenimientoMoldeMaquina != null) || (this.idMantenimientoMoldeMaquina != null && !this.idMantenimientoMoldeMaquina.equals(other.idMantenimientoMoldeMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.MantenimientoMoldeMaquina[ idMantenimientoMoldeMaquina=" + idMantenimientoMoldeMaquina + " ]";
    }
    
}
