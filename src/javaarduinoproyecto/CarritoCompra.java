/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaarduinoproyecto;

/**
 *
 * @author AXVAN
 */
public class CarritoCompra {
    private int idCarrito;
    private int usuarioID;
    private int producto;
    private int cantidad;

    public CarritoCompra(int idCarrito, int usuarioID, int producto, int cantidad) {
        this.idCarrito = idCarrito;
        this.usuarioID = usuarioID;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public int getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(int idCarrito) {
        this.idCarrito = idCarrito;
    }

    public int getUsuarioID() {
        return usuarioID;
    }

    public void setUsuarioID(int usuarioID) {
        this.usuarioID = usuarioID;
    }

    public int getProducto() {
        return producto;
    }

    public void setProducto(int producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
