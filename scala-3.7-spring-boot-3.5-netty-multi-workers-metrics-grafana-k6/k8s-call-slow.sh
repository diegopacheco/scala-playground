#!/bin/bash
set -e
echo "Calling slow endpoint with 60 seconds delay..."
curl -i http://localhost:30082/slow/60
echo ""
echo "Done!"
