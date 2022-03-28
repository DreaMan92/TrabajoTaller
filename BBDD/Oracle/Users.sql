
create tablespace administracion datafile 'administracion' size 1M autoextend on;
create tablespace taller datafile 'taller' size 1M autoextend on;

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


alter profile default limit PASSWORD_REUSE_TIME UNLIMITED;
alter profile DEFAULT LIMIT PASSWORD_LIFE_TIME UNLIMITED;

create profile sesiones_mecanico LIMIT
sessions_per_user 1
failed_login_attemps UNLIMITED;


create role rol_administrador;
grant all on * to rol_administrador;
create rol rol_mecanico;
grant update (Stock) on Piezas to rol_mecanico;
grant insert on Piezas to rol_mecanico;
grant select on Piezas to rol_mecanico;
grant all on Cliente to rol_mecanico;
revoke delete on Cliente to rol_mecanico;
grant all on Vehiculo to rol_mecanico;
revoke delete on Vehiculo to rol_mecanico;
grant all on Factura to rol_mecanico;
revoke delete on Factura to rol_mecanico;
grant all on Reparacion to rol_mecanico;
grant all on Usa to rol_mecanico;
revoke delete on Usa to rol_mecanico;
grant insert on Realiza to rol_mecanico;
grant select on Empleados to rol_mecanico;

grant rol_administrador to Jefe;
grant rol_mecanico to mech1;
grant rol_mecanico to mech2;
grant rol_mecanico to mech3;
grant rol_mecanico to mech4;
grant rol_mecanico to mech5;

