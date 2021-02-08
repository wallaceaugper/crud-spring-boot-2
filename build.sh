echo "INICIANDO DEPLOY"

mvn clean install -D skipTests -D liquibase.dropFirst=true
echo "TERMINOU O CLEAN INSTALL\n"

cp /home/wallace-moraes/IdeaProjects/stream/target/stream.jar /home/wallace-moraes/IdeaProjects/stream/docker/
echo "FEZ A COPIA\n"

cd docker

sudo docker build -t stream .
echo "BUILDOU A IMAGEM\n"

rm stream.jar
echo "REMOVEU A IMAGEM\n"

sudo docker run --name stream -d -p 8080:8080 --network stream-network  stream
echo "RODOU A IMAGEM\n"