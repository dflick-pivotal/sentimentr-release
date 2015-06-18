#!/bin/sh

# For use to create final release tarball
CREATE_FINAL_TARBALL=true

echo "Cleanup previous release ..."
rm -rf releases/sentimentr-release/*.tgz

echo "Creating the release ..."
bosh create release --force

if [ "$CREATE_FINAL_TARBALL" == "true" ]; then
  # To create a final tarball release
  bosh -n create release --with-tarball --final --force
fi

echo "Done creating the release ..."
