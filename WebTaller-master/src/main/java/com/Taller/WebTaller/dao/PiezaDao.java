package com.Taller.WebTaller.dao;

import com.Taller.WebTaller.modelos.Categoria;
import com.Taller.WebTaller.modelos.Marca;
import com.Taller.WebTaller.modelos.Pieza;

import java.util.ArrayList;
import java.util.List;

public interface PiezaDao {

    public List<Pieza> LeerPiezasTodas();
    public List<Pieza> LeerPorMarca(String marca);
    public List<Pieza> LeerPorCategoria(String categoria);
    public List<Marca> ListaMarcas();
    public List<Categoria> ListaCategorias();
    public Pieza piezaPorId(int id);
}