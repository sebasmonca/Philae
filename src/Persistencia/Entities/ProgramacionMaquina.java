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
@Table(name = "programacion_maquina")
@NamedQueries({
    @NamedQuery(name = "ProgramacionMaquina.findAll", query = "SELECT p FROM ProgramacionMaquina p"),
    @NamedQuery(name = "ProgramacionMaquina.findByIdProgramacionMaquina", query = "SELECT p FROM ProgramacionMaquina p WHERE p.idProgramacionMaquina = :idProgramacionMaquina"),
    @NamedQuery(name = "ProgramacionMaquina.findByFechaprogramadaprogramacion", query = "SELECT p FROM ProgramacionMaquina p WHERE p.fechaprogramadaprogramacion = :fechaprogramadaprogramacion"),
    @NamedQuery(name = "ProgramacionMaquina.findByTiempomontajemolde", query = "SELECT p FROM ProgramacionMaquina p WHERE p.tiempomontajemolde = :tiempomontajemolde"),
    @NamedQuery(name = "ProgramacionMaquina.findByTiempoproduccion", query = "SELECT p FROM ProgramacionMaquina p WHERE p.tiempoproduccion = :tiempoproduccion"),
    @NamedQuery(name = "ProgramacionMaquina.findByEmpleadoidEmpleado", query = "SELECT p FROM ProgramacionMaquina p WHERE p.empleadoidEmpleado = :empleadoidEmpleado")})
public class ProgramacionMaquina implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idProgramacion_Maquina")
    private Integer idProgramacionMaquina;
    @Basic(optional = false)
    @Column(name = "Fecha_programada_programacion")
    @Temporal(TemporalType.DATE)
    private Date fechaprogramadaprogramacion;
    @Basic(optional = false)
    @Column(name = "Tiempo_montaje_molde")
    private int tiempomontajemolde;
    @Basic(optional = false)
    @Column(name = "Tiempo_produccion")
    private int tiempoproduccion;
    @Basic(optional = false)
    @Column(name = "Empleado_idEmpleado")
    private int empleadoidEmpleado;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "programacionMaquinaidProgramacionMaquina", fetch = FetchType.EAGER)
    private List<TrabajoMaquina> trabajoMaquinaList;
    @JoinColumn(name = "Maquina_idMaquina", referencedColumnName = "idMaquina")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Maquina maquinaidMaquina;
    @JoinColumn(name = "Molde_idMolde", referencedColumnName = "idMolde")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Molde moldeidMolde;
    @JoinColumn(name = "idOrden_Produccion", referencedColumnName = "idOrden_Produccion")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private OrdenProduccion idOrdenProduccion;

    public ProgramacionMaquina() {
    }

    public ProgramacionMaquina(Integer idProgramacionMaquina) {
        this.idProgramacionMaquina = idProgramacionMaquina;
    }

    public ProgramacionMaquina(Integer idProgramacionMaquina, Date fechaprogramadaprogramacion, int tiempomontajemolde, int tiempoproduccion, int empleadoidEmpleado) {
        this.idProgramacionMaquina = idProgramacionMaquina;
        this.fechaprogramadaprogramacion = fechaprogramadaprogramacion;
        this.tiempomontajemolde = tiempomontajemolde;
        this.tiempoproduccion = tiempoproduccion;
        this.empleadoidEmpleado = empleadoidEmpleado;
    }

    public Integer getIdProgramacionMaquina() {
        return idProgramacionMaquina;
    }

    public void setIdProgramacionMaquina(Integer idProgramacionMaquina) {
        this.idProgramacionMaquina = idProgramacionMaquina;
    }

    public Date getFechaprogramadaprogramacion() {
        return fechaprogramadaprogramacion;
    }

    public void setFechaprogramadaprogramacion(Date fechaprogramadaprogramacion) {
        this.fechaprogramadaprogramacion = fechaprogramadaprogramacion;
    }

    public int getTiempomontajemolde() {
        return tiempomontajemolde;
    }

    public void setTiempomontajemolde(int tiempomontajemolde) {
        this.tiempomontajemolde = tiempomontajemolde;
    }

    public int getTiempoproduccion() {
        return tiempoproduccion;
    }

    public void setTiempoproduccion(int tiempoproduccion) {
        this.tiempoproduccion = tiempoproduccion;
    }

    public int getEmpleadoidEmpleado() {
        return empleadoidEmpleado;
    }

    public void setEmpleadoidEmpleado(int empleadoidEmpleado) {
        this.empleadoidEmpleado = empleadoidEmpleado;
    }

    public List<TrabajoMaquina> getTrabajoMaquinaList() {
        return trabajoMaquinaList;
    }

    public void setTrabajoMaquinaList(List<TrabajoMaquina> trabajoMaquinaList) {
        this.trabajoMaquinaList = trabajoMaquinaList;
    }

    public Maquina getMaquinaidMaquina() {
        return maquinaidMaquina;
    }

    public void setMaquinaidMaquina(Maquina maquinaidMaquina) {
        this.maquinaidMaquina = maquinaidMaquina;
    }

    public Molde getMoldeidMolde() {
        return moldeidMolde;
    }

    public void setMoldeidMolde(Molde moldeidMolde) {
        this.moldeidMolde = moldeidMolde;
    }

    public OrdenProduccion getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(OrdenProduccion idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idProgramacionMaquina != null ? idProgramacionMaquina.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProgramacionMaquina)) {
            return false;
        }
        ProgramacionMaquina other = (ProgramacionMaquina) object;
        if ((this.idProgramacionMaquina == null && other.idProgramacionMaquina != null) || (this.idProgramacionMaquina != null && !this.idProgramacionMaquina.equals(other.idProgramacionMaquina))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.ProgramacionMaquina[ idProgramacionMaquina=" + idProgramacionMaquina + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.trabajoMaquinaList = new ArrayList<>(this.trabajoMaquinaList);
    }
    
}
