/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Entities;

import java.io.Serializable;
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

/**
 *
 * @author Sebas
 */
@Entity
@Table(name = "trabajo_maquina")
@NamedQueries({
    @NamedQuery(name = "TrabajoMaquina.findAll", query = "SELECT t FROM TrabajoMaquina t"),
    @NamedQuery(name = "TrabajoMaquina.findByIdTrabajoMaquina", query = "SELECT t FROM TrabajoMaquina t WHERE t.idTrabajoMaquina = :idTrabajoMaquina")})
public class TrabajoMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idTrabajo_Maquina")
    private Integer idTrabajoMaquina;
    @Lob
    @Column(name = "Observaciones_trabajo_maquina")
    private String observacionestrabajomaquina;
    @JoinColumn(name = "Programacion_Maquina_idProgramacion_Maquina", referencedColumnName = "idProgramacion_Maquina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ProgramacionMaquina programacionMaquinaidProgramacionMaquina;
    @JoinColumn(name = "Tiempo_Inproductivo_idTiempo_Inproductivo", referencedColumnName = "idTiempo_Inproductivo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TiempoInproductivo tiempoInproductivoidTiempoInproductivo;
    @JoinColumn(name = "Tiempo_Productivo_idTiempo_Productivo", referencedColumnName = "idTiempo_Productivo")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TiempoProductivo tiempoProductivoidTiempoProductivo;

    public TrabajoMaquina() {
    }

    public TrabajoMaquina(Integer idTrabajoMaquina) {
        this.idTrabajoMaquina = idTrabajoMaquina;
    }

    public Integer getIdTrabajoMaquina() {
        return idTrabajoMaquina;
    }

    public void setIdTrabajoMaquina(Integer idTrabajoMaquina) {
        this.idTrabajoMaquina = idTrabajoMaquina;
    }

    public String getObservacionestrabajomaquina() {
        return observacionestrabajomaquina;
    }

    public void setObservacionestrabajomaquina(String observacionestrabajomaquina) {
        this.observacionestrabajomaquina = observacionestrabajomaquina;
    }

    public ProgramacionMaquina getProgramacionMaquinaidProgramacionMaquina() {
        return programacionMaquinaidProgramacionMaquina;
    }

    public void setProgramacionMaquinaidProgramacionMaquina(ProgramacionMaquina programacionMaquinaidProgramacionMaquina) {
        this.programacionMaquinaidProgramacionMaquina = programacionMaquinaidProgramacionMaquina;
    }

    public TiempoInproductivo getTiempoInproductivoidTiempoInproductivo() {
        return tiempoInproductivoidTiempoInproductivo;
    }

    public void setTiempoInproductivoidTiempoInproductivo(TiempoInproductivo tiempoInproductivoidTiempoInproductivo) {
        this.tiempoInproductivoidTiempoInproductivo = tiempoInproductivoidTiempoInproductivo;
    }

    public TiempoProductivo getTiempoProductivoidTiempoProductivo() {
        return tiempoProductivoidTiempoProductivo;
    }

    public void setTiempoProductivoidTiempoProductivo(TiempoProductivo tiempoProductivoidTiempoProductivo) {
        this.tiempoProductivoidTiempoProductivo = tiempoProductivoidTiempoProductivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTrabajoMaquina != null ? idTrabajoMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TrabajoMaquina)) {
            return false;
        }
        TrabajoMaquina other = (TrabajoMaquina) object;
        if ((this.idTrabajoMaquina == null && other.idTrabajoMaquina != null) || (this.idTrabajoMaquina != null && !this.idTrabajoMaquina.equals(other.idTrabajoMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.TrabajoMaquina[ idTrabajoMaquina=" + idTrabajoMaquina + " ]";
    }
    
}
