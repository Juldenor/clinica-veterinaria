--
-- PostgreSQL database cluster dump
--

-- Started on 2026-03-05 16:30:24

\restrict gtIbPAOYMkPCFV7PD1FUiOOg7oEtGac8pCTdXslYHWFhPlBCeUwWIaioahRHcvZ

SET default_transaction_read_only = off;

SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;

--
-- Roles
--

CREATE ROLE postgres;
ALTER ROLE postgres WITH SUPERUSER INHERIT CREATEROLE CREATEDB LOGIN REPLICATION BYPASSRLS PASSWORD 'SCRAM-SHA-256$4096:ytlk7gpbqRURlJ2MCAIphg==$sWtiro9XJn3tsmdvnoM/LCedMY0TV5ALXNN6kaQNZ7M=:kH4qpyR3ZmYKuLJG44igT64ImeBDuvPCJ4/SYfSvZDg=';

--
-- User Configurations
--








\unrestrict gtIbPAOYMkPCFV7PD1FUiOOg7oEtGac8pCTdXslYHWFhPlBCeUwWIaioahRHcvZ

--
-- Databases
--

--
-- Database "template1" dump
--

\connect template1

