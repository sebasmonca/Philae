/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia.Implementacion;

import Persistencia.Controller.TipoDocumentoJpaController;
import Persistencia.Entities.TipoDocumento;
import java.util.List;
import javax.persistence.Persistence;

/**
 *
 * @author Sebas
 */
public class ImplTipo_documento {

    private TipoDocumentoJpaController tipoDocumentoJpaController;
    public ImplTipo_documento() {
        try {
            this.tipoDocumentoJpaController = new TipoDocumentoJpaController(Persistence.createEntityManagerFactory("AvancePhilaePU"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public List<Persistencia.Entities.TipoDocumento> findTipoDocumentoEntities() {
        return this.tipoDocumentoJpaController.findTipoDocumentoEntities();
    }
    public Persistencia.Entities.TipoDocumento findTipoDocumentoById(Integer id) {
        return this.tipoDocumentoJpaController.findTipoDocumento(id);
    }
    public TipoDocumento createTipoDocumento(Persistencia.Entities.TipoDocumento tipoDocumento) {
        try {
            this.tipoDocumentoJpaController.create(tipoDocumento);
            return tipoDocumento;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public Persistencia.Entities.TipoDocumento editarTipoDocumento(Persistencia.Entities.TipoDocumento tipo_Documento) {
        try {
            this.tipoDocumentoJpaController.edit(tipo_Documento);
            return tipo_Documento;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Persistencia.Entities.TipoDocumento elimiarTipoDocumento(Persistencia.Entities.TipoDocumento tipo_Documento) {
        try {
            this.tipoDocumentoJpaController.destroy(tipo_Documento.getIdTipodocumento());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
     public List<Persistencia.Entities.TipoDocumento> buscar(String id,String descripcion) {
        try {
            return this.tipoDocumentoJpaController.buscar(id,descripcion);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
