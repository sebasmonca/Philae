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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "orden_produccion")
@NamedQueries({
    @NamedQuery(name = "OrdenProduccion.findAll", query = "SELECT o FROM OrdenProduccion o"),
    @NamedQuery(name = "OrdenProduccion.findByIdOrdenProduccion", query = "SELECT o FROM OrdenProduccion o WHERE o.idOrdenProduccion = :idOrdenProduccion"),
    @NamedQuery(name = "OrdenProduccion.findByPesoproducto", query = "SELECT o FROM OrdenProduccion o WHERE o.pesoproducto = :pesoproducto"),
    @NamedQuery(name = "OrdenProduccion.findByAlturaproducto", query = "SELECT o FROM OrdenProduccion o WHERE o.alturaproducto = :alturaproducto"),
    @NamedQuery(name = "OrdenProduccion.findByDiametroproducto", query = "SELECT o FROM OrdenProduccion o WHERE o.diametroproducto = :diametroproducto"),
    @NamedQuery(name = "OrdenProduccion.findByCapacidadproducto", query = "SELECT o FROM OrdenProduccion o WHERE o.capacidadproducto = :capacidadproducto"),
    @NamedQuery(name = "OrdenProduccion.findByDensidadProducto", query = "SELECT o FROM OrdenProduccion o WHERE o.densidadProducto = :densidadProducto"),
    @NamedQuery(name = "OrdenProduccion.findByNumeropiezas", query = "SELECT o FROM OrdenProduccion o WHERE o.numeropiezas = :numeropiezas"),
    @NamedQuery(name = "OrdenProduccion.findByMateriaprimainicial", query = "SELECT o FROM OrdenProduccion o WHERE o.materiaprimainicial = :materiaprimainicial"),
    @NamedQuery(name = "OrdenProduccion.findByCantidadreprocesos", query = "SELECT o FROM OrdenProduccion o WHERE o.cantidadreprocesos = :cantidadreprocesos"),
    @NamedQuery(name = "OrdenProduccion.findByMateriaprimasobrante", query = "SELECT o FROM OrdenProduccion o WHERE o.materiaprimasobrante = :materiaprimasobrante")})
public class OrdenProduccion implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idOrden_Produccion")
    private Integer idOrdenProduccion;
    @Column(name = "Peso_producto")
    private String pesoproducto;
    @Column(name = "Altura_producto")
    private String alturaproducto;
    @Column(name = "Diametro_producto")
    private String diametroproducto;
    @Column(name = "Capacidad_producto")
    private String capacidadproducto;
    @Column(name = "Densidad_Producto")
    private String densidadProducto;
    @Basic(optional = false)
    @Column(name = "Numero_piezas")
    private String numeropiezas;
    @Basic(optional = false)
    @Column(name = "Materia_prima_inicial")
    private String materiaprimainicial;
    @Basic(optional = false)
    @Column(name = "Cantidad_reprocesos")
    private String cantidadreprocesos;
    @Basic(optional = false)
    @Column(name = "Materia_prima_sobrante")
    private String materiaprimasobrante;
    @JoinColumn(name = "dCliente", referencedColumnName = "idCliente")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Cliente dCliente;
    @JoinColumn(name = "idColor_Producto", referencedColumnName = "idColor_Producto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ColorProducto idColorProducto;
    @JoinColumn(name = "idConfiguracion_Producto", referencedColumnName = "idConfiguracion_Producto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private ConfiguracionProducto idConfiguracionProducto;
    @JoinColumn(name = "idMolde", referencedColumnName = "idMolde")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Molde idMolde;
    @JoinColumn(name = "idTipo_Material_Producto", referencedColumnName = "idTipo_Material_Producto")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoMaterialProducto idTipoMaterialProducto;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idOrdenProduccion", fetch = FetchType.EAGER)
    private List<ProgramacionMaquina> programacionMaquinaList;

    public OrdenProduccion() {
    }

    public OrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public OrdenProduccion(Integer idOrdenProduccion, String numeropiezas, String materiaprimainicial, String cantidadreprocesos, String materiaprimasobrante) {
        this.idOrdenProduccion = idOrdenProduccion;
        this.numeropiezas = numeropiezas;
        this.materiaprimainicial = materiaprimainicial;
        this.cantidadreprocesos = cantidadreprocesos;
        this.materiaprimasobrante = materiaprimasobrante;
    }

    public Integer getIdOrdenProduccion() {
        return idOrdenProduccion;
    }

    public void setIdOrdenProduccion(Integer idOrdenProduccion) {
        this.idOrdenProduccion = idOrdenProduccion;
    }

    public String getPesoproducto() {
        return pesoproducto;
    }

    public void setPesoproducto(String pesoproducto) {
        this.pesoproducto = pesoproducto;
    }

    public String getAlturaproducto() {
        return alturaproducto;
    }

    public void setAlturaproducto(String alturaproducto) {
        this.alturaproducto = alturaproducto;
    }

    public String getDiametroproducto() {
        return diametroproducto;
    }

    public void setDiametroproducto(String diametroproducto) {
        this.diametroproducto = diametroproducto;
    }

    public String getCapacidadproducto() {
        return capacidadproducto;
    }

    public void setCapacidadproducto(String capacidadproducto) {
        this.capacidadproducto = capacidadproducto;
    }

    public String getDensidadProducto() {
        return densidadProducto;
    }

    public void setDensidadProducto(String densidadProducto) {
        this.densidadProducto = densidadProducto;
    }

    public String getNumeropiezas() {
        return numeropiezas;
    }

    public void setNumeropiezas(String numeropiezas) {
        this.numeropiezas = numeropiezas;
    }

    public String getMateriaprimainicial() {
        return materiaprimainicial;
    }

    public void setMateriaprimainicial(String materiaprimainicial) {
        this.materiaprimainicial = materiaprimainicial;
    }

    public String getCantidadreprocesos() {
        return cantidadreprocesos;
    }

    public void setCantidadreprocesos(String cantidadreprocesos) {
        this.cantidadreprocesos = cantidadreprocesos;
    }

    public String getMateriaprimasobrante() {
        return materiaprimasobrante;
    }

    public void setMateriaprimasobrante(String materiaprimasobrante) {
        this.materiaprimasobrante = materiaprimasobrante;
    }

    public Cliente getDCliente() {
        return dCliente;
    }

    public void setDCliente(Cliente dCliente) {
        this.dCliente = dCliente;
    }

    public ColorProducto getIdColorProducto() {
        return idColorProducto;
    }

    public void setIdColorProducto(ColorProducto idColorProducto) {
        this.idColorProducto = idColorProducto;
    }

    public ConfiguracionProducto getIdConfiguracionProducto() {
        return idConfiguracionProducto;
    }

    public void setIdConfiguracionProducto(ConfiguracionProducto idConfiguracionProducto) {
        this.idConfiguracionProducto = idConfiguracionProducto;
    }

    public Molde getIdMolde() {
        return idMolde;
    }

    public void setIdMolde(Molde idMolde) {
        this.idMolde = idMolde;
    }

    public TipoMaterialProducto getIdTipoMaterialProducto() {
        return idTipoMaterialProducto;
    }

    public void setIdTipoMaterialProducto(TipoMaterialProducto idTipoMaterialProducto) {
        this.idTipoMaterialProducto = idTipoMaterialProducto;
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
        hash += (idOrdenProduccion != null ? idOrdenProduccion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrdenProduccion)) {
            return false;
        }
        OrdenProduccion other = (OrdenProduccion) object;
        if ((this.idOrdenProduccion == null && other.idOrdenProduccion != null) || (this.idOrdenProduccion != null && !this.idOrdenProduccion.equals(other.idOrdenProduccion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.OrdenProduccion[ idOrdenProduccion=" + idOrdenProduccion + " ]";
    }
    @PostLoad
    public void fixIt() {
        this.programacionMaquinaList = new ArrayList<>(this.programacionMaquinaList);
    }
    
}
