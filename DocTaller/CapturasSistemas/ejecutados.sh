sudo apt update
sudo apt upgrade
sudo curl -fsSL sudo curl -fsSL https://get.docker.com -o get-docker.sh
sudo chmod +x get-docker.sh
sudo usermod -aG docker 1daw3
mkdir Oracle
cd Oracle 
mkdir data 
docker pull epiclabs/docker-oracle-xe-11g
sudo docker run -d -v /home/1daw3/Oracle/data:/u01/app/oracle --name=TallerDB -p 1521:1
521 --restart unless-stopped -e ORACLE_ALLOW_REMOTE=true -e ORACLE_PASSWORD=1daw3 epiclabs/doc
ker-oracle-xe-11g
sudo apt install default-jre