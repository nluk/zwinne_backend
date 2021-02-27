echo "Creating initial fuel.app collection"
mongo --eval "
db.auth('$MONGO_INITDB_ROOT_USERNAME', '$MONGO_INITDB_ROOT_PASSWORD'); 
db = db.getSiblingDB('$DB_NAME'); 
db.createUser({ user: '$DB_USER', pwd: '$DB_PASSWORD', roles: [{ role: 'readWrite', db: '$DB_NAME' }] });
db.createCollection('fuel_users');
db.fuel_users.createIndex({ 'login' : 1 }, { unique : true });
" 
echo "init.sh done"