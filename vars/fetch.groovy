#!/bin/bash

def call() {
  OUTPUT="\$(curl http://localhost:3000/users)"
  echo "You output : ${OUTPUT}"
  echo "Processing users..."
  USER_ARRAY=()
  
  # Traverse each object and extract `username` and `client_access_token`
  echo "$OUTPUT" | jq -c '.[]' | while read -r user; do
      USERNAME=$(echo "$user" | jq -r '.githubUserName')
      CLIENT_TOKEN=$(echo "$user" | jq -r '.client_access_token')
  
      echo "Username: $USERNAME"
      echo "Client Token: $CLIENT_TOKEN"
      USER_ARRAY+=("{\"githubUserName\": \"$USERNAME\", \"client_access_token\": \"$CLIENT_TOKEN\"}")
  done
  
  echo "Final array : $USER_ARRAY"
}
