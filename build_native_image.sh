#!/usr/bin/env bash
set -e errexit
cd api-transacoes
mvn clean native:compile -Pnative -DskipTests
