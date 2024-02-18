#!/usr/bin/env bash
set -e errexit
cd api-transacoes
mvn clean spring-boot:build-image -Pnative
