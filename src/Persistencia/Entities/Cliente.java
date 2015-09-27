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
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM Cliente c"),
    @NamedQuery(name = "Cliente.findByIdCliente", query = "SELECT c FROM Cliente c WHERE c.idCliente = :idCliente"),
    @NamedQuery(name = "Cliente.findByNumeroidentificacioncliente", query = "SELECT c FROM Cliente c WHERE c.numeroidentificacioncliente = :numeroidentificacioncliente"),
    @NamedQuery(name = "Cliente.findByRazonsocialcliente", query = "SELECT c FROM Cliente c WHERE c.razonsocialcliente = :razonsocialcliente"),
    @NamedQuery(name = "Cliente.findByDireccioncliente", query = "SELECT c FROM Cliente c WHERE c.direccioncliente = :direccioncliente"),
    @NamedQuery(name = "Cliente.findByTelefonocliente", query = "SELECT c FROM Cliente c WHERE c.telefonocliente = :telefonocliente"),
    @NamedQuery(name = "Cliente.findByNombrecontacto", query = "SELECT c FROM Cliente c WHERE c.nombrecontacto = :nombrecontacto"),
    @NamedQuery(name = "Cliente.findByApellidocontacto", query = "SELECT c FROM Cliente c WHERE c.apellidocontacto = :apellidocontacto"),
    @NamedQuery(name = "Cliente.findByTelefonocontacto", query = "SELECT c FROM Cliente c WHERE c.telefonocontacto = :telefonocontacto"),
    @NamedQuery(name = "Cliente.findByTelefonomovilcontacto", query = "SELECT c FROM Cliente c WHERE c.telefonomovilcontacto = :telefonomovilcontacto"),
    @NamedQuery(name = "Cliente.findByCorreocontactocliente", query = "SELECT c FROM Cliente c WHERE c.correocontactocliente = :correocontactocliente")})
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCliente")
    private Integer idCliente;
    @Basic(optional = false)
    @Column(name = "Numero_identificacion_cliente")
    private String numeroidentificacioncliente;
    @Basic(optional = false)
    @Column(name = "Razon_social_cliente")
    private String razonsocialcliente;
    @Basic(optional = false)
    @Column(name = "Direccion_cliente")
    private String direccioncliente;
    @Basic(optional = false)
    @Column(name = "Telefono_cliente")
    private int telefonocliente;
    @Basic(optional = false)
    @Column(name = "Nombre_contacto")
    private String nombrecontacto;
    @Basic(optional = false)
    @Column(name = "Apellido_contacto")
    private String apellidocontacto;
    @Basic(optional = false)
    @Column(name = "Telefono_contacto")
    private int telefonocontacto;
    @Basic(optional = false)
    @Column(name = "Telefono_movil_contacto")
    private int telefonomovilcontacto;
    @Basic(optional = false)
    @Column(name = "Correo_contacto_cliente")
    private String correocontactocliente;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dCliente", fetch = FetchType.EAGER)
    private List<OrdenProduccion> ordenProduccionList;
    @JoinColumn(name = "idTipo_documento", referencedColumnName = "idTipo_documento")
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private TipoDocumento idTipodocumento;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCliente", fetch = FetchType.EAGER)
    private List<Molde> moldeList;

    public Cliente() {
    }

    public Cliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(Integer idCliente, String numeroidentificacioncliente, String razonsocialcliente, String direccioncliente, int telefonocliente, String nombrecontacto, String apellidocontacto, int telefonocontacto, int telefonomovilcontacto, String correocontactocliente) {
        this.idCliente = idCliente;
        this.numeroidentificacioncliente = numeroidentificacioncliente;
        this.razonsocialcliente = razonsocialcliente;
        this.direccioncliente = direccioncliente;
        this.telefonocliente = telefonocliente;
        this.nombrecontacto = nombrecontacto;
        this.apellidocontacto = apellidocontacto;
        this.telefonocontacto = telefonocontacto;
        this.telefonomovilcontacto = telefonomovilcontacto;
        this.correocontactocliente = correocontactocliente;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNumeroidentificacioncliente() {
        return numeroidentificacioncliente;
    }

    public void setNumeroidentificacioncliente(String numeroidentificacioncliente) {
        this.numeroidentificacioncliente = numeroidentificacioncliente;
    }

    public String getRazonsocialcliente() {
        return razonsocialcliente;
    }

    public void setRazonsocialcliente(String razonsocialcliente) {
        this.razonsocialcliente = razonsocialcliente;
    }

    public String getDireccioncliente() {
        return direccioncliente;
    }

    public void setDireccioncliente(String direccioncliente) {
        this.direccioncliente = direccioncliente;
    }

    public int getTelefonocliente() {
        return telefonocliente;
    }

    public void setTelefonocliente(int telefonocliente) {
        this.telefonocliente = telefonocliente;
    }

    public String getNombrecontacto() {
        return nombrecontacto;
    }

    public void setNombrecontacto(String nombrecontacto) {
        this.nombrecontacto = nombrecontacto;
    }

    public String getApellidocontacto() {
        return apellidocontacto;
    }

    public void setApellidocontacto(String apellidocontacto) {
        this.apellidocontacto = apellidocontacto;
    }

    public int getTelefonocontacto() {
        return telefonocontacto;
    }

    public void setTelefonocontacto(int telefonocontacto) {
        this.telefonocontacto = telefonocontacto;
    }

    public int getTelefonomovilcontacto() {
        return telefonomovilcontacto;
    }

    public void setTelefonomovilcontacto(int telefonomovilcontacto) {
        this.telefonomovilcontacto = telefonomovilcontacto;
    }

    public String getCorreocontactocliente() {
        return correocontactocliente;
    }

    public void setCorreocontactocliente(String correocontactocliente) {
        this.correocontactocliente = correocontactocliente;
    }

    public List<OrdenProduccion> getOrdenProduccionList() {
        return ordenProduccionList;
    }

    public void setOrdenProduccionList(List<OrdenProduccion> ordenProduccionList) {
        this.ordenProduccionList = ordenProduccionList;
    }

    public TipoDocumento getIdTipodocumento() {
        return idTipodocumento;
    }

    public void setIdTipodocumento(TipoDocumento idTipodocumento) {
        this.idTipodocumento = idTipodocumento;
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
        hash += (idCliente != null ? idCliente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cliente)) {
            return false;
        }
        Cliente other = (Cliente) object;
        if ((this.idCliente == null && other.idCliente != null) || (this.idCliente != null && !this.idCliente.equals(other.idCliente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persistencia.Entities.Cliente[ idCliente=" + idCliente + " ]";
    }

    @PostLoad
    public void fixIt() {
        this.moldeList = new ArrayList<>(this.moldeList);
        this.ordenProduccionList = new ArrayList<>(this.ordenProduccionList);
    }

}
