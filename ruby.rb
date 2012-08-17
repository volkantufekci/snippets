# remove all whitespaces
" hede hodo ".delete(' ')     # "hedehodo", so this one is simpler than the regexp one below

" hede hodo ".gsub(/\s+/, "") # "hedehodo"


