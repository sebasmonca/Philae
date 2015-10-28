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
import javax.persistence.Lob;
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
 * @author yuri
 */
@Entity
@Table(name = "Molde")
@NamedQueries({
    @NamedQuery(name = "Molde.findAll", query = "SELECT m FROM Molde m")})
public class Molde implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMolde")
    private Integer idMolde;
    @Column(name = "Nombre_molde")
    private String nombremolde;
    @Column(name = "Cavidades_molde")
    private String cavidadesmolde;
    @Column(name = "Ductos_molde")
    private String ductosmolde;
    @Column(name = "Canales_enfriamiento_molde")
    private String canalesenfriamientomolde;
    @Column(name = "Barras_expulsoras_molde")
    private String barrasexpulsorasmolde;
    @Basic(optional = false)
    @Column(name = "Peso_molde")
    private int pesomolde;
    @Basic(optional = false)
    @Column(name = "Ciclo_tiempo_molde")
    private int ciclotiempomolde;
    @Basic(optional = false)
    @Column(name = "Propiedad_intelectual_molde")
    private String propiedadintelectualmolde;
    @Column(name = "Fecha_creacion_molde")
    @Temporal(TemporalType.DATE)
    private Date fechacreacionmolde;
    @Column(name = "Fecha_ingreso_molde")
    @Temporal(TemporalType.DATE)
    private Date fechaingresomolde;
    @Lob
    @Column(name = "Observaciones_molde")
    private String observacionesmolde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMolde", fetch = FetchType.LAZY)
    private List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMolde", fetch = FetchType.LAZY)
    private List<OrdenProduccion> ordenProduccionList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idMolde", fetch = FetchType.LAZY)
    private List<ConfiguracionProducto> configuracionProductoList;
    @JoinColumn(name = "idCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "idEstado_Molde_Maquina", referencedColumnName = "idEstado_Molde_Maquina")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadoMoldeMaquina idEstadoMoldeMaquina;
    @JoinColumn(name = "idMaterial_Molde", referencedColumnName = "idMaterial_Molde")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private MaterialMolde idMaterialMolde;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "moldeidMolde", fetch = FetchType.LAZY)
    private List<ProgramacionMaquina> programacionMaquinaList;

    public Molde() {
    }

    public Molde(Integer idMolde) {
        this.idMolde = idMolde;
    }

    public Molde(Integer idMolde, int pesomolde, int ciclotiempomolde, String propiedadintelectualmolde) {
        this.idMolde = idMolde;
        this.pesomolde = pesomolde;
        this.ciclotiempomolde = ciclotiempomolde;
        this.propiedadintelectualmolde = propiedadintelectualmolde;
    }

    public Integer getIdMolde() {
        return idMolde;
    }

    public void setIdMolde(Integer idMolde) {
        this.idMolde = idMolde;
    }

    public String getNombremolde() {
        return nombremolde;
    }

    public void setNombremolde(String nombremolde) {
        this.nombremolde = nombremolde;
    }

    public String getCavidadesmolde() {
        return cavidadesmolde;
    }

    public void setCavidadesmolde(String cavidadesmolde) {
        this.cavidadesmolde = cavidadesmolde;
    }

    public String getDuctosmolde() {
        return ductosmolde;
    }

    public void setDuctosmolde(String ductosmolde) {
        this.ductosmolde = ductosmolde;
    }

    public String getCanalesenfriamientomolde() {
        return canalesenfriamientomolde;
    }

    public void setCanalesenfriamientomolde(String canalesenfriamientomolde) {
        this.canalesenfriamientomolde = canalesenfriamientomolde;
    }

    public String getBarrasexpulsorasmolde() {
        return barrasexpulsorasmolde;
    }

    public void setBarrasexpulsorasmolde(String barrasexpulsorasmolde) {
        this.barrasexpulsorasmolde = barrasexpulsorasmolde;
    }

    public int getPesomolde() {
        return pesomolde;
    }

    public void setPesomolde(int pesomolde) {
        this.pesomolde = pesomolde;
    }

    public int getCiclotiempomolde() {
        return ciclotiempomolde;
    }

    public void setCiclotiempomolde(int ciclotiempomolde) {
        this.ciclotiempomolde = ciclotiempomolde;
    }

    public String getPropiedadintelectualmolde() {
        return propiedadintelectualmolde;
    }

    public void setPropiedadintelectualmolde(String propiedadintelectualmolde) {
        this.propiedadintelectualmolde = propiedadintelectualmolde;
    }

    public Date getFechacreacionmolde() {
        return fechacreacionmolde;
    }

    public void setFechacreacionmolde(Date fechacreacionmolde) {
        this.fechacreacionmolde = fechacreacionmolde;
    }

    public Date getFechaingresomolde() {
        return fechaingresomolde;
    }

    public void setFechaingresomolde(Date fechaingresomolde) {
        this.fechaingresomolde = fechaingresomolde;
    }

    public String getObservacionesmolde() {
        return observacionesmolde;
    }

    public void setObservacionesmolde(String observacionesmolde) {
        this.observacionesmolde = observacionesmolde;
    }

    public List<MantenimientoMoldeMaquina> getMantenimientoMoldeMaquinaList() {
        return mantenimientoMoldeMaquinaList;
    }

    public void setMantenimientoMoldeMaquinaList(List<MantenimientoMoldeMaquina> mantenimientoMoldeMaquinaList) {
        this.mantenimientoMoldeMaquinaList = mantenimientoMoldeMaquinaList;
    }

    public List<OrdenProduccion> getOrdenProduccionList() {
        return ordenProduccionList;
    }

    public void setOrdenProduccionList(List<OrdenProduccion> ordenProduccionList) {
        this.ordenProduccionList = ordenProduccionList;
    }

    public List<ConfiguracionProducto> getConfiguracionProductoList() {
        return configuracionProductoList;
    }

    public void setConfiguracionProductoList(List<ConfiguracionProducto> configuracionProductoList) {
        this.configuracionProductoList = configuracionProductoList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public EstadoMoldeMaquina getIdEstadoMoldeMaquina() {
        return idEstadoMoldeMaquina;
    }

    public void setIdEstadoMoldeMaquina(EstadoMoldeMaquina idEstadoMoldeMaquina) {
        this.idEstadoMoldeMaquina = idEstadoMoldeMaquina;
    }

    public MaterialMolde getIdMaterialMolde() {
        return idMaterialMolde;
    }

    public void setIdMaterialMolde(MaterialMolde idMaterialMolde) {
        this.idMaterialMolde = idMaterialMolde;
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
        hash += (idMolde != null ? idMolde.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Molde)) {
            return false;
        }
        Molde other = (Molde) object;
        if ((this.idMolde == null && other.idMolde != null) || (this.idMolde != null && !this.idMolde.equals(other.idMolde))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.Molde[ idMolde=" + idMolde + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.mantenimientoMoldeMaquinaList = new ArrayList<>(this.mantenimientoMoldeMaquinaList);
        this.ordenProduccionList = new ArrayList<>(this.ordenProduccionList);
        this.programacionMaquinaList = new ArrayList<>(this.programacionMaquinaList);
        this.configuracionProductoList=new ArrayList<>(this.configuracionProductoList);
    }

}
