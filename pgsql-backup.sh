#!/bin/bash
 USER=postgres
 # shellcheck disable=SC2209
 PASS=admin
 KEEP=14
 DIR=/etc/backup
 PGPASSWORD=$PASS
 export PGPASSWORD
 NOW=$(date +"%Y-%m-%d")
 DBS="$(psql -U $USER -lt | awk '{print $1}' |grep -vE '^\||^-|^List|^Name|template[0|1]|postgres')"
 for db in $DBS
   do
     # shellcheck disable=SC2061
     BACKUPS=`find $DIR -name $db.*.gz |wc -l|sed 's/\ //g'`
     while [ $BACKUPS -ge $KEEP ]
       do
        ls -tr1 $DIR/$db.*.gz | head -n 1 | xargs rm -f
        BACKUPS=`expr $BACKUPS - 1`
       done
     FILE=$DIR/$db.$NOW-$(date +"%H"-"%M")
     pg_dump --column-inserts --attribute-inserts -U $USER -Fc $db | gzip -c > $FILE.gz
   done
 PGPASSWORD=
 export PGPASSWORD
 exit 0