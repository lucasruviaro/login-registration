# Login/Registration application

This application allows the user to register a new account, which is stored in AWS RDS using PostgreSQL.

The user can register an account with the following request:

POST - /api/v1/registration
```
{
    "firstName": "Lucas",
    "lastName": "Ruviaro",
    "email": "lucasruviaro2@hotmail.com",
    "password": "password123"
}
```

After doing it, using MailDev, the user will receive an e-mail with the link to verificate the account created. After clicking the link, the account becomes enabled and accessible.
