## Consul: instancia local
```
cd ~/code/Documents/consul
./consul agent -server -bootstrap -data-dir /tmp/consul -ui -advertise 10.10.0.46
```

## ELK

### En Vagrant
```
vagrant up services
```

### En Docker
```
docker run -p 51515:51515 logstash -e 'input { tcp { port => 51515 codec => json_lines } } output { elasticsearch { hosts => "192.168.33.10" index => "news" } }'
```




## Docker

### Iniciar agente de Consul 

```bash
docker run -p 8300:8300 -p 8301:8301 -p 8301:8301/udp -p 8302:8302 -p 8302:8302/udp -p 8400:8400 -p 8500:8500 -d progrium/consul -join 192.168.33.10
```

### Iniciar servicio de Logstash 

```bash
docker run -p 51515:51515 logstash -e 'input { tcp { port => 51515 codec => json_lines } } output { elasticsearch { hosts => "192.168.33.10" index => "weather-service" } }'
```

## Iniciando la aplicación
```bash
mvn spring-boot:run -DLOGSTASH_HOST=172.17.0.3 -DLOGSTASH_PORT=51515
```