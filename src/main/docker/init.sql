CREATE DATABASE pem;

\connect pem;

CREATE ROLE pem_gerente;
ALTER ROLE pem_gerente SET search_path TO public;
GRANT ALL PRIVILEGES ON DATABASE pem TO pem_gerente;

CREATE USER pem WITH PASSWORD 'abaco' IN ROLE pem;