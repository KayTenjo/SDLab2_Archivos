/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Kay
 */
@Entity
@Table(name = "archivo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Archivo.findAll", query = "SELECT a FROM Archivo a"),
    @NamedQuery(name = "Archivo.findByArchivoId", query = "SELECT a FROM Archivo a WHERE a.archivoId = :archivoId"),
    @NamedQuery(name = "Archivo.findByArchivoNombreVirtual", query = "SELECT a FROM Archivo a WHERE a.archivoNombreVirtual = :archivoNombreVirtual"),
    @NamedQuery(name = "Archivo.findByArchivoNombreFisico", query = "SELECT a FROM Archivo a WHERE a.archivoNombreFisico = :archivoNombreFisico"),
    @NamedQuery(name = "Archivo.findByArchivoUsuario", query = "SELECT a FROM Archivo a WHERE a.archivoUsuario = :archivoUsuario")})
public class Archivo implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "archivo_id")
    private Integer archivoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_virtual")
    private String archivoNombreVirtual;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_nombre_fisico")
    private String archivoNombreFisico;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 256)
    @Column(name = "archivo_usuario")
    private String archivoUsuario;

    public Archivo() {
    }

    public Archivo(Integer archivoId) {
        this.archivoId = archivoId;
    }

    public Archivo(Integer archivoId, String archivoNombreVirtual, String archivoNombreFisico, String archivoUsuario) {
        this.archivoId = archivoId;
        this.archivoNombreVirtual = archivoNombreVirtual;
        this.archivoNombreFisico = archivoNombreFisico;
        this.archivoUsuario = archivoUsuario;
    }

    public Integer getArchivoId() {
        return archivoId;
    }

    public void setArchivoId(Integer archivoId) {
        this.archivoId = archivoId;
    }

    public String getArchivoNombreVirtual() {
        return archivoNombreVirtual;
    }

    public void setArchivoNombreVirtual(String archivoNombreVirtual) {
        this.archivoNombreVirtual = archivoNombreVirtual;
    }

    public String getArchivoNombreFisico() {
        return archivoNombreFisico;
    }

    public void setArchivoNombreFisico(String archivoNombreFisico) {
        this.archivoNombreFisico = archivoNombreFisico;
    }

    public String getArchivoUsuario() {
        return archivoUsuario;
    }

    public void setArchivoUsuario(String archivoUsuario) {
        this.archivoUsuario = archivoUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (archivoId != null ? archivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Archivo)) {
            return false;
        }
        Archivo other = (Archivo) object;
        if ((this.archivoId == null && other.archivoId != null) || (this.archivoId != null && !this.archivoId.equals(other.archivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entity.Archivo[ archivoId=" + archivoId + " ]";
    }
    
}
