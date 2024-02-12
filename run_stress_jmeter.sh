#!/usr/bin/env bash
set -e errexit
jmeter -n -t ApiTransacoes.jmx -l jmeter_requests_logs.csv -e -o jmeter-report/
