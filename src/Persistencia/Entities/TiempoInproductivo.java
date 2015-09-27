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
@Table(name = "tiempo_inproductivo")
@NamedQueries({
    @NamedQuery(name = "TiempoInproductivo.findAll", query = "SELECT t FROM TiempoInproductivo t"),
    @NamedQuery(name = "TiempoInproductivo.findByIdTiempoInproductivo", query = "SELECT t FROM TiempoInproductivo t WHERE t.idTiempoInproductivo = :idTiempoInproductivo"),
    @NamedQuery(name = "TiempoInproductivo.findByFechainicioimprod", query = "SELECT t FROM TiempoInproductivo t WHERE t.fechainicioimprod = :fechainicioimprod"),
    @NamedQuery(name = "TiempoInproductivo.findByHorainicialimprod", query = "SELECT t FROM TiempoInproductivo t WHERE t.horainicialimprod = :horainicialimprod"),
    @NamedQuery(name = "TiempoInproductivo.findByFechafinimprod", query = "SELECT t FROM TiempoInproductivo t WHERE t.fechafinimprod = :fechafinimprod"),
    @NamedQuery(name = "TiempoInproductivo.findByHorafinalimprod", query = "SELECT t FROM TiempoInproductivo t WHERE t.horafinalimprod = :horafinalimprod")})
public class TiempoInproductivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTiempo_Inproductivo")
    private Integer idTiempoInproductivo;
    @Basic(optional = false)
    @Column(name = "Fecha_inicio_improd")
    @Temporal(TemporalType.DATE)
    private Date fechainicioimprod;
    @Basic(optional = false)
    @Column(name = "Hora_inicial_improd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horainicialimprod;
    @Column(name = "Fecha_fin_improd")
    @Temporal(TemporalType.DATE)
    private Date fechafinimprod;
    @Column(name = "Hora_final_improd")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horafinalimprod;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tiempoInproductivoidTiempoInproductivo", fetch = FetchType.EAGER)
    private List<TrabajoMaquina> trabajoMaquinaList;

    public TiempoInproductivo() {
    }

    public TiempoInproductivo(Integer idTiempoInproductivo) {
        this.idTiempoInproductivo = idTiempoInproductivo;
    }

    public TiempoInproductivo(Integer idTiempoInproductivo, Date fechainicioimprod, Date horainicialimprod) {
        this.idTiempoInproductivo = idTiempoInproductivo;
        this.fechainicioimprod = fechainicioimprod;
        this.horainicialimprod = horainicialimprod;
    }

    public Integer getIdTiempoInproductivo() {
        return idTiempoInproductivo;
    }

    public void setIdTiempoInproductivo(Integer idTiempoInproductivo) {
        this.idTiempoInproductivo = idTiempoInproductivo;
    }

    public Date getFechainicioimprod() {
        return fechainicioimprod;
    }

    public void setFechainicioimprod(Date fechainicioimprod) {
        this.fechainicioimprod = fechainicioimprod;
    }

    public Date getHorainicialimprod() {
        return horainicialimprod;
    }

    public void setHorainicialimprod(Date horainicialimprod) {
        this.horainicialimprod = horainicialimprod;
    }

    public Date getFechafinimprod() {
        return fechafinimprod;
    }

    public void setFechafinimprod(Date fechafinimprod) {
        this.fechafinimprod = fechafinimprod;
    }

    public Date getHorafinalimprod() {
        return horafinalimprod;
    }

    public void setHorafinalimprod(Date horafinalimprod) {
        this.horafinalimprod = horafinalimprod;
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
        hash += (idTiempoInproductivo != null ? idTiempoInproductivo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiempoInproductivo)) {
            return false;
        }
        TiempoInproductivo other = (TiempoInproductivo) object;
        if ((this.idTiempoInproductivo == null && other.idTiempoInproductivo != null) || (this.idTiempoInproductivo != null && !this.idTiempoInproductivo.equals(other.idTiempoInproductivo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.TiempoInproductivo[ idTiempoInproductivo=" + idTiempoInproductivo + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.trabajoMaquinaList = new ArrayList<>(this.trabajoMaquinaList);
    }
    
}
