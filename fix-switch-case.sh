#!/bin/bash

# Define the root directory (can be set to the current directory or project root)
ROOT_DIR="."

# Function to fix the switch-case formatting
fix_switch_case() {
  local file="$1"
  
  # Create a temporary file and ensure it's valid
  TEMP_FILE=$(mktemp)
  if [[ ! -f "$TEMP_FILE" ]]; then
    echo "Error creating temporary file. Exiting..."
    exit 1
  fi
  
  echo "Fixing switch cases in: $file"
  
  # Use awk to modify the switch cases
  awk '
    # When encountering a "switch(" line, print it as is (retaining the parentheses content)
    /switch\s?\(/ {
      print $0  # Retain the original switch statement with the condition inside parentheses
      in_switch = 1
      next
    }
    
    # When encountering a "case" line, print with same indent level as switch
     /case\s+/ && in_switch {
      print substr($0, length($1)+1)
      next
    }

     /^[[:space:]]{3}/ && in_switch {
      print substr($0, 2)  # Strip exactly two leading spaces
      next
    }

    # Close the switch block when encountering a closing brace
    /}/ && in_switch {
      print $0  # Print the closing brace for the switch block
      in_switch = 0
      next
    }

    # For other lines, print them unchanged
    {
      print $0
    }
  ' "$file" > "$TEMP_FILE" && mv "$TEMP_FILE" "$file"
  
  # After moving, unset TEMP_FILE so that it doesn't cause confusion later
  unset TEMP_FILE
}

# Find all .java files and process them
find "$ROOT_DIR" -name "*.java" | while read -r file; do
  fix_switch_case "$file"
done

