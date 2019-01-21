#!/bin/sh

echo "Cleanup previous release ..."
rm -rf releases/sentimentr-release/*.tgz

echo "Creating the release ..."
bosh create release --with-tarball --force

#bosh -n create release --with-tarball --final --force

echo "Done creating the release ..."
