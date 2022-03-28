cd ~
mkdir Oracle
cd Oracle
mkdir data
sudo usermod -a -G docker usuario
sudo docker run -d -v /home/usuario/Oracle/data:/u01/app/oracle --name=TallerDB -p 1521:1521 --restart unless-stopped -e ORACLE_ALLOW_REMOTE=true -e ORACLE_PASSWORD=1daw3 epiclabs/docker-oracle-xe-11g