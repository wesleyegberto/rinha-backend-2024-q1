#!/usr/bin/env bash
set -e errexit
cd api-transacoes-jvm
docker buildx build --platform linux/amd64 -t wesleyegberto/rinhabackend2024q1-java-jvm:latest .
