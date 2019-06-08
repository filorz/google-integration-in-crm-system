#!/usr/bin/env bash

mvn clean package

echo 'Copy files...'

scp -i ~/.ssh/key-rsa \
    target/crm-pet-project-0.0.1-SNAPSHOT.jar \
    image.properties \
    google-calendar.properties \
    eefilee@35.188.221.240:/home/eefilee/

echo 'Restart server...'

ssh -i ~/.ssh/key-rsa eefilee@35.188.221.240 << EOF

pgrep java | xargs kill -9
nohup java -jar crm-pet-project-0.0.1-SNAPSHOT.jar > log.txt &

EOF

echo 'Bye'