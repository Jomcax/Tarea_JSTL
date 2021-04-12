package com.emergentes.modelo;

import java.util.ArrayList;
import java.util.Iterator;

public class GestorProductos {
    
    private ArrayList<productos> lista;
    
    public GestorProductos()
    {
        lista = new ArrayList<productos>();
    }
    
    public ArrayList<productos> getLista(){
        return lista;
    }
    
    public void setLista(ArrayList<productos> lista){
        this.lista = lista;
    }
    
    public void insertarProductos(productos item){
        lista.add(item);
    }
    
    public void modificarProductos(int pos, productos item){
        lista.set(pos, item);
    }
    
    public void eliminarProductos(int pos){
        lista.remove(pos);
    }
    
    public int obtieneId(){
        int idaux = 0;
        
        for (productos item : lista){
            idaux = item.getId();
        }
        return idaux + 1;
    }
    
    public int ubicarProductos(int id){
        int pos = -1;
        Iterator<productos> it = lista.iterator();
        
        while ( it.hasNext()){
            ++pos;
            productos aux = it.next();
            
            if (aux.getId() == id){
                break;
            }
        }
        return pos;
    }
}
