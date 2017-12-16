# JavaDB Final Project

### To Use Database With Ryerson's Servers:

1. Log in and port forward with `ssh -L 9876:oracle.scs.ryerson.ca:1521`
2. Set DB_URL to `jdbc:oracle:thin:@localhost:1234:orcl`
3. Set USER to your username
4. Set PASS to your password
5. Use `Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);` to open a connection.
