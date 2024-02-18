#!/usr/bin/env bash
set -e errexit
docker compose -f docker-compose.yml up
docker compose rm
