
/*Creamos tablespaces*/
create tablespace administracion datafile 'administracion.dbf' size 1M autoextend on;
create tablespace taller datafile 'taller.dbf' size 1M autoextend on;

/*Creamos los usuarios*/
create user Jefe identified by "jefe_1daw3_chuleta";
alter user Jefe default tablespace administracion;
create user mech1 identified by "mech1";
alter user mech1 default tablespace taller;
create user mech2 identified by "mech2";
alter user mech2 default tablespace taller;
create user mech3 identified by "mech3";
alter user mech3 default tablespace taller;
create user mech4 identified by "mech4";
alter user mech4 default tablespace taller;
create user mech5 identified by "mech5";
alter user mech5 default tablespace taller;



/*Modificamos los perfiles por defecto para que no pidan cambiar password*/
alter profile default limit PASSWORD_REUSE_TIME UNLIMITED;
alter profile DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;

/*Modificamos perfil de los mecanicos, maximo 1 sesion por usuario*/
create profile sesiones_mecanico LIMIT
sessions_per_user 1
failed_login_attempts UNLIMITED;
alter user mech1 PROFILE sesiones_mecanico;
alter user mech2 PROFILE sesiones_mecanico;
alter user mech3 PROFILE sesiones_mecanico;
alter user mech4 PROFILE sesiones_mecanico;
alter user mech5 PROFILE sesiones_mecanico;



/*Cremos las tablas, triggers y costraints*/
create table CLIENTE(
    ID_CLIENTE NUMBER(10) PRIMARY KEY,
    NOMBRE VARCHAR2(20) NOT NULL,
    APELLIDO VARCHAR2(20) NOT NULL,
    TELEFONO VARCHAR2(9) NOT NULL,
    DIRECCION VARCHAR2(40),
    DNI VARCHAR2(9)
) tablespace taller;
create public synonym CLIENTE for system.CLIENTE;
CREATE SEQUENCE CLIENTES_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER CLIENTES_IDS BEFORE INSERT ON CLIENTE FOR EACH ROW
BEGIN SELECT CLIENTES_SEQ.NEXTVAL INTO :NEW.ID_CLIENTE FROM DUAL;
END;
/


create table VEHICULO(


) tablespace taller;
create public synonym VEHICULO for system.VEHICULO;

create table factura(


) tablespace taller;
create public synonym FACTURA for system.FACTURA;

create table reparacion(


) tablespace taller;
create public synonym REPARACION for system.reparacion;

create table piezas(


) tablespace taller;
create public synonym PIEZAS for system.piezas;

create table usa(


) tablespace taller;
create public synonym USA for system.USA;

create table Realiza(


) tablespace taller;
create public synonym REALIZA for system.Realiza;

create table empleado(


)tablespace administracion;
create public synonym EMPLEADO for system.empleado;




/*Creamos roles para usuario de administracion y mecanico, y los asignamos*/
create role rol_administrador;
grant all privileges to rol_administrador;
create role rol_mecanico;
grant create SESSION to rol_mecanico;
grant update (Stock) on Piezas to rol_mecanico;
grant insert on Piezas to rol_mecanico;
grant select on Piezas to rol_mecanico;
grant all on CLIENTE to rol_mecanico;

revoke delete on CLIENTE from rol_mecanico;
grant all on Vehiculo to rol_mecanico;
revoke delete on Vehiculo from rol_mecanico;
grant all on Factura to rol_mecanico;
revoke delete on Factura from rol_mecanico;
grant all on Reparacion to rol_mecanico;
grant all on Usa to rol_mecanico;
revoke delete on Usa from rol_mecanico;
grant insert on Realiza to rol_mecanico;
grant select on Empleados to rol_mecanico;

grant rol_administrador to Jefe;
grant rol_mecanico to mech1;
grant rol_mecanico to mech2;
grant rol_mecanico to mech3;
grant rol_mecanico to mech4;
grant rol_mecanico to mech5;
alter user mech1 DEFAULT role rol_mecanico;
alter user mech2 DEFAULT role rol_mecanico;
alter user mech3 DEFAULT role rol_mecanico;
alter user mech4 DEFAULT role rol_mecanico;
alter user mech5 DEFAULT role rol_mecanico;

