#!/bin/zsh
# This script is used to pull the latest postgresql image, and start the
# server automatically, removing human error for program startup.
# just run: "./autopgstart.sh" without the "" from the
# root of the project directory.
#
# The docker command to start the postgresql server.
docker compose up --remove-orphans
#
# wait for the container to be running, and accepting connections before
# starting the fP.main()
if [[ $(docker inspect -f '{{.State.Running}}' postgres) == "true" ]]; then
    echo "PostgreSQL server is running."
    echo "Starting fP.main()..."
    fP.main()
else
    echo "PostgreSQL server is not running."
    echo "Exiting..."
    exit 1
fi
