package com.Taller.WebTaller.dao;


import com.Taller.WebTaller.modelos.Categoria;
import com.Taller.WebTaller.modelos.Marca;
import com.Taller.WebTaller.modelos.Pieza;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class PiezaDaoTodos implements PiezaDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Pieza> LeerPiezasTodas() {
        String sql = "SELECT id_pieza,marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria";

        return  jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Pieza> LeerPorMarca(String marca) {
        String sql = "SELECT id_pieza,marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria where marca='"+marca+"'";

        return  jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Pieza> LeerPorCategoria(String categoria) {
        String sql = "SELECT id_pieza,marca,modelo,precio,descripcion,stock,url,categorias.nombre as categoria FROM piezas inner join categorias on piezas.id_categoria = categorias.id_categoria where categorias.nombre='"+categoria+"'";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }

    @Override
    public List<Marca> ListaMarcas(){
        String sql = "select marca, count(*) as cantidad from PIEZAS group by marca";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Marca.class));
    }
    @Override
    public List<Categoria> ListaCategorias(){
        String sql = "select nombre from categorias";
        return jdbcTemplate.query(sql,
                BeanPropertyRowMapper.newInstance(Categoria.class));
    }

    @Override
    public Pieza piezaPorId(int id) {
        String sql = "select id_pieza,marca,modelo,precio,descripcion,stock,url from piezas where id_pieza="+id;
        return  jdbcTemplate.queryForObject(sql,
                BeanPropertyRowMapper.newInstance(Pieza.class));
    }
}