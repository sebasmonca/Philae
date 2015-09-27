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
@Table(name = "tiempo_productivo")
@NamedQueries({
    @NamedQuery(name = "TiempoProductivo.findAll", query = "SELECT t FROM TiempoProductivo t"),
    @NamedQuery(name = "TiempoProductivo.findByIdTiempoProductivo", query = "SELECT t FROM TiempoProductivo t WHERE t.idTiempoProductivo = :idTiempoProductivo"),
    @NamedQuery(name = "TiempoProductivo.findByFechainicioprod", query = "SELECT t FROM TiempoProductivo t WHERE t.fechainicioprod = :fechainicioprod"),
    @NamedQuery(name = "TiempoProductivo.findByHorainicioprod", query = "SELECT t FROM TiempoProductivo t WHERE t.horainicioprod = :horainicioprod"),
    @NamedQuery(name = "TiempoProductivo.findByFechafinprod", query = "SELECT t FROM TiempoProductivo t WHERE t.fechafinprod = :fechafinprod"),
    @NamedQuery(name = "TiempoProductivo.findByHorafinprod", query = "SELECT t FROM TiempoProductivo t WHERE t.horafinprod = :horafinprod")})
public class TiempoProductivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTiempo_Productivo")
    private Integer idTiempoProductivo;
    @Basic(optional = false)
    @Column(name = "Fecha_inicio_prod")
    @Temporal(TemporalType.DATE)
    private Date fechainicioprod;
    @Basic(optional = false)
    @Column(name = "Hora_inicio_prod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainicioprod;
    @Column(name = "Fecha_fin_prod")
    @Temporal(TemporalType.DATE)
    private Date fechafinprod;
    @Column(name = "Hora_fin_prod")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horafinprod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiempoProductivoidTiempoProductivo", fetch = FetchType.EAGER)
    private List<TrabajoMaquina> trabajoMaquinaList;

    public TiempoProductivo() {
    }

    public TiempoProductivo(Integer idTiempoProductivo) {
        this.idTiempoProductivo = idTiempoProductivo;
    }

    public TiempoProductivo(Integer idTiempoProductivo, Date fechainicioprod, Date horainicioprod) {
        this.idTiempoProductivo = idTiempoProductivo;
        this.fechainicioprod = fechainicioprod;
        this.horainicioprod = horainicioprod;
    }

    public Integer getIdTiempoProductivo() {
        return idTiempoProductivo;
    }

    public void setIdTiempoProductivo(Integer idTiempoProductivo) {
        this.idTiempoProductivo = idTiempoProductivo;
    }

    public Date getFechainicioprod() {
        return fechainicioprod;
    }

    public void setFechainicioprod(Date fechainicioprod) {
        this.fechainicioprod = fechainicioprod;
    }

    public Date getHorainicioprod() {
        return horainicioprod;
    }

    public void setHorainicioprod(Date horainicioprod) {
        this.horainicioprod = horainicioprod;
    }

    public Date getFechafinprod() {
        return fechafinprod;
    }

    public void setFechafinprod(Date fechafinprod) {
        this.fechafinprod = fechafinprod;
    }

    public Date getHorafinprod() {
        return horafinprod;
    }

    public void setHorafinprod(Date horafinprod) {
        this.horafinprod = horafinprod;
    }

    public List<TrabajoMaquina> getTrabajoMaquinaList() {
        return trabajoMaquinaList;
    }

    public void setTrabajoMaquinaList(List<TrabajoMaquina> trabajoMaquinaList) {
        this.trabajoMaquinaList = trabajoMaquinaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTiempoProductivo != null ? idTiempoProductivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiempoProductivo)) {
            return false;
        }
        TiempoProductivo other = (TiempoProductivo) object;
        if ((this.idTiempoProductivo == null && other.idTiempoProductivo != null) || (this.idTiempoProductivo != null && !this.idTiempoProductivo.equals(other.idTiempoProductivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.TiempoProductivo[ idTiempoProductivo=" + idTiempoProductivo + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.trabajoMaquinaList = new ArrayList<>(this.trabajoMaquinaList);
    }
    
}
