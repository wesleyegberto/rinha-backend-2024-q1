#!/usr/bin/env bash
set -e errexit
docker compose -f docker-compose.db.yml up
docker-compose rm
