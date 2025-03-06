#!/bin/bash

# Find all Java files and process them
find . -type f -name "*.java" | while read -r file; do
    awk '
    BEGIN { inside_switch = 0; indent = "" }

    # Detect switch statement and store its indentation
    /^(\s*)switch\s*\(/ {
        inside_switch = 1;
        indent = substr($0, 0, index($0, "switch") - 1);
        print;
        next;
    }

    # Align 'case' and 'default' at the same indentation level as 'switch'
    inside_switch && /^\s*case / {
	sub(/^(\s+)/, "", $0); # Remove leading spaces
        print indent $0;
        next;
    }

    inside_switch && /^\s*default:/  {
	sub(/^(\s+)/, "", $0); # Remove leading spaces
        print indent $0;
        next;
    }


    # Indent statements inside case one level deeper
    inside_switch && !/^\s*case / && !/^\s*default:/ && !/^\s*}/ {
	sub(/^(\s+)/, "", $0); # Remove leading spaces
        print indent $0;
        #print "                     " $0;
        next;
    }

    # Detect closing '}' of switch-case and reset flag
    inside_switch && /^\s*}/ {
        inside_switch = 0;
        print;
        next;
    }

    { print }
    ' "$file" > temp.java && mv temp.java "$file"
done

echo "Switch-case indentation updated in all Java files."

