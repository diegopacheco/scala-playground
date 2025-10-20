#!/bin/bash
echo "Creating 1000 entries..."
curl -s http://localhost:8081/create/1000 > /dev/null
if [ $? -eq 0 ]; then
  echo "Successfully created 1000 entries!"
else
  echo "Failed to create entries"
  exit 1
fi
