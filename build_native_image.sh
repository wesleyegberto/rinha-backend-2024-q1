#!/usr/bin/env bash
set -e errexit
cd api-transacoes
mvn -Pnative clean native:compile
