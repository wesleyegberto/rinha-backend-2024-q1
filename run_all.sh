#!/usr/bin/env bash
set -e errexit
docker compose -f docker-compose.yml up
sleep 3
docker compose -f docker-compose.yml rm
