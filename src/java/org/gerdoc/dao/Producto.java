/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.gerdoc.dao;

import java.io.Serializable;

/**
 *
 * @author Alumno
 */
public class Producto implements Serializable
{
    private Integer idProducto;
    private String nombre;
    private String descripcion;
    private String url;
    private Integer PrecioPub;
    private Integer costo;
    private Marca marca;
    private Proveedor proveedor;

    public Marca getMarca() {
        return marca;
    }

    public Producto(Marca marca, Proveedor proveedor) {
        this.marca = marca;
        this.proveedor = proveedor;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProvedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

   public Producto() 
    {       
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPrecioPub() {
        return PrecioPub;
    }

    public void setPrecioPub(Integer PrecioPub) {
        this.PrecioPub = PrecioPub;
    }

    public Integer getCosto() {
        return costo;
    }

    public void setCosto(Integer costo) {
        this.costo = costo;
    }

    
    
}