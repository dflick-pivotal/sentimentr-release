#!/bin/sh
git stash save --keep-index
git stash drop
git pull
vi sentimentr-tile.yml