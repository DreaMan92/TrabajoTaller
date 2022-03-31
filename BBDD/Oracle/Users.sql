
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
    DNI VARCHAR2(9) UNIQUE,
    CORREO VARCHAR2(20) UNIQUE
) tablespace taller;
create public synonym CLIENTE for system.CLIENTE;
CREATE SEQUENCE CLIENTES_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER CLIENTES_IDS BEFORE INSERT ON CLIENTE FOR EACH ROW
BEGIN SELECT CLIENTES_SEQ.NEXTVAL INTO :NEW.ID_CLIENTE FROM DUAL;
END;
/


create table VEHICULO(
    ID_VEHICULO NUMBER(10) PRIMARY KEY,
    MODELO VARCHAR2(20),
    COLOR VARCHAR2(10),
    AÑO DATE,
    MATRICULA VARCHAR2(7) UNIQUE,
    ID_CLIENTE NUMBER(10)
) tablespace taller;
create public synonym VEHICULO for system.VEHICULO;
CREATE SEQUENCE VEHICULOS_SEQ START WITH 1;

CREATE OR REPLACE TRIGGER VEHICULOS_IDS BEFORE INSERT ON VEHICULO FOR EACH ROW
BEGIN SELECT VEHICULO_SEQ.NEXTVAL INTO :NEW.ID_VEHICULO FROM DUAL;
END;
/


create table FACTURA(
    ID_FACTURA NUMBER(10) PRIMARY KEY,
    FECHA_ENTRADA DATE,
    PRECIO_TOTAL NUMBER(5,2),
    FECHA_FIN DATE,
    PAGADO NUMBER(1,0),
    ID_VEHICULO NUMBER(10)
) tablespace taller;
create public synonym FACTURA for system.FACTURA;
CREATE SEQUENCE FACTURA_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER FACTURAS_IDS BEFORE INSERT ON FACTURA FOR EACH ROW
BEGIN SELECT FACTURA_SEQ.NEXTVAL INTO :NEW.ID_FACTURA FROM DUAL;
END;
/

create table REPARACION(
    ID_REPARACION NUMBER(10) PRIMARY KEY,
    TIEMPO_HORA DATE,
    HORAS_DURACION NUMBER(4,2),
    COMENTARIOS VARCHAR2(300),
    ID_FACTURA NUMBER(10)

) tablespace taller;
create public synonym REPARACION for system.reparacion;
CREATE SEQUENCE REPARACION_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER REPARACION_IDS BEFORE INSERT ON REPARACION FOR EACH ROW
BEGIN SELECT REPARACION_SEQ.NEXTVAL INTO :NEW.ID_REPARACION FROM DUAL;
END;
/

create table piezas(
    ID_PIEZA NUMBER(10) PRIMARY KEY,
    MARCA VARCHAR2(20),
    MODELO VARCHAR2(20),
    PRECIO NUMBER(5,2),
    STOCK NUMBER(3),
    DESCRIPCION VARCHAR2(100),
    CATEGORIA VARCHAR2(20)
) tablespace taller;
create public synonym PIEZAS for system.piezas;
CREATE SEQUENCE PIEZAS_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER PIEZAS_IDS BEFORE INSERT ON PIEZAS FOR EACH ROW
BEGIN SELECT PIEZAS_SEQ.NEXTVAL INTO :NEW.ID_PIEZA FROM DUAL;
END;
/

create table usa(
    ID_PIEZA NUMBER(10),
    ID_REPARACION NUMBER(10),
    CANTIDAD NUMBER(3)
) tablespace taller;
create public synonym USA for system.USA;

create table Realiza(
    ID_REPARACION NUMBER(10),
    ID_EMPLEADO NUMBER(10)

) tablespace taller;
create public synonym REALIZA for system.Realiza;

create table empleado(
    ID_EMPLEADO NUMBER(10) PRIMARY KEY,
    NOMBRE VARCHAR2(10),
    APELLIDO VARCHAR2(10),
    TELEFONO VARCHAR2(9),
    IBAN VARCHAR2(30),
    DNI VARCHAR2(9),
    ID_JEFE NUMBER(10)
)tablespace administracion;
create public synonym EMPLEADO for system.empleado;
CREATE SEQUENCE EMPLEADO_SEQ START WITH 1;
CREATE OR REPLACE TRIGGER EMPLEADOS_IDS BEFORE INSERT ON EMPLEADO FOR EACH ROW
BEGIN SELECT EMPLEADO_SEQ.NEXTVAL INTO :NEW.ID_EMPLEADO FROM DUAL;
END;
/

ALTER TABLE VEHICULO ADD CONSTRAINT VEHICULO_FK FOREIGN KEY (ID_CLIENTE) REFERENCES CLIENTE(ID_CLIENTE);
ALTER TABLE FACTURA ADD CONSTRAINT FACTURA_FK FOREIGN KEY (ID_VEHICULO) REFERENCES VEHICULO(ID_VEHICULO);
ALTER TABLE REPARACION ADD CONSTRAINT REP_FK FOREIGN KEY (ID_FACTURA) REFERENCES FACTURA(ID_FACTURA);
ALTER TABLE EMPLEADO ADD CONSTRAINT EMP_FK FOREIGN KEY (ID_JEFE) REFERENCES EMPLEADO(ID_EMPLEADO);
ALTER TABLE USA ADD CONSTRAINT USA_PK PRIMARY KEY (ID_PIEZA,ID_REPARACION);
ALTER TABLE USA ADD CONSTRAINT USA_FK1 FOREIGN KEY (ID_PIEZA) REFERENCES PIEZA(ID_PIEZA);
ALTER TABLE USA ADD CONSTRAINT USA_FK2 FOREIGN KEY (ID_REPARACION) REFERENCES REPARACION(ID_REPARACION);
ALTER TABLE REALIZA ADD CONSTRAINT REALIZA_PK PRIMARY KEY (ID_EMPLEADO,ID_REPARACION);
ALTER TABLE REALIZA ADD CONSTRAINT REALIZA_FK1 FOREIGN KEY (ID_EMPLEADO) REFERENCES EMPLEADO(ID_EMPLEADO);
ALTER TABLE REALIZA ADD CONSTRAINT REALIZA_FK2 FOREIGN KEY (ID_REPARACION) REFERENCES REPARACION(ID_REPARACION);


ALTER TABLE CLIENTE ADD CONSTRAINT DNI_FORM CHECK (REGEXP_LIKE(DNI,'\d{8}[A-Z]$'));
ALTER TABLE CLIENTE ADD CONSTRAINT TEL_FORM CHECK (REGEXP_LIKE(TELEFONO,'\d{9}'));
ALTER TABLE CLIENTE ADD CONSTRAINT CORREO_FROM CHECK (REGEXP_LIKE(CORREO,'[A-Za-zá-ú0-9_.]*@[A-Za-zá-ú0-9_]*.[A-Za-z]*$')) 
ALTER TABLE EMPLEADO ADD CONSTRAINT EMP_DNI_FORM CHECK (REGEXP_LIKE(DNI,'\d{8}[A-Z]$'))
ALTER TABLE EMPLEADO ADD CONSTRAINT EMP_TEL_FORM CHECK (REGEXP_LIKE(TELEFONO,'\d{9}'))
/*ALTER TABLE VEHICULO ADD CONSTRAINT MATR_FORM CHECK (REGEXP_LIKE(MATRICULA, ''))
*/


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

