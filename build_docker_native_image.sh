#!/usr/bin/env bash
set -e errexit
cd api-transacoes
docker buildx build --platform linux/amd64 \
	-t wesleyegberto/rinha-backend-2024-q1-native:latest \
	.
