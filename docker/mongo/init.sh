mongo --eval "
db.auth('$MONGO_INITDB_ROOT_USERNAME', '$MONGO_INITDB_ROOT_PASSWORD'); 
db = db.getSiblingDB('$DB_NAME'); 
db.createUser({ user: '$DB_USER', pwd: '$DB_PASSWORD', roles: [{ role: 'readWrite', db: '$DB_NAME' }] });
db.createCollection('fuel_users');
db.fuel_users.insertOne(
  { email: 'admin@admin.com',
    password: 'a99ced7cf16f6ff50d1cdd9148dc4b78296e43421ebafb1a8f4da4160366b8b2',
    grantedRoles: [{role: 'ADMIN'}],
    login: 'admin'
  }
);
db.fuel_users.createIndex({ 'login' : 1 }, { unique : true });
" 
