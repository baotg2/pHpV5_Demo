#!/bin.sh

export BROWSERSTACK_USERNAME=
export BROWSERSTACK_ACCESS_KEY=
export ALLURE_SERVER="https://cloudcall-s01.phpfox.com/allure-docker-service"
export ALLURE_PROJECT=metafox-e2e-dev
export BASE_URL="https://dev-foxsocial.phpfox.us/"

# Run command to load new environment variables
# source ./env.example.sh