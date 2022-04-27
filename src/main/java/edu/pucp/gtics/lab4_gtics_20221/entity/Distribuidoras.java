package edu.pucp.gtics.lab4_gtics_20221.entity;

import edu.pucp.gtics.lab4_gtics_20221.entity.Paises;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "distribuidoras")
public class Distribuidoras {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddistribuidora", nullable = false)
    private Integer id;

    @Size(min = 3, max = 50, message = "El nombre debe contener entre 3 y 50 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @Size(min = 3, max = 198, message = "La descripción dee contener entre 3 y 198 caracteres")
    @Column(name = "descripcion")
    private String descripcion;

    @Min(value = 1800,message = "El año no puede ser menor a 1800")
    @Max(value = 2100,message = "El año no puede ser mayor a 2100")
    @Column(name = "fundacion", nullable = false)
    private Integer fundacion;

    @Size(min=3,max=192,message = "La url debe estar comprendida entre 3 y 198 caracteres")
    @URL(protocol = "https")
    @Column(name = "web")
    private String web;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idsede")
    @NotNull(message = "Debe seleccionar un país")
    private Paises idsede;

    public Paises getIdsede() {
        return idsede;
    }

    public void setIdsede(Paises idsede) {
        this.idsede = idsede;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public Integer getFundacion() {
        return fundacion;
    }

    public void setFundacion(Integer fundacion) {
        this.fundacion = fundacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}