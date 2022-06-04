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
public class Proveedor implements Serializable
{
    private Integer IdProveedor;
    private String provedor;

    public Proveedor() 
    {
    }

    public Integer getIdProveedor() {
        return IdProveedor;
    }

    public void setIdProveedor(Integer IdProveedor) {
        this.IdProveedor = IdProveedor;
    }

    public String getProveedor() {
        return provedor;
    }

    public void setProveedor(String provedor) {
        this.provedor = provedor;
    }

   

}
