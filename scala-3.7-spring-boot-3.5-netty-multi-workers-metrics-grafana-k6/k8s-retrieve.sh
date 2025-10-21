#!/bin/bash
set -e
echo "Creating 3 entries..."
curl -s http://localhost:30081/create/3 | jq '.'
echo ""
echo "Calling retrieve endpoint..."
curl -i http://localhost:30081/retrieve
echo ""
echo "Done!"
