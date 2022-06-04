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
public class Marca implements Serializable
{
    private Integer IdMarca;
    private String marca;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Marca() 
    {
    }

    public Integer getIdMarca() {
        return IdMarca;
    }

    public void setIdMarca(Integer IdMarca) {
        this.IdMarca = IdMarca;
    }

    
}
