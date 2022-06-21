package com.Taller.WebTaller.service;


import com.Taller.WebTaller.dao.PiezaDao;
import com.Taller.WebTaller.dao.PiezaDaoTodos;
import com.Taller.WebTaller.modelos.Categoria;
import com.Taller.WebTaller.modelos.Marca;
import com.Taller.WebTaller.modelos.Pieza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Service
public class Gestor {
    private final PiezaDao piezaDao;

    @Autowired
    public Gestor(PiezaDao piezaDao) {
        this.piezaDao = piezaDao;
    }

    public List<Pieza> obtenerPiezasTodas(){
        return piezaDao.LeerPiezasTodas();
    }
    public List<Pieza> obtenerPiezasMarca(String marca){
        return piezaDao.LeerPorMarca(marca);
    }
    public List<Pieza> obtenerPiezasCategoria(String categoria){ return piezaDao.LeerPorCategoria(categoria);
    }

    public List<Categoria> listaCategorias(){return piezaDao.ListaCategorias();}

    public List<Marca> listaMarcas(){return piezaDao.ListaMarcas();}

    public Pieza obtenerPiezaPorId(int id){return piezaDao.piezaPorId(id);}
}

